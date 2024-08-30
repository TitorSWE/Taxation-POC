package org.taxation.liability.model.events;

import org.taxation.liability.model.valueObjects.Type;
import org.taxation.liability.model.valueObjects.Year;

public class LiabilityTypeDefined {

    private String personId;
    private String liabilityId;
    private Type liabilityType;
    private Year year;

    public LiabilityTypeDefined(){}

    public LiabilityTypeDefined(String personId, String liabilityId, Type liabilityType, Year year) {
        this.personId = personId;
        this.liabilityId = liabilityId;
        this.liabilityType = liabilityType;
        this.year = year;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(String liabilityId) {
        this.liabilityId = liabilityId;
    }

    public Type getLiabilityType() {
        return liabilityType;
    }

    public void setLiabilityType(Type liabilityType) {
        this.liabilityType = liabilityType;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
