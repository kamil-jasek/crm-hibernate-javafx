package pl.sda.crm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@DiscriminatorValue("PERSON") // ustala wartość w kolumnie customer_type
public class Person extends Customer {

    private String firstName;
    private String lastName;

    @Embedded // pola z klasy Pesel będą zapisane wraz z polami Person w jednej tabeli
    private Pesel pesel;

    // only for hibernate - don't use
    private Person() {
    }

    public Person(String firstName, String lastName, Pesel pesel) {
        validate(firstName != null && !firstName.isBlank(), "first name is invalid: " + firstName);
        validate(lastName != null && !lastName.isBlank(), "last name is invalid: " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = requireNonNull(pesel, "pesel is null");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Pesel getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && pesel.equals(person.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, pesel);
    }
}