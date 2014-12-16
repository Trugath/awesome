name := "awesome"

version := "1.0"

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

scalacOptions ++= Seq("-optimize", "-Yinline-warnings", "-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"