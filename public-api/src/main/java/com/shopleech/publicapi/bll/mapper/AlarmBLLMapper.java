package com.shopleech.publicapi.bll.mapper;

import com.shopleech.publicapi.bll.dto.AlarmBLLDTO;
import com.shopleech.publicapi.dal.dto.AlarmDALDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Component
public class AlarmBLLMapper {

//    @Autowired
//    CustomerBLLMapper customerBLLMapper;
//    @Autowired
//    ProductBLLMapper productBLLMapper;

    public List<AlarmBLLDTO> mapToDto(List<AlarmDALDTO> alarms) {
        return alarms.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AlarmBLLDTO mapToDto(AlarmDALDTO c) {

        AlarmBLLDTO dto = new AlarmBLLDTO();
        dto.setId(c.getId());
//        dto.setCustomer(customerBLLMapper.mapToDto(c.getCustomer()));
//        dto.setProduct(productBLLMapper.mapToDto(c.getProduct()));
        dto.setAlarmTypeCode(c.getAlarmTypeCode());
        dto.setMinValue(c.getMinValue());
        dto.setMaxValue(c.getMaxValue());
        dto.setName(c.getName());

        return dto;
    }

    public List<AlarmDALDTO> mapToEntity(List<AlarmBLLDTO> alarms) {
        return alarms.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public AlarmDALDTO mapToEntity(AlarmBLLDTO newAlarm) {

        AlarmDALDTO entity = new AlarmDALDTO();
        entity.setId(newAlarm.getId());
//        entity.setCustomer(customerBLLMapper.mapToEntity(newAlarm.getCustomer()));
//        entity.setProduct(productBLLMapper.mapToEntity(newAlarm.getProduct()));
        entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
        entity.setMinValue(newAlarm.getMinValue());
        entity.setMaxValue(newAlarm.getMaxValue());
        entity.setName(newAlarm.getName());

        return entity;
    }
}
