package org.taxation.liability.infrastructure.persistence;

import org.taxation.liability.model.valueObjects.Type;
import org.taxation.liability.model.valueObjects.Year;

public class LiabilityProjection {

    private String liabilityId;
    private String personId;
    private Year year;
    private Type type;

    public String getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(String liabilityId) {
        this.liabilityId = liabilityId;
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
