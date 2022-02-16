package com.setpoint.reservapistes.modules.usuaris.domain.nivell;

import java.util.Arrays;

public enum Nivell {
    PRINCIPIANT("Principant"),
    INTERMIG("Intermig"),
    AVANCAT("Avançat");

    private final String descripcio;

    private Nivell(final String descripcio) {
        this.descripcio = descripcio;
    }

    public static Boolean isValid(final String nivell) {
        return Arrays.stream(Nivell.values()).anyMatch(nivell1 -> nivell1.name().equals(nivell));
    }

    public String getDescripcio() { return descripcio; }

    public static Nivell fromNom(String nom) {
        for (Nivell nivell : values()) {
            if (nivell.name().equals(nom)) {
                return nivell;
            }
        }
        throw new NivellInvalid();
    }
}
