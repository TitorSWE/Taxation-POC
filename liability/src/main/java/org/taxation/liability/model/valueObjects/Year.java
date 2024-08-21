package org.taxation.liability.model.valueObjects;

import org.taxation.liability.model.PersonState;

public class Year {
    private int year;

    public Year(){}

    public Year(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    @Override
    public boolean equals(Object obj){
        // Cast obj to PersonState if possible
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Year other = (Year) obj;

        return this.year == other.getYear();
    }
}
