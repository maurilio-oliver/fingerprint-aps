package helper;


import org.example.helper.PropertieHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PropertiesTest {
    private static final Logger LOGGER = Logger.getLogger(Properties.class.getName());

    @Test
    public void loadPropertiesTest(){
        // get a new properties instance
        Properties properties = PropertieHelper.load();
        // Check if properties is not null
        Assert.assertNotNull(properties);

        if ((properties instanceof Properties)) {
            LOGGER.log(Level.SEVERE,"test.properties.help.error.divergencia-na-instancia-do-objeto");

        }
    }

}
