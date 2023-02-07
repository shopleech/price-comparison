package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.AlarmService;
import com.shopleech.publicapi.dto.v1.AlarmDTO;
import com.shopleech.publicapi.dto.v1.mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@RestController
@RequestMapping("/api/v1/alarm")
@RequiredArgsConstructor
public class AlarmController {

   private AlarmService alarmService;

   private AlarmMapper alarmMapper;

   @GetMapping("/{id}")
   public ResponseEntity<AlarmDTO> getById(@PathVariable(value = "id") Integer id) {
      return ResponseEntity.ok(alarmMapper.mapToDto(alarmService.get(id)));
   }
}
