package se.patrikbergman.java.ee.examples.interceptor.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * The following producer allows us to use dependency injection to create a logger instance in every location of our
 * application like this:
 *
 * @Inject Logger log;
 *
 * Using a producer allows us to obtain information about the injection point and use this information to extract the
 * package/class name and create a new logger instance with it.
 *
 * http://www.hascode.com/2014/05/java-ee-logging-user-interaction-the-aspect-oriented-way-using-interceptors/
 */

public class LogProvider {

	@Produces
	public Logger createLogger(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName());
	}
}
