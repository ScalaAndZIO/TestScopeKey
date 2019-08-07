
name in Scope.Global:= "Masterapp"

name in Test := "Testapp"

name in Runtime := "Runtimeapp"

lazy val psk = taskKey[Unit]("Print Scoped Key")

val pskSetting = psk := {
  println("***** [APP NAME] " + name.value)
  println("Hi from naseem")
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

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar")
  )

lazy val util = (project in file("util"))
  .settings(
    commonSettings,
    packageBin in Compile := file(s"${name.value}_${scalaBinaryVersion.value}.jar")
  )

lazy val master = (project in file("."))
  .aggregate(util, core)
