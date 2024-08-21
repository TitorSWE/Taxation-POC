package org.taxation.liability.infrastructure.resources.response;

import org.taxation.liability.model.valueObjects.Type;

public class SetLiabilityResponse extends Response{

    private String message;
    private String personId;
    private String liabilityId;
    private Type type;

    public SetLiabilityResponse(String personDeclaredSuccessfully, String personId, Type type) {
        this.setMessage(personDeclaredSuccessfully);
        this.setPersonId(personId);
        this.setType(type);
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
