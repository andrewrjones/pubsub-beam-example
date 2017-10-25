package com.andrewjones;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class PubSubConsumerExample {

    public static void main(String[] args) {
        PubsubOptions options = PipelineOptionsFactory.as(PubsubOptions.class);
        options.setPubsubRootUrl("http://pubsub:8042");
        Pipeline p = Pipeline.create(options);

        p.apply(
                PubsubIO.readStrings().fromTopic("projects/project-id/topics/words"))
                .apply("ExtractWords", ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        // just print to console
                        System.out.println(c.element());
                    }
                }));

        p.run().waitUntilFinish();
    }
}
