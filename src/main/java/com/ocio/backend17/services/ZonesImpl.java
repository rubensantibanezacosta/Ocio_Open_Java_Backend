package com.ocio.backend17.services;

import com.ocio.backend17.dao.ZonesDao;
import com.ocio.backend17.entities.Zones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonesImpl implements IZones {
    @Autowired
    ZonesDao zonesDao;

    @Override
    public List<Zones> getAll() {

        return (List<Zones>) zonesDao.findAll();
    }

    public void updateZoneAvgPunctuation(String id, Double punctuation) {
        if (zonesDao.existsById(id)) {
            Zones oldZone = zonesDao.findById(id).get();
            oldZone.setPunctuationavg(punctuation);
            zonesDao.save(oldZone);
        }

    }
}
