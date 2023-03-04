package org.example.service;

import org.example.repository.db.DBMenager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnectionService {
    private DBMenager<Object> dbMenager= new DBMenager<>(new Object());
    private Connection connection = dbMenager.getConnection();

    public Map<String, Object> test( String query) throws SQLException {
        PreparedStatement s =this.connection.prepareStatement(query);
        ResultSet d =s.executeQuery();
        ResultSetMetaData metaData = d.getMetaData();
        List<String> fields = new ArrayList<>();
        List<Object[]> data = new ArrayList<>();
        int count = 1;
        while (count <= metaData.getColumnCount()) {
            String name = metaData.getColumnName(count);
            fields.add(name);
             count++;
        }
        while (d.next()) {
            List<Object> dataList = new ArrayList<>();
            fields.forEach(f -> {

                try {
                    dataList.add(d.getObject(f));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            data.add(dataList.toArray());
        }
        System.out.println(d);
        data = data.stream().sorted((ant, atu) -> {
            return   ((int) ant[0]) <= ((int)atu[0])? 1:-1;
        }).toList();
        return Map.of("fields", fields, "data", data);
    }

    public static void main(String[] args) throws SQLException {
//           new DBConnectionService().test();
    System.out.println(1+1);
    }

}
