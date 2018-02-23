name := "slick-pg"

scalaVersion := "2.12.4"

version := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.typesafe.play" %% "play-json" % "2.6.8",
  "org.postgresql" % "postgresql" % "42.2.1",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "io.underscore" %% "slickless" % "0.3.3",
  "com.github.tminglei" %% "slick-pg" % "0.15.7",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.15.7",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
)
