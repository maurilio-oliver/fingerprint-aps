package org.example.service;

import org.example.model.Person;
import org.example.repository.PersonRepository;

public class PersonService {
    PersonRepository personRepository = new PersonRepository();

    public Person findPersonByEmailOrCPF(Person person) {
        if (person != null  ) {
            if (person.getCpf() != null && !person.getCpf().isBlank() && !person.getCpf().isEmpty()) {
                return this.personRepository.findAllByPerson(person);
            } else if (person.getEmail()!=null && !person.getEmail().isBlank() && !person.getEmail().isEmpty()) {
                return this.personRepository.findAllByPerson(person);
            } else if (person != null && person.getId() != null) {
               return this.personRepository.findAllByPerson(person);
            } else {
                return null;
            }
        }
        return null;
    }


    public void createNewPerson(Person person) {
        this.personRepository.save(person);
    }

    public void updatePerson(Person person) {
        this.personRepository.update(person);
    }

    public void test(){
        Person ethan=new Person();
        ethan.setId(6L);
        Person test=this.findPersonByEmailOrCPF(ethan);
        System.out.println(test);
        System.out.println(test.getLevel());
    }
    public static void main(String[] arg){
        new PersonService().test();
    }
}
