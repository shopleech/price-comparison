package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.dto.AlarmBLLDTO;
import com.shopleech.publicapi.dto.v1.AlarmDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AlarmMapper {

   public List<AlarmDTO> mapToDto(List<AlarmBLLDTO> alarms) {
      return alarms.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public AlarmDTO mapToDto(AlarmBLLDTO c) {
      return new AlarmDTO(
              c.getId(),
              c.getAlarmTypeCode(),
              c.getMinValue(),
              c.getMaxValue(),
              c.getName()
      );
   }

   public AlarmBLLDTO mapToEntity(AlarmDTO newAlarm) {
      AlarmBLLDTO entity = new AlarmBLLDTO();
      entity.setId(newAlarm.getId());
      entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
      entity.setMinValue(newAlarm.getMinValue());
      entity.setMaxValue(newAlarm.getMaxValue());
      entity.setName(newAlarm.getName());
      return entity;
   }

}
