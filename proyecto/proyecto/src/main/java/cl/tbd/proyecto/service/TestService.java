package cl.tbd.proyecto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Service
public class TestService {
    public String HelloWorld() {
        return "Hello, world";
    }
}
