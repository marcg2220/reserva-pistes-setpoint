package com.setpoint.reservapistes.modules.shared.core.exceptions.domain;

public class DomainExceptionBuilder {

    private String entity;
    private String field;
    private String apiMessage;
    private String uiMessage;

    public DomainExceptionBuilder withEntity(final String entity) {
        this.entity = entity;
        return this;
    }

    public DomainExceptionBuilder withField(final String field) {
        this.field = field;
        return this;
    }

    public DomainExceptionBuilder withApiMessage(final String apiMessage) {
        this.apiMessage = apiMessage;
        return this;
    }

    public DomainExceptionBuilder withUiMessage(final String uiMessage) {
        this.uiMessage = uiMessage;
        return this;
    }

    public DomainException asNotFoundException() {
        this.apiMessage = String.format("The %s was not found.", entity);
        this.uiMessage = String.format("No s'ha trobat cap %s amb aquest identificador.", entity);
        return this.asProtocolException();
    }

    public DomainException asAlreadyExists() {
        this.apiMessage = String.format("Already exists %s with same %s", entity, field);
        this.uiMessage = String.format("Ja existeix un/a %s amb aquest %s.", entity, field);
        return this.asProtocolException();
    }

    public DomainException asInvalidFieldException() {
        this.apiMessage = String.format("The value of %s is not valid.", field);
        this.uiMessage = String.format("El valor del camp %s no és valid.", field);
        return this.asFormatException();
    }

    public DomainException asRequiredFieldException() {
        this.apiMessage = String.format("");
        this.uiMessage = String.format("");
        return this.asBusinessException();
    }

    private DomainException asProtocolException() {
        return new DomainProtocolException() {

            @Override
            public String entityName() {
                return entity != null ? entity : super.entityName();
            }

            @Override
            public String entityField() {
                return field != null ? field : super.entityField();
            }

            @Override
            public String apiMessage() {
                return apiMessage;
            }

            @Override
            public String uiMessage() {
                return uiMessage != null ? uiMessage : super.uiMessage();
            }
        };
    }

    private DomainException asFormatException() {
        return new DomainFormatException() {
            @Override
            public String entityName() {
                return entity != null ? entity : super.entityName();
            }

            @Override
            public String entityField() {
                return field != null ? field : super.entityField();
            }

            @Override
            public String apiMessage() {
                return apiMessage;
            }

            @Override
            public String uiMessage() {
                return uiMessage != null ? uiMessage : super.uiMessage();
            }
        };
    }

    private DomainException asBusinessException() {
        return new DomainBusinessException() {
            @Override
            public String entityName() {
                return entity != null ? entity : super.entityName();
            }

            @Override
            public String entityField() {
                return field != null ? field : super.entityField();
            }

            @Override
            public String apiMessage() {
                return apiMessage;
            }

            @Override
            public String uiMessage() {
                return uiMessage != null ? uiMessage : super.uiMessage();
            }
        };
    }
}
