package org.taxation.liability.model.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.taxation.liability.model.valueObjects.Type;

public class SetLiabilityType {

    @TargetAggregateIdentifier
    private String personId;
    private Type type;

    public SetLiabilityType() {}

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
