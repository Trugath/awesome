name := "awesome"

version := "1.0"

scalaVersion := "2.11.5"

scalacOptions ++= Seq("-optimize", "-Yinline-warnings", "-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"