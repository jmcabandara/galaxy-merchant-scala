name := "galaxy-merchant-scala"
organization := "kakumara"
version := "1.0"
scalaVersion := "2.12.1"

libraryDependencies ++= {
  val scalaTestV = "3.0.1"
  val scalaMockV = "3.5.0"
  val scalazScalaTestV = "0.2.3"

  Seq(
    "org.scalatest" %% "scalatest" % scalaTestV % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV % "test"
  )
}
