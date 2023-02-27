package com.shopleech.publicapi.dal.mapper;

import com.shopleech.publicapi.dal.dto.AlarmDALDTO;
import com.shopleech.publicapi.domain.Alarm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AlarmDALMapper {

    public List<AlarmDALDTO> mapToDto(List<Alarm> alarms) {
        return alarms.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AlarmDALDTO mapToDto(Alarm c) {
        return new AlarmDALDTO(
                c.getId(),
                c.getAlarmTypeCode(),
                c.getMinValue(),
                c.getMaxValue(),
                c.getName()
        );
    }

    public Alarm mapToEntity(AlarmDALDTO newAlarm) {
        Alarm entity = new Alarm();
        entity.setId(newAlarm.getId());
        entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
        entity.setMinValue(newAlarm.getMinValue());
        entity.setMaxValue(newAlarm.getMaxValue());
        entity.setName(newAlarm.getName());
        return entity;
    }

}
