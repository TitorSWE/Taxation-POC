package org.taxation.liability.model.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.taxation.liability.model.valueObjects.Year;
import org.taxation.liability.model.valueObjects.SocialSecurityNumber;

public class DeclarePerson {

    @TargetAggregateIdentifier
    private String personId;

    private String liabilityId;
    private Year year;
    private SocialSecurityNumber socialSecurityNumber;

    public DeclarePerson(){}

    public DeclarePerson(String personId, String liabilityId, Year year, SocialSecurityNumber socialSecurityNumber){
        this.personId = personId;
        this.liabilityId = liabilityId;
        this.year = year;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Year getArrivalYear() {
        return year;
    }

    public void setArrivalYear(Year year) {
        this.year = year;
    }

    public SocialSecurityNumber getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public String getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(String liabilityId) {
        this.liabilityId = liabilityId;
    }
}
