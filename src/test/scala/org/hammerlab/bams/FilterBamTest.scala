package org.hammerlab.bams

import org.bdgenomics.utils.cli.Args4j
import org.hammerlab.spark.test.suite.SparkSuite
import org.hammerlab.test.Resources.file
import org.hammerlab.test.files.Verify

class FilterBamTest extends SparkSuite {
  test("ranges") {
    val outFile = tmpFile(suffix = ".sam")
    val args =
      Args4j[Arguments](
        Array(
          file("test.bam"),
          "Y:2500000-3500000,17:130000-170000",
          outFile
        )
      )

    FilterBam.run(args, sc)

    Verify("test.sam.golden", outFile)
  }
}
