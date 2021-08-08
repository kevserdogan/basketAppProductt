package com.basketapp.basketappproduct.Orchestration;
import com.basketapp.basketappproduct.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import java.util.UUID;

@Service
public class Consumer {

    @Value("${kafka.topic.productPriceChange}")
    private String priceChangeTopic;
    @Value("${kafka.topic.stockDecreased}")
    private String stockDecreasedTopic;
    @Value("${kafka.topic.soldOut}")
    private String soldOuttopic;
    private final KafkaTemplate kafkaTemplate;


    public Consumer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishProductPriceChangeEvent(String serialNumber) {
        kafkaTemplate.send(priceChangeTopic, UUID.randomUUID().toString(), serialNumber);
    }

    public void publishProductStockDecreasedEvent(String serialNumber) {
        kafkaTemplate.send(stockDecreasedTopic, UUID.randomUUID().toString(), serialNumber);
    }

    public void publishProductSoldOutEvent(String serialNumber) {
        kafkaTemplate.send(soldOuttopic, UUID.randomUUID().toString(), serialNumber);
    }
}
