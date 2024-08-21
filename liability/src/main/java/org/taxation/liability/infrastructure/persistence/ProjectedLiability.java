package org.taxation.liability.infrastructure.persistence;

import org.taxation.liability.model.valueObjects.Type;
import org.taxation.liability.model.valueObjects.Year;

public class ProjectedLiability implements ProjectedEntity{

    private String id;
    private String personId;
    private Year year;
    private Type type;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String liabilityId) {
        this.id = liabilityId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
