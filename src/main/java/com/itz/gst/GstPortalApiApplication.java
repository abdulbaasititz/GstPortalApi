package com.itz.gst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class GstPortalApiApplication {
    @Value("${spring.date.time.zone}")
    private String timeZone;

    public static void main(String[] args) {
        SpringApplication.run(GstPortalApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }

}

@RestController()
@RequestMapping("/api/v1")
class PingController {

    @Value("${spring.credential.security-check}")
    private Boolean securityCheck;

    @GetMapping("/ping")
    public ResponseEntity<?> pingTest() {
        Map<String, Object> statusOb = new HashMap<>();
        String userDirectory = Paths.get("").toAbsolutePath().toString();
        statusOb.put("ping", 1);
        statusOb.put("userDirectory", userDirectory);
        statusOb.put("securityCheck", securityCheck);
        return new ResponseEntity<>(statusOb, HttpStatus.OK);
    }
}