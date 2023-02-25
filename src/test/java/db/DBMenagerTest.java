package db;

import org.example.repository.db.DBMenager;
import org.junit.Test;

public class DBMenagerTest {

    @Test
    public void executeQueryTest() {
        DBMenager dbMenager = null;

        try {
            dbMenager = new DBMenager<model.Test>(new model.Test());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        model.Test a = (model.Test) dbMenager.select("select * from test");


    }

    @Test
    public void updateDataTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(0);
        s.setName("insert data test");

        try {
            dbMenager = new DBMenager<model.Test>(new model.Test());
            dbMenager.update(s);
            var b = dbMenager.select("select * from test");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insertTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(1);
        s.setName("insert data test");

        try {
            dbMenager = new DBMenager<model.Test>(new model.Test());
            dbMenager.insert(s);
            model.Test b =(model.Test) dbMenager.select("select * from test where id = 1");
            b.getTest();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void deleteTest(){
        DBMenager dbMenager = null;
        var s = new model.Test();
        s.setTest(true);
        s.setId(1);
        s.setName("insert data test");

        try {
            dbMenager = new DBMenager<model.Test>(new model.Test());
            dbMenager.delete(s);
            model.Test b =(model.Test) dbMenager.select("select * from test where id = 1");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
