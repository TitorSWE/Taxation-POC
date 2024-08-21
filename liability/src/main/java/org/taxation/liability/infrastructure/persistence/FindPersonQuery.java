package org.taxation.liability.infrastructure.persistence;

public class FindPersonQuery {
    private String personId;

    public FindPersonQuery(String personId){
        this.setPersonId(personId);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
