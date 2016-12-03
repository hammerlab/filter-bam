name := "filter-bam"
version := "1.0.2-SNAPSHOT"
description := "Filter BAMs to loci-intervals using Apache Spark."

sparkVersion := "1.6.1"

libraryDependencies ++= Seq(
  libraries.value('args4j),
  libraries.value('kryo),
  libraries.value('spark_commands),
  "org.hammerlab.adam" %% "adam-core" % "0.20.3",
  "org.hammerlab" %% "genomic-loci" % "1.4.2"
)

providedDeps ++= Seq(
  libraries.value('spark),
  libraries.value('hadoop)
)

testDeps += libraries.value('spark_tests)

mainClass := Some("org.hammerlab.bams.FilterBam")

assemblyMergeStrategy in assembly := {
  // Two org.bdgenomics deps include the same log4j.properties.
  case PathList("log4j.properties") => MergeStrategy.first
  case x => (assemblyMergeStrategy in assembly).value(x)
}
