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
        // Check if this properties variable is instence of Propertie class
        Assert.assertTrue((properties instanceof Properties));
    }

    @Test
    public void valuesPropertiesTest() {
        Properties properties = PropertieHelper.load();

        // check if properties value is not equals
        Assert.assertNotEquals(20, properties.getProperty("test.value.not-equals"));
        // check if properties value is equals
        Assert.assertEquals(true, Boolean.valueOf(properties.getProperty("test.value.equals")));
        // check if properties value does not exist
        Assert.assertNull(properties.getProperty("test.value.not-exist"));
    }

}
