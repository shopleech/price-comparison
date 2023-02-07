package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.AlarmBLLDTO;
import com.shopleech.publicapi.dal.dto.AlarmDALDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public class AlarmBLLMapper {

   public List<AlarmBLLDTO> mapToDto(List<AlarmDALDTO> alarms) {
      return alarms.stream()
              .map(this::mapToDto).collect(Collectors.toList());
   }

   public AlarmBLLDTO mapToDto(AlarmDALDTO c) {
      return new AlarmBLLDTO(
              c.getId(),
              c.getAlarmTypeCode(),
              c.getMinValue(),
              c.getMaxValue(),
              c.getName()
      );
   }

   public AlarmDALDTO mapToEntity(AlarmBLLDTO newAlarm) {
      AlarmDALDTO entity = new AlarmDALDTO();
      entity.setId(newAlarm.getId());
      entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
      entity.setMinValue(newAlarm.getMinValue());
      entity.setMaxValue(newAlarm.getMaxValue());
      entity.setName(newAlarm.getName());
      return entity;
   }
}
