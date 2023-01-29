package com.shopleech.publicapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 27.01.2023
 */
@RestController
public class SampleController {
   @GetMapping("/sample")
   public String showForm() {
      return "index";
   }

}
