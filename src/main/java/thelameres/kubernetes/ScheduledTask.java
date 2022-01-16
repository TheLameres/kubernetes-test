package thelameres.kubernetes;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class ScheduledTask {

    private final AtomicLong atomicLong = new AtomicLong(0);
    private final RabbitTemplate rabbitTemplate;

    public ScheduledTask(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Scheduled(fixedDelay = 250) //250 ms
    public void scheduled() {
        rabbitTemplate.setExchange("simple-exchange");
        rabbitTemplate.convertAndSend(new HelloDto(atomicLong.incrementAndGet(), "name"), message -> {
            message
                    .getMessageProperties()
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT); // Даем RabbitMQ знать, что мы сохраняем сообщение
            return message;
        });
    }
}