package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IAlarmService;
import com.shopleech.publicapi.dal.repository.AlarmRepository;
import com.shopleech.publicapi.domain.Alarm;
import com.shopleech.publicapi.domain.Watchlist;
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
    @Autowired
    protected UserService userService;

    @Override
    public Alarm add(Alarm data) throws Exception {
        var check = alarmRepository.findByProduct(
                data.getCustomer().getId(), data.getProduct().getId());

        if (check != null) {
            throw new Exception("alarm item exists");
        }

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
        var customerId = userService.getCurrentUser().getCustomer().getId();

        return alarmRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Alarm update(Integer id, Alarm alarm) {
        return alarm;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        alarmRepository.deleteById(id);

        var item = alarmRepository.findById(id);
        if (item.isPresent()) {
            throw new Exception("alarm removal failed");
        }

        return id;
    }
}
