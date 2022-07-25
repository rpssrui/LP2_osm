package edu.ufp.inf.project;

import java.io.Serializable;
import java.util.Vector;


public enum Type implements Serializable {

    BASIC("basic"),
    ADMIN("admin");

    public String typeString;

    Type(String Type) {
        typeString = Type;
    }

    @Override
    public String toString() {
        return typeString;
    }

    /**
     * Transforma o typeString em Type
     *
     * @return type
     */
    public static Type fromString(String typeString) {
        for (Type type : Type.values()) {
            if (type.typeString.equalsIgnoreCase(typeString)) {
                return type;
            }
        }
        return null;
    }

}