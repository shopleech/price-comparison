package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.UserService;
import com.shopleech.publicapi.domain.Alarm;
import com.shopleech.publicapi.dto.v1.AlarmDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Component
public class AlarmMapper {
    Logger logger = LoggerFactory.getLogger(AlarmMapper.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public List<AlarmDTO> mapToDto(List<Alarm> alarms) {
        return alarms.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AlarmDTO mapToDto(Alarm c) {
        return new AlarmDTO(
                c.getId(),
                c.getProduct().getId(),
                c.getAlarmTypeCode(),
                c.getMinValue(),
                c.getMaxValue(),
                c.getName()
        );
    }

    public Alarm mapToEntity(AlarmDTO newAlarm) throws Exception {
        Alarm entity = new Alarm();
        entity.setId(newAlarm.getId());
        entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
        entity.setCustomer(userService.getCurrentUser().getCustomer());
        entity.setProduct(productService.get(newAlarm.getProductId()));
        entity.setMinValue(newAlarm.getMinValue());
        entity.setMaxValue(newAlarm.getMaxValue());
        entity.setName(newAlarm.getName());

        return entity;
    }

}
