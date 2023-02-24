package org.example.helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertieHelper {
    public static Properties load(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
            return properties;
        } catch (IOException e) {
            //TODO: criar menssagem personalizada
            throw new RuntimeException(e);
        }
    }

}
