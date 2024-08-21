package org.taxation.liability.model.events;

import org.taxation.liability.model.valueObjects.Type;

public class LiabilityTypeDefined {

    private String personId;
    private String liabilityId;
    private Type type;

    public LiabilityTypeDefined(){}

    public LiabilityTypeDefined(String personId, String liabilityId, Type type) {
        this.personId = personId;
        this.liabilityId = liabilityId;
        this.type = type;
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
