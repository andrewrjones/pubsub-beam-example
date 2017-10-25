package com.andrewjones;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;

import java.util.ArrayList;
import java.util.List;

public class PubSubProducerExample {
    public static void main(String[] args){

        PubsubOptions options = PipelineOptionsFactory.as(PubsubOptions.class);
        options.setPubsubRootUrl("http://pubsub:8042");
        Pipeline p = Pipeline.create(options);

        // sample data
        List<String> strings = new ArrayList<>();
        strings.add("hi there");
        strings.add("hi");
        strings.add("hi sue bob");
        strings.add("hi sue");
        strings.add("hi bob");

        PCollection<String> input = p.apply(Create.of(strings));

        input.apply(PubsubIO.writeStrings().to("projects/project-id/topics/words"));

        p.run().waitUntilFinish();
    }
}
