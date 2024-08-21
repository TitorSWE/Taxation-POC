package org.taxation.liability.model.valueObjects;

import java.util.Objects;

public class SocialSecurityNumber {

    private String number;

    public SocialSecurityNumber(){}

    public SocialSecurityNumber(String number) {
        this.setNumber(number);
    }

    public boolean isNumberCorrect(String number){
        // length = 13
        if (number.length() != 13) return false;

        // All digits
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Swiss country code
        return number.startsWith("756");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number){
        if (isNumberCorrect(number)) this.number = number;
        else throw new RuntimeException("the number provided is incorrect");
    }

    @Override
    public boolean equals(Object obj){
        // Cast obj to PersonState if possible
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final SocialSecurityNumber other = (SocialSecurityNumber) obj;

        return this.number.equals(other.getNumber());
    }
}
