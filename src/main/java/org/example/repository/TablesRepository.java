package org.example.repository;

import org.example.model.Tables;
import org.example.repository.db.DBMenager;

import java.util.List;
import java.util.Map;

public class TablesRepository {
    private DBMenager<Tables> dbMenager = new DBMenager<>(new Tables());

    public List<Tables> findAll() {
        return this.dbMenager.select("select * from tables");
    }

}
