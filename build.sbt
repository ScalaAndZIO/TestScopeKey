//cross building with two versions
lazy val scala210 = "2.10.2"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala210, scala211)

ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala210


lazy val master = (project in file("."))
  .aggregate(common,util, core)
  .settings(crossScalaVersions := Nil)

lazy val common = project

//Default case (or compile )=> core/psk
//test => core/test:psk
//run => core/runtime:psk
lazy val core = project
  .settings(
    name in Test := "CoreTestapp",
    name in Runtime := "CoreRunTimeApp",
    name in Compile := "CoreCompileApp",
    crossScalaVersions := List(scala210),
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    myPsks
  )
  .dependsOn(common)

//Default case (or compile )=> util/psk
//test => util/test:psk
//run => util/runtime:psk
lazy val util = project
  .settings(
    //    commonSettings,
    crossScalaVersions := supportedScalaVersions,
    name in Test := "utilTestapp",
    name in Runtime := "utilRunTimeApp",
    name in Compile := "utilCompileApp",
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    myPsks,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    TaskKey[Unit]("mytestTask") := (testOnly in Test).toTask(" mytests.MyTest").value,
    TaskKey[Unit]("mytestTask2") := (testOnly in Test).toTask(" mytests.MyTest2").value
  )
  .dependsOn(common)

import sbt.taskKey

name in Scope.Global := "Masterapp"

name in Test := "Testapp"

name in Runtime := "Runtimeapp"


lazy val sampleTask = taskKey[Int]("A sample task.")
lazy val intTask = taskKey[Int]("An int task")

//intTask
intTask := 1 + 2

//sampleTask
sampleTask := {
  val sum = 1 + 2
  println("sum: " + sum)
  sum
}

//test:sampleTask
sampleTask in Test := {
  val sum = (intTask in Compile).value * 3; println(sum); sum
}

lazy val psk = taskKey[Unit]("Print Scoped Key")

val pskSetting = psk := {
  println("***** [APP NAME] " + name.value)
}
val myPsks = Seq(Compile, Test, Runtime) flatMap { conf =>
  inConfig(conf)(Seq(pskSetting))
}
myPsks


