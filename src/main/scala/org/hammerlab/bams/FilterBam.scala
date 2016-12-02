package org.hammerlab.bams

import org.apache.spark.{ SparkConf, SparkContext }
import org.bdgenomics.adam.rdd.ADAMContext._
import org.bdgenomics.utils.cli.{ Args4j, Args4jBase }
import org.hammerlab.commands.{ Args, SparkCommand }
import org.hammerlab.genomics.loci.parsing.ParsedLoci
import org.kohsuke.args4j.{ Argument, Option => Args4JOption }

class Arguments extends Args {
  @Argument(
    index = 0,
    required = true,
    usage = "Input BAM path."
  )
  var inputBamStr: String = ""

  @Argument(
    index = 1,
    required = true,
    usage = "Loci to filter BAM to."
  )
  var lociStr: String = ""

  @Argument(
    index = 2,
    required = true,
    usage = "Output BAM path."
  )
  var outputBamStr: String = ""

  @Args4JOption(
    name = "--count",
    aliases = Array("-c"),
    usage = "When set, print the number of reads in the output BAM."
  )
  var printCount: Boolean = false

  @Args4JOption(
    name = "--include-unmapped-mates",
    usage = "When set, include unmapped reads whose mates are mapped to selected regions."
  )
  var includeUnmappedMates: Boolean = false
}

object FilterBam extends SparkCommand[Arguments] {

  override def name: String = "filter-bam"

  override def description: String = "Filter a BAM to given regions"

  override def run(args: Arguments, sc: SparkContext): Unit = {

    val loci = ParsedLoci(args.lociStr)

    val bam = sc.loadIndexedBam(args.inputBamStr, loci, includeUnmappedMates = args.includeUnmappedMates)

    if (args.printCount)
      println(s"${bam.rdd.count} reads")

    bam.saveAsSam(args.outputBamStr, asSingleFile = true)
  }
}
