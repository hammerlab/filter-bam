# filter-bam

[![Maven Central](https://img.shields.io/maven-central/v/org.hammerlab/filter-bam_2.11.svg?maxAge=1800)](http://search.maven.org/#search%7Cga%7C1%7Cfilter-bam)

Stand-alone utility for filtering a BAM file to specific genomic regions, using Apache Spark.

## Usage

```bash
sbt assembly
spark-submit \
  --properties-file <spark properties file> \
  target/scala-2.11/filter-bam-assembly-1.0.0-SNAPSHOT.jar \
  in.bam \
  <regions> \
  out.bam \
  [-c] [--include-unmapped-mates]
```

### Parameters
- `<regions>`: comma-separated list of genomic regions, in [the format accepted by `hammerlab/genomic-loci`](https://github.com/hammerlab/genomic-loci/blob/1.4.1/src/main/scala/org/hammerlab/genomics/loci/parsing/LociRange.scala#L5-L15).
- `-c`/`--count`: print the number of reads output, in addition to writing `out.bam`.
- `--include-unmapped-mates`: include unmapped reads whose mate-contig and mate-start are set to a value that overlaps `<regions>`.

