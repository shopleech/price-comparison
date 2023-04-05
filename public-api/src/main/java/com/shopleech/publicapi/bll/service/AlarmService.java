package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IAlarmService;
import com.shopleech.publicapi.dal.repository.AlarmRepository;
import com.shopleech.publicapi.domain.Alarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
public class AlarmService implements IAlarmService {
    Logger logger = LoggerFactory.getLogger(AlarmService.class);

    @Autowired
    protected AlarmRepository alarmRepository;

    @Override
    public Alarm add(Alarm data) {
        return alarmRepository.save(data);
    }

    @Override
    public Alarm get(int id) throws Exception {
        var item = alarmRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("alarm not found");
        }

        return item.get();
    }

    @Override
    public List<Alarm> getAll() {
        return alarmRepository.findAll();
    }

    @Override
    public Alarm update(Integer id, Alarm alarm) {
        return alarm;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }
}
