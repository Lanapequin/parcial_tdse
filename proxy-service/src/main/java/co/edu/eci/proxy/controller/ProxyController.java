package co.edu.eci.proxy.controller;

import co.edu.eci.proxy.service.ActivePassiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final ActivePassiveService activePassiveService;

    @Autowired
    public ProxyController(ActivePassiveService activePassiveService) {
        this.activePassiveService = activePassiveService;
    }

    @GetMapping("/proxy/luc")
    public ResponseEntity<String> luc(@RequestParam("value") int value) {
        return delegate("/lucasseq?value=" + value);
    }

    @GetMapping("/proxy/status")
    public ResponseEntity<String> status() {
        String body = "{\"instance1\":\"" + activePassiveService.getInstance1Url() + "\"," +
                       "\"instance2\":\"" + activePassiveService.getInstance2Url() + "\"}";
        return ResponseEntity.ok(body);
    }

    private ResponseEntity<String> delegate(String path) {
        try {
            String result = activePassiveService.callWithFailover(path);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String errorJson = "{\"error\":\"" + e.getMessage() + "\"}";
            return ResponseEntity.status(503).body(errorJson);
        }
    }
}
