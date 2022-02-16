package com.setpoint.reservapistes.modules.usuaris.infrastructure.persistence;

import com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure.DomainMapperAdapter;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import com.setpoint.reservapistes.modules.shared.interfaces.usuaris.responses.UsuariSearchResponse;
import com.setpoint.reservapistes.modules.shared.values.date.SharedDateTime;
import com.setpoint.reservapistes.modules.usuaris.domain.Usuari;
import org.springframework.stereotype.Service;

@Service
public class UsuariDomainMapper extends DomainMapperAdapter<Usuari, UsuariEntity> {
    @Override
    protected Usuari mapToAggregateTemplate(UsuariEntity entity) {
        final Usuari usuari = Usuari.create(
                entity.getId(),
                entity.getNom(),
                entity.getCognoms(),
                entity.getDni(),
                entity.getEmail(),
                entity.getNivell(),
                entity.getDataAlta().toString()
        );
        //TODO: usuari.update....

        return usuari;
    }

    @Override
    protected UsuariEntity mapToEntityTemplate(Usuari aggregate) {
        final UsuariEntity entity = new UsuariEntity();
        entity.setId(aggregate.getId().valor());
        entity.setNom(aggregate.getNom());
        entity.setCognoms(aggregate.getCognoms());
        entity.setDni(aggregate.getDni().valor());
        entity.setEmail(aggregate.getEmail().valor());
        entity.setDataAlta(aggregate.getDataAlta().value());

        entity.setDataBaixa(aggregate.getDataBaixa().map(SharedDateTime::value).orElse(null));

        return entity;
    }

    @Override
    protected Response mapToResponseTemplate(UsuariEntity entity) {
        return UsuariSearchResponse.create(
                entity.getId(),
                entity.getNom(),
                entity.getCognoms(),
                entity.getDni(),
                entity.getEmail(),
                entity.getNivell(),
                entity.getDataAlta(),
                entity.getDataBaixa()
        );
    }
}
