import sbt.taskKey

name in Scope.Global:= "Masterapp"

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
sampleTask in Test := {val sum = (intTask in Compile).value * 3; println(sum); sum}

lazy val psk = taskKey[Unit]("Print Scoped Key")

val pskSetting = psk := {
  println("***** [APP NAME] " + name.value)
}
val myPsks = Seq(Compile, Test, Runtime) flatMap { conf =>
  inConfig(conf)( Seq(pskSetting) )
}
myPsks

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.2"
)

//Default case (or compile )=> core/psk
//test => core/test:psk
//run => core/runtime:psk
lazy val core = (project in file("core"))
  .settings(
    name in Test := "CoreTestapp",
    name in Runtime := "CoreRunTimeApp",
    name in Compile := "CoreCompileApp",
    commonSettings,
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    myPsks
  )

//Default case (or compile )=> util/psk
//test => util/test:psk
//run => util/runtime:psk
lazy val util = (project in file("util"))
  .settings(
    commonSettings,
    name in Test := "utilTestapp",
    name in Runtime := "utilRunTimeApp",
    name in Compile := "utilCompileApp",
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar"),
    myPsks

  )

lazy val master = (project in file("."))
  .aggregate(util, core)