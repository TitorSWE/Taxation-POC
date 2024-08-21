package org.taxation.liability.infrastructure.persistence;

import org.taxation.liability.model.valueObjects.SocialSecurityNumber;
import org.taxation.liability.model.valueObjects.Year;
import org.taxation.tools.Persistence.ProjectedEntity;

public class ProjectedPerson implements ProjectedEntity {

    private String id;
    private SocialSecurityNumber socialSecurityNumber;
    private Year arrivalYear;
    private ProjectedLiability projectedLiability;

    public ProjectedPerson() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String personId) {
        this.id = personId;
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

    public ProjectedLiability getLiability() {
        return projectedLiability;
    }

    public void setLiability(ProjectedLiability liability) {
        this.projectedLiability = liability;
    }
}
