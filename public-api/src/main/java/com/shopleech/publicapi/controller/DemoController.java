package com.shopleech.publicapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@RestController
@RequestMapping("/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello");
    }

}
