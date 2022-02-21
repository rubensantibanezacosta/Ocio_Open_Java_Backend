package com.ocio.backend17.services;

import com.ocio.backend17.entities.Zones;

import java.util.List;

public interface IZones {
    List<Zones> getAll();

    public void updateZoneAvgPunctuation(String name, Double punctuation);
}
