package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AlarmDALDTO;
import com.shopleech.publicapi.dal.mapper.AlarmDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class AlarmRepositoryImpl implements AlarmRepositoryCustom {

    private AlarmRepository alarmRepository;

    private AlarmDALMapper alarmDALMapper;

    @Override
    public void addAlarm(AlarmDALDTO alarm) {
        // alarmRepository.add(alarmDALMapper.mapToEntity(alarm));
    }

    @Override
    public AlarmDALDTO getAlarmById(Integer id) {
        return alarmDALMapper.mapToDto(alarmRepository.getReferenceById(id));
    }

    @Override
    public List<AlarmDALDTO> getAllAlarms() {
        return alarmDALMapper.mapToDto(alarmRepository.findAll());
    }

    @Override
    public AlarmDALDTO getAlarmByName(String name) {
        return null;
    }
}
