package org.taxation.liability.infrastructure.resources.response;

public class DeclarePersonResponse extends Response {
    private String message;
    private String personId;
    private String liabilityId;

    public DeclarePersonResponse(String message, String personId, String liabilityId) {
        this.message = message;
        this.personId = personId;
        this.liabilityId = liabilityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    // Getters and Setters
}
