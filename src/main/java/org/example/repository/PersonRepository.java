package org.example.repository;

import org.example.model.Person;
import org.example.repository.db.DBMenager;

public class PersonRepository{

    DBMenager dbMenager = new DBMenager<>(new Person());

    public void save(Person person) {

        if (person != null) {
            dbMenager.insert(person);
        }
    }

    public Person findById(int id) {
        Person obj = (Person) dbMenager.select("select * from person where id="+id).get(0);
        return obj;
    }

    public Person findByEmail(String email) {
        return  (Person) dbMenager.select("select * from person where email="+email).get(0);
    };

    public Person findAllByPerson(Person person){
        try {
            String query = "select * from person where " +
                    "email='"+person.getEmail()+"'" +
                    " or cpf='"+person.getCpf() + "'" +
                    "or id="+person.getId();
        Person obj = (Person) dbMenager.select(query).get(0);
        return obj;
        } catch (Exception e) {
          return null;
        }
    }

    public Boolean update(Person person) {
       int rows =  this.dbMenager.update(person);
       return rows >= 1;
    }

    public Boolean deleteByPerson(Person person) {
       return  this.dbMenager.delete(person) == 1;
    }


}
