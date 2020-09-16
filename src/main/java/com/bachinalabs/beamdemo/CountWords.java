package com.bachinalabs.beamdemo;

import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

public class CountWords extends PTransform<PCollection<String>, PCollection<KV<String, Long>>> {


    @Override
    public PCollection<KV<String, Long>> expand(PCollection<String> lines) {

        // Convert lines of text into individual words
        PCollection<String> words = lines.apply(
                ParDo.of(new ExtractWordsFn()));

        // Count the number of times each word counts
        PCollection<KV<String, Long>> wordCounts =
                words.apply(Count.perElement());

        return wordCounts;
    }
}
