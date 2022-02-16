package com.setpoint.reservapistes.modules.usuaris.infrastructure.api;

import com.setpoint.reservapistes.modules.shared.core.api.infrastructure.ApiException;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandBus;
import com.setpoint.reservapistes.modules.shared.core.identifier.domain.IdentifierGenerator;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import com.setpoint.reservapistes.modules.shared.interfaces.usuaris.commands.UsuariCreateCommand;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuaris")
public class UsuariCreateController {

    private final CommandBus commandBus;
    private final IdentifierGenerator identifierGenerator;

    public UsuariCreateController(CommandBus commandBus, IdentifierGenerator identifierGenerator) {
        this.commandBus = commandBus;
        this.identifierGenerator = identifierGenerator;
    }

    @Operation(summary = "Alta d'un usuari", tags = "usuaris")
    @PostMapping
    public ResponseEntity<Response> create(@RequestBody final UsuariCreateRequest usuariCreateRequest) {
        try {
            final UsuariCreateCommand usuariCreateCommand = this.createCommand(usuariCreateRequest);
            commandBus.dispatch(usuariCreateCommand);
            return ResponseEntity.created(new URI("/usuaris/" + usuariCreateCommand.getId())).build();
        } catch (Exception e) {
            return ApiException.response(e);
        }
    }

    private UsuariCreateCommand createCommand(UsuariCreateRequest usuariCreateRequest) {
        final String id = Optional.ofNullable(usuariCreateRequest.getId()).orElse(identifierGenerator.generate().valor());
        return new UsuariCreateCommand() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getNom() {
                return usuariCreateRequest.getNom();
            }

            @Override
            public String getCognoms() {
                return usuariCreateRequest.getCognoms();
            }

            @Override
            public String getDni() {
                return usuariCreateRequest.getDni();
            }

            @Override
            public String getEmail() {
                return usuariCreateRequest.getEmail();
            }

            @Override
            public String getNivell() {
                return usuariCreateRequest.getNivell();
            }
        };
    }

}
