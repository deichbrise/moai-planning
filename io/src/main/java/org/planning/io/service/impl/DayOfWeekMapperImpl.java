package org.planning.io.service.impl;

import org.planning.io.service.DayOfWeekMapper;
import org.planning.io.service.Mapper;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pascalstammer
 */
public class DayOfWeekMapperImpl implements DayOfWeekMapper {

    private Map<String, DayOfWeek> map;

    @Override
    public DayOfWeek map(String input) {
        if(map == null) {
            init();
        }
        return map.get(input);
    }

    public void init() {
        map = new HashMap<>();
        map.put("Monday", DayOfWeek.MONDAY);
        map.put("Tuesday", DayOfWeek.TUESDAY);
        map.put("Wednesday", DayOfWeek.WEDNESDAY);
        map.put("Thursday", DayOfWeek.THURSDAY);
        map.put("Friday", DayOfWeek.FRIDAY);
    }
}
