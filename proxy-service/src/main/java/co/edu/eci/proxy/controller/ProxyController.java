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
}
