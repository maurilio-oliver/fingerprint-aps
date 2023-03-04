package db;

import org.example.model.Person;
import org.example.repository.db.DBMenager;
import org.junit.Test;

import java.util.Map;

public class DBMenagerTest {

    @Test
    public void executeQueryTest() {
        DBMenager dbMenager = null;

        dbMenager = new DBMenager<Person>(new Person());
        Person a = (Person) dbMenager.select("select * from person");


    }

    @Test
    public void updateDataTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(0);
        s.setName("insert data test");

        dbMenager = new DBMenager<model.Test>(new model.Test());
        dbMenager.update(s);
        var b = dbMenager.select("select * from test");
    }

    @Test
    public void insertTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(1);
        s.setName("insert data test");

        dbMenager = new DBMenager<model.Test>(new model.Test());
        dbMenager.insert(s);
        model.Test b =(model.Test) dbMenager.select("select * from test where id = 1");
        b.getTest();

    }

    @Test
    public void deleteTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(1);
        s.setName("insert data test");

        dbMenager = new DBMenager<model.Test>(new model.Test());
        dbMenager.delete(s);
        model.Test b =(model.Test) dbMenager.select("select * from test where id = 1");

    }

    @Test
    public void update(){
        Person person = new Person();
        DBMenager dbMenager = new DBMenager<Person>(person);
        person.setCpf("4004000");
        person.setId(1L);
        person.setLevel(1);
        person.setName("maurilio");
        dbMenager.update(person);

    }


}
