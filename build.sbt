name := "filter-bam"
version := "1.0.0-SNAPSHOT"
description := "Filter BAMs to loci-intervals using Apache Spark."

sparkVersion := "1.6.1"

libraryDependencies <++= libraries { v => Seq(
  "org.hammerlab.adam" %% "adam-core" % "0.20.2-SNAPSHOT",
  "com.esotericsoftware.kryo" % "kryo" % "2.21",
  "args4j" % "args4j" % "2.33",
  "org.hammerlab" %% "genomic-loci" % "1.4.0-SNAPSHOT" classifier("assembly"),
  "org.apache.hadoop" % "hadoop-client" % "2.6.0" % "provided"
)}

providedDeps ++= Seq(
  libraries.value('spark)
)

mainClass in (Compile, run) := Some("org.hammerlab.bams.FilterBam")

assemblyMergeStrategy in assembly := {
  case PathList("log4j.properties") => MergeStrategy.first
  case x => (assemblyMergeStrategy in assembly).value(x)
}
