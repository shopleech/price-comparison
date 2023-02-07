package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.AlarmBLLDTO;
import com.shopleech.publicapi.bll.mapper.AlarmBLLMapper;
import com.shopleech.publicapi.dal.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
@RequiredArgsConstructor
public class AlarmService {

   protected AlarmRepository alarmRepository;

   protected AlarmBLLMapper alarmMapper;

   public void createAlarm(AlarmBLLDTO data) {
      alarmRepository.addAlarm(alarmMapper.mapToEntity(data));
   }

   public AlarmBLLDTO get(int id) {
      return alarmMapper.mapToDto(alarmRepository.getAlarmById(id));
   }

   public AlarmBLLDTO getByName(String name) {
      return alarmMapper.mapToDto(alarmRepository.getAlarmByName(name));
   }

   public List<AlarmBLLDTO> getAll() {
      return alarmMapper.mapToDto(alarmRepository.getAllAlarms());
   }
}
