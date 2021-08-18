package pl.sda.crm.entity;

import com.neovisionaries.i18n.CountryCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String zipCode;
    private CountryCode countryCode;

    // hibernate only - don't use
    private Address() {}

    public Address(String street, String city, String zipCode, CountryCode countryCode) {
        validate(street != null && !street.isBlank(), "invalid street");
        validate(city != null && !city.isBlank(), "invalid city");
        validate(zipCode != null && zipCode.matches("\\d{1,3}-\\d{1,5}"), "invalid zip code");
        this.id = UUID.randomUUID();
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = requireNonNull(countryCode);
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id) && street.equals(address.street) && city.equals(address.city) && zipCode.equals(address.zipCode) && countryCode == address.countryCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, zipCode, countryCode);
    }
}
