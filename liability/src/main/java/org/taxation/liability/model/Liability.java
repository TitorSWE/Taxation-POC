package org.taxation.liability.model;

import org.axonframework.modelling.command.EntityId;
import org.taxation.liability.model.valueObjects.Type;
import org.taxation.liability.model.valueObjects.Year;

public class Liability {
    @EntityId
    final private String liabilityId;
    final private String personId;
    final private Year year;
    final private Type type;

    public Liability(String liabilityId, String personId, Year year, Type type){
        this.liabilityId = liabilityId;
        this.personId = personId;
        this.year = year;
        this.type = type;
    }

    public Liability createLiability(String liabilityId, String personId, Year year){
        return new Liability(liabilityId, personId, year, Type.UNDEFINED);
    }

    public Liability defineLiability(Type type){
        return new Liability(liabilityId, personId, year, type);
    }

    public String getLiabilityId() {
        return liabilityId;
    }

    public String getPersonId() {
        return personId;
    }

    public Year getYear() {
        return year;
    }

    public Type getType() { return type; }

    @Override
    public boolean equals(Object object){
        // Cast obj to PersonState if possible
        if (object == null) return false;
        if (object.getClass() != this.getClass()) return false;
        final Liability other = (Liability) object;

        if (!this.liabilityId.equals(other.getLiabilityId())) return false;
        if (!this.personId.equals(other.getPersonId())) return false;
        if (!this.year.equals(other.year)) return false;
        if (!this.type.equals(other.getType())) return false;

        return true;
    }
}
