package co.edu.eci.mathservice.controller;

import co.edu.eci.mathservice.model.MathResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class MathController {
    private String getInstanceId() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown-instance";
        }
    }

    @GetMapping("/lucasseq")
    public MathResponse computeLuc(@RequestParam("value") double value) {
        double result = Math.sin(Math.toRadians(value));
        return new MathResponse("Secuencia de Lucas", value, result, getInstanceId());
    }

    @GetMapping("/health")
    public String health() {
        return "UP - instance: " + getInstanceId();
    }
}
