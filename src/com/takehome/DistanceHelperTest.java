package com.takehome;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DistanceHelperTest {

    @Test
    public void checkDistanceBetweenKnownPoints() {
        Location dublinOffice = new Location("Dublin office", 53.339428,-6.257664);
        Location hyd = new Location("Hyderabad", 17.3850, 78.4867);
        double distance = DistanceHelper.distance(dublinOffice.latitude, dublinOffice.longitude, hyd.latitude, hyd.longitude, "K");
        double knownDistance = 8120.12;
        assertEquals(knownDistance, distance, 1.0);
    }
}