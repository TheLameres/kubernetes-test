package thelameres.kubernetes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class KubernetesApplicationTests {

    @Container
    public RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(
            DockerImageName.parse("rabbitmq:3.9.12-management"))
            .withUser("user", "user")
            .withNetworkAliases("hello-world")
            .withExposedPorts(5672);

    @Test
    void contextLoads() {
        //Test to load context
    }

}
