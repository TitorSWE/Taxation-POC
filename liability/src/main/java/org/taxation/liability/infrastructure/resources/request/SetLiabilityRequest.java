package org.taxation.liability.infrastructure.resources.request;

import org.taxation.liability.model.valueObjects.Type;

public class SetLiabilityRequest {
    private Type type;

    public SetLiabilityRequest() {}

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
