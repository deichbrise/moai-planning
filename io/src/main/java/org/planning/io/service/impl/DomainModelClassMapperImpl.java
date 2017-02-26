package org.planning.io.service.impl;

import org.planning.io.service.DomainModelClassMapper;
import org.planning.domain.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pascalstammer
 */
public class DomainModelClassMapperImpl implements DomainModelClassMapper {

    private Map<String, Class<? extends DomainModel>> index;

    @Override
    public Class<? extends DomainModel> map(String input) {
        if(index == null) {
            init();
        }
        return index.get(input);
    }

    public void init() {
        index = new HashMap<>();
        index.put("Lecture", Lecture.class);
        index.put("Day", Day.class);
        index.put("Room", Room.class);
        index.put("TimeSlot", TimeSlot.class);
        index.put("Instructor", Instructor.class);
    }
}
