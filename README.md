# Investigating using Apache Beam with PubSub

* Start the [PubSub emulator](https://cloud.google.com/pubsub/docs/emulator)
    * `make up`
* Create the topic
    * `make topic`
* Start Beam consumer and wait for messages
    * `make consumer`
* In another window, use Beam to write to topic. Output will be written to STDOUT by consumer
    * `make producer`
* Stop emulator
    * `make down`
* Clean up
    * `make clean`
