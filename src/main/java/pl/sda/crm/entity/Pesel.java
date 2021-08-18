package pl.sda.crm.entity;

import java.util.Objects;

import static pl.sda.crm.util.ArgumentValidator.validate;

public class Pesel {

    private String value;

    public Pesel(String value) {
        validate(value != null && value.matches("\\d{11}"), "pesel is invalid: " + value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesel pesel = (Pesel) o;
        return value.equals(pesel.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
