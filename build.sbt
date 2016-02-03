name := "hello"

version := "1.0"

scalaVersion := "2.10.3"

mainClass in Compile := Some("Web")

resolvers += Classpaths.typesafeResolver

libraryDependencies += "com.twitter" %% "finagle-http" % "6.31.0"

enablePlugins(JavaAppPackaging)
