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

//    @Autowired
//    CustomerDALMapper customerDALMapper;
//    @Autowired
//    ProductDALMapper productDALMapper;

    public List<AlarmDALDTO> mapToDto(List<Alarm> alarms) {
        return alarms.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AlarmDALDTO mapToDto(Alarm c) {

        AlarmDALDTO dto = new AlarmDALDTO();
        dto.setId(c.getId());
//        dto.setCustomer(customerDALMapper.mapToDto(c.getCustomer()));
//        dto.setProduct(productDALMapper.mapToDto(c.getProduct()));
        dto.setAlarmTypeCode(c.getAlarmTypeCode());
        dto.setMinValue(c.getMinValue());
        dto.setMaxValue(c.getMaxValue());
        dto.setName(c.getName());

        return dto;
    }

    public List<Alarm> mapToEntity(List<AlarmDALDTO> alarms) {
        return alarms.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Alarm mapToEntity(AlarmDALDTO newAlarm) {

        Alarm entity = new Alarm();
        entity.setId(newAlarm.getId());
//        entity.setCustomer(customerDALMapper.mapToEntity(newAlarm.getCustomer()));
//        entity.setProduct(productDALMapper.mapToEntity(newAlarm.getProduct()));
        entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
        entity.setMinValue(newAlarm.getMinValue());
        entity.setMaxValue(newAlarm.getMaxValue());
        entity.setName(newAlarm.getName());

        return entity;
    }

}
