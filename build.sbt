lazy val root = (project in file(".")).settings(
    organization := "dita-ot",
    name := "dita-ot",
    version := "3.0",
    scalaVersion := "2.11.8"
)

unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(Seq(_))
unmanagedSourceDirectories in Test <<= (scalaSource in Test)(Seq(_))

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "org.specs2" % "specs2-junit_2.11" % "3.8.3",
  "com.github.eerohele" % "expek_2.11" % "0.1.0"
)

testOptions in Test += Tests.Setup(() => {
    System.setProperty("xml.catalog.files", "src/main/catalog-dita.xml")
    System.setProperty("plugins.html5.dir", "src/main/plugins/org.dita.html5")
    System.setProperty("schema.base.dir", "src/main/plugins/org.oasis-open.dita.v1_3/schema-url")
})
