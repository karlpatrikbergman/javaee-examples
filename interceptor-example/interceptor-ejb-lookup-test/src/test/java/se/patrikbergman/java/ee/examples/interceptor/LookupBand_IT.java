package se.patrikbergman.java.ee.examples.interceptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.patrikbergman.java.ee.examples.interceptor.ejb.BandBean;
import se.patrikbergman.java.ee.examples.interceptor.ejb.BandService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static org.junit.Assert.*;

public class LookupBand_IT {

    private BandService bandService;
    private Context context;

    @Before
    public void setup() {
        try {
            Properties jndiProps = readPropertiesFromFile();
            context = new InitialContext(jndiProps);
            String jndiName = "BandBean#se.patrikbergman.java.ee.examples.interceptor.ejb.BandService";
            bandService = (BandService) context.lookup(jndiName);
            assertNotNull(bandService);
        } catch (NamingException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void lookupBandService() {
        String response = bandService.hello("Bruce Dickinson");
        assertNotNull(response);
        System.out.println(response);
    }

    @After
    public void after() {
        try {
            context.close();
            bandService = null;
        } catch (NamingException e) {
            fail(e.getMessage());
        }
    }

    private Properties readPropertiesFromFile() {
        Properties properties = new Properties();
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("jndi.properties");
            properties.load(input);
        } catch (IOException e) {
            fail(e.getMessage());
        }
        return properties;
    }
}
