package org.taxation.liability.infrastructure.persistence;

import org.taxation.liability.model.Liability;
import org.taxation.liability.model.valueObjects.SocialSecurityNumber;
import org.taxation.liability.model.valueObjects.Year;

public class PersonProjection {

    private String personId;
    private SocialSecurityNumber socialSecurityNumber;
    private Year arrivalYear;
    private LiabilityProjection liabilityProjection;

    public PersonProjection() {}

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public SocialSecurityNumber getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Year getArrivalYear() {
        return arrivalYear;
    }

    public void setArrivalYear(Year arrivalYear) {
        this.arrivalYear = arrivalYear;
    }

    public LiabilityProjection getLiability() {
        return liabilityProjection;
    }

    public void setLiability(LiabilityProjection liability) {
        this.liabilityProjection = liability;
    }
}
