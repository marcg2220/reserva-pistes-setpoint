package com.setpoint.reservapistes.modules.usuaris.domain;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainException;
import com.setpoint.reservapistes.modules.shared.values.date.SharedDateTime;
import com.setpoint.reservapistes.modules.usuaris.domain.documentidentitat.DocumentIdentitat;
import com.setpoint.reservapistes.modules.usuaris.domain.email.Email;
import com.setpoint.reservapistes.modules.usuaris.domain.nivell.Nivell;

import java.util.Objects;
import java.util.Optional;

public class Usuari extends AggregateRoot {

    //Immutable - Required
    private final UsuariId id;

    //Mutable - Required
    private String nom;
    private String cognoms;
    private DocumentIdentitat dni;
    private Email email;
    private Nivell nivell;
    private SharedDateTime dataAlta;

    //Mutable
    private SharedDateTime dataBaixa;


    public Usuari(final UsuariId id) {
        this.id = id;
    }

    public static Usuari create(
            final String id,
            final String nom,
            final String cognoms,
            final String dni,
            final String email,
            final String nivell,
            final String dataAlta
    ) {
        final Usuari usuari = new Usuari(UsuariId.create(id));
        usuari.changeNom(nom);
        usuari.changeCognoms(cognoms);
        usuari.changeDni(dni);
        usuari.changeEmail(email);
        usuari.changeNivell(nivell);
        usuari.changeDataAlta(dataAlta);

        return usuari;
    }

    private void changeNom(final String nom) {
        this.nom = Optional.ofNullable(nom).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari")
                .withField("nom")
                .asRequiredFieldException());
    }

    private void changeCognoms(final String cognoms) {
        this.cognoms = Optional.ofNullable(cognoms).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari").
                withField("cognoms")
                .asRequiredFieldException());
    }

    private void changeDni(final String dni) {
        this.dni = Optional.ofNullable(dni).map(DocumentIdentitat::create).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari")
                .withField("dni")
                .asRequiredFieldException());
    }

    private void changeEmail(final String email) {
        this.email = Optional.ofNullable(email).map(Email::create).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari")
                .withField("email")
                .asRequiredFieldException());
    }

    private void changeNivell(final String nivell) {
        this.nivell = Optional.ofNullable(nivell).map(Nivell::fromNom).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari")
                .withField("nivell")
                .asRequiredFieldException());
    }

    private void changeDataAlta(final String dataAlta) {
        this.dataAlta = Optional.ofNullable(dataAlta).map(SharedDateTime::create).orElseThrow(() -> DomainException.builder()
                .withEntity("Usuari")
                .withField("dataAlta")
                .asRequiredFieldException());
    }

    private void changeDataBaixa(final String dataBaixa) {
        this.dataBaixa = Optional.ofNullable(dataBaixa)
                .map(SharedDateTime::create)
                .orElse(null);
    }

    public UsuariId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public DocumentIdentitat getDni() {
        return dni;
    }

    public Email getEmail() {
        return email;
    }

    public Nivell getNivell() {
        return nivell;
    }

    public SharedDateTime getDataAlta() {
        return dataAlta;
    }

    public Optional<SharedDateTime> getDataBaixa() {
        return Optional.ofNullable(dataBaixa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuari usuari = (Usuari) o;
        return Objects.equals(dni, usuari.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, cognoms, dni, email, nivell);
    }
}
