package org.example.repository;

import org.example.model.FingerPrint;
import org.example.model.Person;
import org.example.repository.db.DBMenager;

public class FingerprintRepository {
    DBMenager dbMenager = new DBMenager<Person>(new Person());

    public void save(FingerPrint fingerPrint) {
        this.dbMenager.insert(fingerPrint);
    }

}
