package org.taxation.liability.model.events;

import org.taxation.liability.model.valueObjects.Year;
import org.taxation.liability.model.valueObjects.SocialSecurityNumber;

public class PersonDeclared {


    private String personId;
    private Year year;
    private SocialSecurityNumber socialSecurityNumber;

    public PersonDeclared(){}

    public PersonDeclared(String personId, Year year, SocialSecurityNumber socialSecurityNumber){
        this.setPersonId(personId);
        this.setArrivalYear(year);
        this.setSocialSecurityNumber(socialSecurityNumber);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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
}
