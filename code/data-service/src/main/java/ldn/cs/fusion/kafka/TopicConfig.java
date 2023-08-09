package ldn.cs.fusion.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic staffTopic() {
        return TopicBuilder.name("topic_staff_message")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic wealthTopic() {
        return TopicBuilder.name("topic_wealth_message")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic conveyTopic() {
        return TopicBuilder.name("topic_convey_message")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic productionTopic() {
        return TopicBuilder.name("topic_production_message")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic saleTopic() {
        return TopicBuilder.name("topic_sale_message")
                .partitions(1)
                .replicas(1)
                .build();
    }
}