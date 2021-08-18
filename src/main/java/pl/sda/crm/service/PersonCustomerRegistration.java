package pl.sda.crm.service;

import org.hibernate.SessionFactory;
import pl.sda.crm.entity.Person;

import static java.util.Objects.requireNonNull;

public class PersonCustomerRegistration {

    private final SessionFactory sessionFactory;

    public PersonCustomerRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    public RegisteredCustomerId registerPerson(RegisterPersonForm form) {
        // 0. otworzenie transakcji
        final var session = sessionFactory.openSession();
        final var tx = session.beginTransaction();

        // 1. walidacja last name + pesel
        // SQL + sprawdzić wynik

        // 2. zamiana form w encje Person
        final var person = Person.from(form);

        // 3. zapisanie Person do DB
        session.save(person);

        // 4. commit & zwrócenie id
        tx.commit();
        session.close();
        return new RegisteredCustomerId(person.getId());
    }

}
