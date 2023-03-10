package org.example.repository.db;

import com.google.gson.Gson;
import org.example.helper.PropertieHelper;
import org.example.model.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBMenager<T> {
    T entity;
    private Connection connection;


    public DBMenager(T entity) {
        this.entity = entity;
    }


    public Connection getConnection() {

        Properties prop = new Properties();
        // set db properties
        prop.setProperty("user", PropertieHelper.load().getProperty("config.db.user"));
        prop.setProperty("password", PropertieHelper.load().getProperty("config.db.password"));
        String url = PropertieHelper.load().getProperty("config.db.url");
        try {
            // get db connection
            return connection = DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> select(String query) {
        this.getConnection();
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            return extractResult(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<T> extractResult(ResultSet resultSet) throws SQLException {
        List<T> objs = new ArrayList<T>();
        while (resultSet.next()) {
            try {
                T o = (T) this.entity.getClass().getConstructor(null).newInstance(null);
                Class<?> clazz = o.getClass();

                for (Method methdo : clazz.getDeclaredMethods()) {
                    String methodName = methdo.getName();
                    String nameFormmater = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

                    if (methdo.getName().contains("set")) {
                        try {

                            Method outSide = o.getClass().getMethod(methodName.replace("set", "get"), null);
                            Object arg = resultSet.getObject(nameFormmater);
                            switch (outSide.getReturnType().getName()){
                                case "java.lang.String":
                                    arg = (String) arg;
                                    break;
                                default:
                                    arg = new Gson().fromJson(arg == null ? null : arg.toString(), outSide.getReturnType());
                                    break;
                            }

                            o.getClass().getMethod(methodName, outSide.getReturnType()).invoke(o, arg);

                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                objs.add(o);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }


        }
        return objs;
    }

    public int update(Object obj) {

        String dbName = obj.getClass().getName().replace("model.", "").toLowerCase();
        String set = "";
        String id = "";

        for (Method declaredMethod : obj.getClass().getDeclaredMethods()) {
            String methodName = declaredMethod.getName();
            String nameFormmater = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

            if (methodName.contains("get")) {

                try {
                    if (nameFormmater.contains("id")) {
                        id = declaredMethod.invoke(obj, null) + "";
                    } else {
                        set = set + ", " + nameFormmater + "='" + new Gson().toJsonTree(declaredMethod.invoke(obj, null)) + "'";
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);

                }
            }
        }

        this.getConnection();

        try {
            String query = " UPDATE public." + dbName.replace("org.example.", "") + " SET ?1 WHERE id = ?2;"
                    .replace("?1", set.substring(2))
                    .replace("?2", id);

            PreparedStatement statement = this.connection.prepareStatement(
                    query.replaceAll("'null'", "null")
                            .replaceAll("'\"", "'").replaceAll("\"'", "'")
            );

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int insert(T obj) {

        String dbName = obj.getClass().getName().replace("model.", "").toLowerCase();
        String collum = "";
        String value = "";

        for (Method declaredMethod : obj.getClass().getDeclaredMethods()) {
            String methodName = declaredMethod.getName();
            String nameFormmater = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

            try {
                if (methodName.contains("get")) {
                    Object a = declaredMethod.invoke(obj, null);
                    if (a != null) {
                        collum = collum + ", " + nameFormmater;
                        value = value + ", '" + a + "'";
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);

            }
        }

        this.getConnection();

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO public.?0 (?1) VALUES (?2);"
                            .replace("?0", dbName.replace("org.example.", ""))
                            .replace("?1", collum.substring(2))
                            .replace("?2", value.substring(2)));

            return statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * @param obj
     * @return Number of lines changed.
     */
    public int delete(Object obj) {
        this.getConnection();
        String dbName = obj.getClass().getName().replace("model.", "").toLowerCase();
        String id;
        try {
            id = "" + obj.getClass().getMethod("getId").invoke(obj, null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.connection.prepareStatement(
                    "DELETE FROM public.?1 where id=".replace("?1", dbName) + id
            );
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
