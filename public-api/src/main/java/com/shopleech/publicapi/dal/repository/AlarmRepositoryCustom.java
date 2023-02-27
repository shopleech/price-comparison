package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AlarmDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface AlarmRepositoryCustom {

    void addAlarm(AlarmDALDTO mapToEntity);

    AlarmDALDTO getAlarmById(Integer id);

    List<AlarmDALDTO> getAllAlarms();

    AlarmDALDTO getAlarmByName(String name);
}
