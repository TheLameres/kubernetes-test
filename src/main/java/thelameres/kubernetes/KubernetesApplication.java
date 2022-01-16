package thelameres.kubernetes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(value = {SomeProperties.class})
@EnableDiscoveryClient
@EnableScheduling
@Slf4j
public class KubernetesApplication {

    public static void main(String[] args) {
        SpringApplication.run(KubernetesApplication.class, args);
    }


    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "simple-exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "simple-queue")))
    public void listener(HelloDto helloDto) {
        log.info("Received message: {}", helloDto);
    }
}
