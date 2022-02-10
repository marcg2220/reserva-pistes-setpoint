package com.setpoint.reservapistes.modules.usuaris.domain;

import com.setpoint.reservapistes.config.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.usuaris.domain.email.Email;

public class Usuari extends AggregateRoot {

    //Immutable - Required
    private final String id;

    //Mutable - Required
    private String nom;
    private String cognoms;
    //private DocumentIdentitat nif;
    private Email email;
    //private Nivell nivell;


    public Usuari(final String id) {
        this.id = id;
    }
}
