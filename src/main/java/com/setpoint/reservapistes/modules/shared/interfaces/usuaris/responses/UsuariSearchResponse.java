package com.setpoint.reservapistes.modules.shared.interfaces.usuaris.responses;

import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.time.OffsetDateTime;

public interface UsuariSearchResponse extends Response {
    String getId();

    String getNom();

    String getCognoms();

    String getDni();

    String getEmail();

    String getNivell();

    OffsetDateTime getDataAlta();

    OffsetDateTime getDataBaixa();

    static UsuariSearchResponse create(
            final String id,
            final String nom,
            final String cognoms,
            final String dni,
            final String email,
            final String nivell,
            final OffsetDateTime dataAlta,
            final OffsetDateTime dataBaixa
    ) {
        return new UsuariSearchResponse() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getNom() {
                return nom;
            }

            @Override
            public String getCognoms() {
                return cognoms;
            }

            @Override
            public String getDni() {
                return dni;
            }

            @Override
            public String getEmail() {
                return email;
            }

            @Override
            public String getNivell() {
                return nivell;
            }

            @Override
            public OffsetDateTime getDataAlta() {
                return dataAlta;
            }

            @Override
            public OffsetDateTime getDataBaixa() {
                return dataBaixa;
            }
        };
    }
}
