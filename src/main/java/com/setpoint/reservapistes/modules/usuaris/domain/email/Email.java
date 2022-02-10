package com.setpoint.reservapistes.modules.usuaris.domain.email;

import java.util.Objects;

public class Email {

    private String valor;

    protected Email(String valor) {
        validaEmail(valor);
        this.valor = valor;
    }

    private void validaEmail(final String valor) {
        if(valor == null || !valor.trim().isEmpty() && !valor.trim().matches("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)"))
            throw new EmailInvalid();
    }

    public static Email create(String value) {
        return new Email(value);
    }

    public String valor() { return valor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return valor.equals(email.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
