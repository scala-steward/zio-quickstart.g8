val zioVersion = "1.0.11"
val zioHttpVersion = "1.0.0.0-RC17"
val zioJsonVersion = "0.2.0-M1"

//TODO those are only local versions => remotely not existing
val zioZMXVersion = "0.0.7"
val zioConfigVersion = "1.0.6+26-04ae8574+20210830-1558-SNAPSHOT"
val zioLoggingVersion = "0.5.11+25-a55eb828+20210830-1557-SNAPSHOT"

// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    resolvers += "Sonatype OSS Snapshots s01" at "https://s01.oss.sonatype.org/content/repositories/snapshots",
    name := "zio-quickstart",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "io.d11"  %% "zhttp" % zioHttpVersion,
      "io.d11"  %% "zhttp-test" % "1.0.0.0-RC17+37-1c8ceea7-SNAPSHOT" % Test,
      "dev.zio" %% "zio-config" % zioConfigVersion,
      "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
      "dev.zio" %% "zio-logging" % zioLoggingVersion,
      "dev.zio" %% "zio-logging-slf4j" % zioLoggingVersion,
      "dev.zio" %% "zio-zmx" % zioZMXVersion,
      "dev.zio" %% "zio-json" % zioJsonVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
      "dev.zio" %% "zio-test-junit" % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
    ),
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    scriptedLaunchOpts ++= List(
      "-Xms1024m",
      "-Xmx1024m",
      "-XX:ReservedCodeCacheSize=128m",
      "-Xss2m",
      "-Dfile.encoding=UTF-8",
    ),
    resolvers += Resolver.url(
      "typesafe",
      url("https://repo.typesafe.com/typesafe/ivy-releases/"),
    )(Resolver.ivyStylePatterns),
  )
