package ldn.cs.access.kafaka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic myTopic() {
        return TopicBuilder.name("topic_data_service_original_message")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // 如果需要初始化多个主题，可以创建多个方法，每个方法创建一个主题。
}