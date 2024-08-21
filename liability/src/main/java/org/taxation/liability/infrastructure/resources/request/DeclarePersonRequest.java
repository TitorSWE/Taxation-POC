package org.taxation.liability.infrastructure.resources.request;

import org.taxation.liability.model.valueObjects.SocialSecurityNumber;
import org.taxation.liability.model.valueObjects.Year;

public class DeclarePersonRequest {

    private Year year;
    private SocialSecurityNumber socialSecurityNumber;

    public DeclarePersonRequest(){}

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public SocialSecurityNumber getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
