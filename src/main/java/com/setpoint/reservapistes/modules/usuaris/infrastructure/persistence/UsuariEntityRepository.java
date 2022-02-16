package com.setpoint.reservapistes.modules.usuaris.infrastructure.persistence;

import com.setpoint.reservapistes.modules.shared.core.repositories.domain.filters.Filters;
import com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure.DomainMapper;
import com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure.DomainRepositoryAdapter;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import com.setpoint.reservapistes.modules.usuaris.domain.Usuari;
import com.setpoint.reservapistes.modules.usuaris.domain.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariEntityRepository extends DomainRepositoryAdapter<Usuari, UsuariEntity> implements UsuariRepository {
    private final UsuariJpaRepository usuariJpaRepository;
    private final UsuariDomainMapper usuariDomainMapper;

    public UsuariEntityRepository(UsuariJpaRepository usuariJpaRepository, UsuariDomainMapper usuariDomainMapper) {
        this.usuariJpaRepository = usuariJpaRepository;
        this.usuariDomainMapper = usuariDomainMapper;
    }

    @Override
    protected JpaRepository<UsuariEntity, String> repository() {
        return usuariJpaRepository;
    }

    @Override
    protected JpaSpecificationExecutor<UsuariEntity> specificationExecutor() {
        return usuariJpaRepository;
    }

    @Override
    protected DomainMapper<Usuari, UsuariEntity> domainMapper() {
        return usuariDomainMapper;
    }

    @Override
    protected <R extends Response> Optional<R> findTemplate(String id, Class<R> clazz) {
        return this.usuariJpaRepository.findById(id, clazz);
    }

    @Override
    protected List<Specification<UsuariEntity>> specificationFromFiltersTemplate(Filters filters) {
        return null;
    }
}
