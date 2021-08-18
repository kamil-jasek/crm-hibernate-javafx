package pl.sda.crm.entity;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Test
    void shouldSavePersonInDatabase() {
        // given
        final var person = new Person("Jan", "Kowalski", new Pesel("92030202211"));

        // when
        final var session = factory.openSession();
        final var tx = session.beginTransaction();
        session.save(person); // dodanie encji do cache -> Map<ID, ENTITY> = person.id, person
        // poniższego nie używaj w kodzie produkcyjnym, to jest tylko do testów
        session.flush(); // zapisz encje w bazie danych
        session.clear(); // czyści cache

        // then
        // pobierz Person po jego id.
        final var readPerson = session.get(Person.class, person.getId());
        assertEquals(person, readPerson);
        tx.rollback(); // wycofanie transakcji aby jeden test nie wpływał na resztę testów
    }
}