package ldn.cs.access.kafaka;

import ldn.cs.decision.alghthrims.convey.ConveyPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final KafkaProducer instance = new KafkaProducer();

    private KafkaProducer() {
    }

    public static KafkaProducer getInstance() {
        return instance;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic ,String message) {
        kafkaTemplate.send(topic, message);
    }
}
