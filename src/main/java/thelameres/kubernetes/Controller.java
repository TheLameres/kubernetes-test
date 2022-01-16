package thelameres.kubernetes;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class Controller {
    private final DiscoveryClient discoveryClient;

    @GetMapping
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
