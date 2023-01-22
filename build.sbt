ThisBuild / scalaVersion := "2.13.10"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """services-scala""",
    libraryDependencies ++= Seq(
      guice
    )
  )

val silhouetteVer = "7.0.2"

libraryDependencies ++= Seq(
  //for slick
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.github.tminglei" %% "slick-pg" % "0.20.3",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.20.3",

  "org.postgresql" % "postgresql" % "42.3.4",

  "io.lemonlabs" %% "scala-uri" % "1.5.1",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.atlassian.jwt" % "jwt-api" % "3.2.3",
  "com.atlassian.jwt" % "jwt-core" % "3.2.3",
  "com.pauldijou" %% "jwt-core" % "5.0.0",
  "io.github.honeycomb-cheesecake" %% "play-silhouette" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-password-bcrypt" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-persistence" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-crypto-jca" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-testkit" % silhouetteVer % "test",
)

libraryDependencies += specs2 % Test
resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"



