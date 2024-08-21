package org.taxation.liability.model;

import org.taxation.liability.model.valueObjects.Type;
import org.taxation.liability.model.valueObjects.Year;
import org.taxation.liability.model.valueObjects.SocialSecurityNumber;

public class PersonState {
    final private SocialSecurityNumber socialSecurityNumber;
    final private Year arrivalYear;
    final private Liability liability;


    public PersonState(SocialSecurityNumber socialSecurityNumber, Year year, Liability liability){
        this.arrivalYear = year;
        this.socialSecurityNumber = socialSecurityNumber;
        this.liability = liability;
    }

    public PersonState createPerson(SocialSecurityNumber socialSecurityNumber, Year year) {
        return new PersonState(socialSecurityNumber, year, this.liability);
    }

    public PersonState createLiability(String liabilityId, String personId, Year year){
        return new PersonState(socialSecurityNumber, arrivalYear, liability.createLiability(liabilityId, personId, year));
    }

    public PersonState defineLiabilityType(String personId, Type type){
        return new PersonState(socialSecurityNumber, arrivalYear, liability.defineLiability(type));
    }

    public Liability getLiability() {
        return liability;
    }

    public Year getArrivalYear() {
        return arrivalYear;
    }

    public SocialSecurityNumber getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public boolean equals(Object obj){

        // Cast obj to PersonState if possible
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final PersonState other = (PersonState) obj;

        // Compare each member
        if (!this.liability.equals(other.getLiability())) return false;
        if (!this.socialSecurityNumber.equals(other.getSocialSecurityNumber())) return false;
        if (!this.arrivalYear.equals(other.getArrivalYear())) return false;

        return true;
    }
}
