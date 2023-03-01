package service;

import org.example.model.Person;
import org.example.service.PersonService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonServiceTest {
    private PersonService personService = new PersonService();

    @Test
    public void createPersonTest() {
        Long cpf = new Random().nextLong(10000000000L,99999999999L );
        Person person = new Person();
        person.setName("maurilio de paulo viana de oliveira");
        person.setCpf(cpf.toString());

        this.personService.createNewPerson(person);

        Person updatedPerson = this.personService.findPersonByEmailOrCPF(person);

        Assert.assertNotNull(updatedPerson);
        Assert.assertEquals(cpf, updatedPerson.getCpf());
        Assert.assertNotNull(updatedPerson.getId());

        Logger.getLogger("maurilio").log(Level.WARNING, updatedPerson.toString());

    }
}
