package com.shopleech.publicapi.controller;

import com.shopleech.publicapi.bll.service.AlarmService;
import com.shopleech.publicapi.dto.v1.AlarmDTO;
import com.shopleech.publicapi.dto.v1.mapper.AlarmMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/alarm")
@Tag(name = "Alarm controller", description = "Endpoint")
public class AlarmController {
    Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private AlarmMapper alarmMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AlarmDTO> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(alarmMapper.mapToDto(alarmService.get(id)));
    }
}
