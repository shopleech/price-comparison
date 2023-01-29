package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.AccountService;
import com.shopleech.publicapi.dto.v1.Account;
import com.shopleech.publicapi.dto.v1.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 21.01.2023
 */
@RestController
public class HelloController {

   @Autowired
   private AccountService accountService;

   @Autowired
   private AccountMapper modelMapper;

   @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
   public List<Account> welcome() {
      return modelMapper.mapToDto(accountService.getAll());
   }

   @GetMapping("/hello/{name:.+}")
   public String hello() {
      return "welcome";
   }

}
