package com.setpoint.reservapistes.modules.usuaris.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(schema = "usuaris", name = "usuaris")
public class UsuariEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String cognoms;

    @Column(unique = true)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nivell;

    @Column(nullable = false)
    private OffsetDateTime dataAlta;

    private OffsetDateTime dataBaixa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivell() {
        return nivell;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public OffsetDateTime getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(OffsetDateTime dataAlta) {
        this.dataAlta = dataAlta;
    }

    public OffsetDateTime getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(OffsetDateTime dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
}
