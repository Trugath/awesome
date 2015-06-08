name := "awesome"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-optimize", "-Yinline-warnings", "-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies += "org.parboiled" %% "parboiled" % "2.1.0"