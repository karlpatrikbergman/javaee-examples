package se.patrikbergman.java.ee.examples.interceptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.patrikbergman.java.ee.examples.interceptor.ejb.BandService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class LookupBand_IT {

	private BandService bandService;
	private Context context;

	@Before
	public void setup() throws NamingException, IOException {
		context = new InitialContext(readPropertiesFromFile());
		final String jndiName = "BandBean#se.patrikbergman.java.ee.examples.interceptor.ejb.BandService";
		bandService = (BandService) context.lookup(jndiName);
		assertNotNull(bandService);
	}

	@Test
	public void lookupBandService() {
		final String response = bandService.hello("Bruce Dickinson");
		assertNotNull(response);
		System.out.println(response);
	}

	@After
	public void after() throws NamingException {
		context.close();
	}

	private Properties readPropertiesFromFile() throws IOException {
		final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("jndi.properties");
		Properties properties = new Properties();
		properties.load(input);
		return properties;
	}
}
