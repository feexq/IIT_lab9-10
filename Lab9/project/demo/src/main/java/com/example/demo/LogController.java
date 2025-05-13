package com.example.demo;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.fluentd.logger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private FluentLogger fluentLogger;

    private final Counter messageCounter;

    public LogController(MeterRegistry registry) {
        this.messageCounter = Counter.builder("app.messages.count")
                .description("Кількість надісланих повідомлень")
                .register(registry);
    }

    @PostMapping("/send")
    public String sendLog(@RequestBody String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("time", System.currentTimeMillis());
        data.put("msg", message);
        fluentLogger.log("msg-data", data);
        messageCounter.increment();

        return "Message sent to Kibana via Fluentd: " + message;
    }
}

