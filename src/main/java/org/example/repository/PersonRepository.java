package org.example.repository;

import org.example.model.Person;
import org.example.repository.db.DBMenager;

public class PersonRepository{

    DBMenager dbMenager = new DBMenager<>(Person.class);

    public void save(Person person) {

        if (person != null) {
            dbMenager.insert(person);
        }
    }

    public Person findById(int id) {
        Person obj = (Person) dbMenager.select("select * from person where id="+id);
        return obj;
    }


}
