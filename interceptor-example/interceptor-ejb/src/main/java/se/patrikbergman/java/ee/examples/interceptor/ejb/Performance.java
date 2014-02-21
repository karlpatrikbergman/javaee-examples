package se.patrikbergman.java.ee.examples.interceptor.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class Performance {
    protected String greeting;
    private static final String trace = Performance.class.getSimpleName();
    private static final Logger log = Logger.getLogger(trace);

    public Performance() {
    }

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
