package se.patrikbergman.java.ee.examples.interceptor.ejb;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Log {

	@Inject
	private Logger log;

	@AroundInvoke
	protected Object protocolInvocation(final InvocationContext ic) throws Exception {
		final String target = (ic.getTarget() != null) ? ic.getTarget().getClass().getSimpleName() : "";
		final String method = (ic.getMethod() != null) ? ic.getMethod().getName() : "";

		StringBuilder logString = new StringBuilder();
		logString.append("Invoked ");
		logString.append(target);
		logString.append(".");
		logString.append(method);
		logString.append("(");

		if(ic.getParameters() != null) {
			Object parameters[] = ic.getParameters();
			for(int i = 0; i<parameters.length; i++) {
				logString.append(parameters[i].toString());
				String s = (i+1<parameters.length) ? ", " : ")";
				logString.append(s);
			}
		}
//		logString.append(")");

		log.info(logString.toString());

		return ic.proceed();
	}
}
