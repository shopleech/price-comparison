package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Alarm;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IAlarmService {
    Alarm add(Alarm data) throws Exception;

    Alarm get(int id) throws Exception;

    List<Alarm> getAll();

    Alarm update(Integer id, Alarm alarm);

    Integer remove(Integer id);
}
