package com.setpoint.reservapistes.modules.usuaris.domain.documentidentitat;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class DocumentIdentitat {
    private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]]");
    private static final String DIGIT_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String[] INVALIDS = new String[] {"00000000T", "00000001R", "99999999R"};

    private String valor;

    protected DocumentIdentitat(String dni) {
        validarDni(dni);
        this.valor = dni;
    }

    private void validarDni(final String dni) {
        if (Arrays.binarySearch(INVALIDS, dni) < 0 && REGEXP.matcher(dni).matches() && dni.charAt(8) == DIGIT_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23)) {
            throw new DniInvalid();
        }
    }

    public static DocumentIdentitat create(String dni) { return new DocumentIdentitat(dni); }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentIdentitat that = (DocumentIdentitat) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
