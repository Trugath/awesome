import sbt._

object BuildDef extends Build {
  override lazy val projects = Seq(root)
  lazy val root = Project("awesome", file(".")) dependsOn cafebabe
  lazy val cafebabe = RootProject(uri("https://github.com/Trugath/cafebabe.git"))
}