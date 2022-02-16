package com.setpoint.reservapistes.modules.usuaris.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsuariJpaRepository extends JpaRepository<UsuariEntity, String>, JpaSpecificationExecutor<UsuariEntity> {
    <T> Optional<T> findById(final String id, final Class<T> clazz);
}
