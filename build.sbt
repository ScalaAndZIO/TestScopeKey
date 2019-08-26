import sbt.Keys.crossScalaVersions
import sbt.TaskKey

/*The build file contains the master project with 4 sub-project {2 Jvms (Core,util) , js, native }
below is the cross building with two versions
In order to switch to one on the version :
++ <version> [command]
*/
lazy val scala210 = "2.10.2"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala210, scala211)

lazy val master = (project in file("."))
  .aggregate(common, util, core, native)
  .settings(crossScalaVersions := Nil)

lazy val common = project

lazy val core = project
  .settings(
    name in Test := "CoreTestapp",
    name in Runtime := "CoreRunTimeApp",
    name in Compile := "CoreCompileApp",
    crossScalaVersions := List(scala210),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    /* this task running the test inside core
      you can run it by this command :
      core/coreTestTask
      */
    TaskKey[Unit]("coreTestTask") := (testOnly in Test).toTask(" mytests.TestCore").value,
    /*
    this task also running hello world main in the core sub-project
     you can run it by this command :
    core/coreRunTask
     */
    TaskKey[Unit]("coreRunTask") := (runMain in Compile in util).toTask(" mains.MyMain").value
  )
  .dependsOn(common)

lazy val util = project
  .settings(
    crossScalaVersions := supportedScalaVersions,
    name in Test := "utilTestapp",
    name in Runtime := "utilRunTimeApp",
    name in Compile := "utilCompileApp",
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    /* this task running the test inside util
   you can run it by this command :
   util/utilTask
   */
    TaskKey[Unit]("utilTask") := (testOnly in Test).toTask(" mytests.TestUtil").value,
    /* this task running the test2 inside util
   you can run it by this command :
   util/utilTask2
   */
    TaskKey[Unit]("utilTask2") := (testOnly in Test).toTask(" mytests.TestUtil2").value
  )
  .dependsOn(common)

lazy val native = project
  .settings(
    scalaVersion := "2.11.12",
    nativeLinkStubs := true,
    fork in test := true,
    libraryDependencies += "org.scalatest" % "scalatest_native0.3_2.11" % "3.2.0-SNAP10",
    /* this task running the test inside native
   you can run it by this command :
   native/nativeTask
   */
    TaskKey[Unit]("nativeTask") := (testOnly in Test).toTask(" mytests.TestNative").value
  )
  .enablePlugins(ScalaNativePlugin)


lazy val js = project
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := "2.11.12",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6",
    jsDependencies += "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js",
    /* this task running the test inside js
    you can run it by this command :
     js/jsTask
    */
    TaskKey[Unit]("jsTask") := (testOnly in Test).toTask(" tests.JsTest").value
  )

fork in run := true
