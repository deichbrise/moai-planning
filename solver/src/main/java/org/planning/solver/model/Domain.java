package org.planning.solver.model;

import org.chocosolver.solver.variables.IntVar;
import org.planning.domain.model.DomainModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pascalstammer
 */
public class Domain {
    private Map<String, Entry> entryMap = new HashMap<>();

    public void put(final String guid, final Class<? extends DomainModel> domainModelClass, final IntVar intVar) {
        if(!entryMap.containsKey( guid )) {
            final Entry entry = new Entry();
            entryMap.put(guid, entry);
        }
        entryMap.get( guid ).put(domainModelClass, intVar);
    }

    public IntVar get(final String guid, final Class<? extends DomainModel> domainModelClass) {
        return entryMap.get(guid).get(domainModelClass);
    }

    /**
     * Define private access to inner wrapper class to prevent unforeseen behaviour
     */
    private class Entry {
        private Map<String, IntVar> domainModelMap = new HashMap<>();

        public void put(final Class<? extends DomainModel> domainModelClass, final IntVar intVar) {
            domainModelMap.put(domainModelClass.getName(), intVar);
        }

        public IntVar get(final Class<? extends DomainModel> domainModelClass) {
            return domainModelMap.get(domainModelClass.getName());
        }
    }
}
