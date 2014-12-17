name := "awesome"

version := "1.0"

scalaVersion := "2.11.4"

scalacOptions ++= Seq("-optimize", "-Yinline-warnings", "-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies += "org.parboiled" %% "parboiled" % "2.0.1"