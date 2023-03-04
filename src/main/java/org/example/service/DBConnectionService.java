package org.example.service;

import org.example.repository.db.DBMenager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBConnectionService {
    private DBMenager<Object> dbMenager= new DBMenager<>(new Object());
    private Connection connection = dbMenager.getConnection();

    /**
     *  get the data in the table
     * @param query
     * @return table related data map
     * @throws SQLException
     */
    public Map<String, Object> select( String query) throws SQLException {
        // prepare data
        PreparedStatement prepare =this.connection.prepareStatement(query);
        ResultSet resultSet =prepare.executeQuery();
        ResultSetMetaData metaData =  resultSet.getMetaData();
        // prepare data container
        List<String> fields = new ArrayList<>();
        List<Object[]> data = new ArrayList<>();
        int count = 1;
        // get fields name
        while (count <= metaData.getColumnCount()) {
            String name = metaData.getColumnName(count);
            fields.add(name);
             count++;
        }
        // get row data
        while (resultSet.next()) {
            List<Object> dataList = new ArrayList<>();
            fields.forEach(f -> {
                try {
                    dataList.add(resultSet.getObject(f));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            data.add(dataList.toArray());
        }
        // sort in ord by id
        data = data.stream().sorted((ant, atu) -> {
            return   ((int) ant[0]) >= ((int)atu[0])? 1:-1;
        }).toList();
        return Map.of("fields", fields, "data", data);
    }

}
