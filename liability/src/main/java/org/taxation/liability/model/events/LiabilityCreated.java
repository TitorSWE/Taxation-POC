package org.taxation.liability.model.events;

import org.taxation.liability.model.valueObjects.Year;

public class LiabilityCreated {
    private String liabilityId;
    private String personId;
    private Year year;

    public LiabilityCreated(){}

    public LiabilityCreated(String liabilityId, String personId, Year year){
        this.setLiabilityId(liabilityId);
        this.setPersonId(personId);
        this.setYear(year);
    }

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
}
