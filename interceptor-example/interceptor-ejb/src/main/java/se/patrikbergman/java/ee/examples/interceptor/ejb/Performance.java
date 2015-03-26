package se.patrikbergman.java.ee.examples.interceptor.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Performance {
    protected String greeting;
    private static final String trace = Performance.class.getSimpleName();
	private static Logger log = LoggerFactory.getLogger(Performance.class);

    @AroundInvoke
    public Object measureTime(InvocationContext ctx) throws Exception {
        long beforeTime = System.currentTimeMillis();
        Object obj = null;
        String targetName = ctx.getTarget().getClass().getSimpleName();
        String methodName = ctx.getMethod().getName();
        try {
            obj = ctx.proceed();
            return obj;
        }
        finally {
            long executionTime = System.currentTimeMillis() - beforeTime;
            log.info(String.format("Execution time for %s.%s() is %d", targetName, methodName, executionTime));
        }
    }
}
