package org.planning.domain.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public abstract class AbstractModel implements DomainModel {

    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid( final String guid ) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractModel that = (AbstractModel) o;

        return guid != null ? guid.equals(that.guid) : that.guid == null;
    }

    @Override
    public int hashCode() {
        return guid != null ? guid.hashCode() : 0;
    }
}
