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
    @Autowired
    private ProductMapper productMapper;

    public List<AlarmDTO> mapToDto(List<Alarm> alarms) {
        return alarms.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public AlarmDTO mapToDto(Alarm c) {
        var dto = new AlarmDTO();
        dto.setId(c.getId());
        try {
            dto.setProductId(c.getProduct().getId());
            dto.setProduct(productMapper.mapToDto(
                    productService.get(c.getProduct().getId())));
        } catch (Exception e) {
            logger.error("alarm mapper failed");
        }
        dto.setAlarmTypeCode(c.getAlarmTypeCode());
        dto.setMinValue(c.getMinValue());
        dto.setMaxValue(c.getMaxValue());
        dto.setName(c.getName());

        return dto;
    }

    public Alarm mapToEntity(AlarmDTO newAlarm) {
        Alarm entity = new Alarm();
        entity.setId(newAlarm.getId());
        entity.setAlarmTypeCode(newAlarm.getAlarmTypeCode());
        entity.setCustomer(userService.getCurrentUser().getCustomer());
        try {
            entity.setProduct(productService.get(newAlarm.getProductId()));
        } catch (Exception e) {
            logger.error("alarm mapper failed");
        }
        entity.setMinValue(newAlarm.getMinValue());
        entity.setMaxValue(newAlarm.getMaxValue());
        entity.setName(newAlarm.getName());

        return entity;
    }

}
