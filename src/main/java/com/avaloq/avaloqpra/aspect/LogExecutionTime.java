package com.avaloq.avaloqpra.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // @Component needed to detect @Aspect!
@Aspect
public class LogExecutionTime {

  private static final Logger log = LoggerFactory.getLogger(LogExecutionTime.class);

  //@Pointcut("execution(* transfer(..))") // ?
  // private void anyOldTransfer() {}

  @Around(value = "@annotation(annotation)")
  public Object logTime(ProceedingJoinPoint joinPoint, final LogExecTime annotation)
      throws Throwable {

    //Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getClass());
    Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());

    final long startMillis = System.currentTimeMillis();
    try {
      //System.out.println("Starting timed operation");
      //logger.debug("Starting timed operation");
      logger.debug(joinPoint.getSignature().getName()+" started");
      final Object retVal = joinPoint.proceed();
      return retVal;

    } finally {
      final long duration = System.currentTimeMillis() - startMillis;
      //System.out.println("Call to " + joinPoint.getSignature() + " took " + duration + " ms");
      //logger.debug("Call to " + joinPoint.getSignature() + " took " + duration + " ms");
      logger.debug(joinPoint.getSignature().getName()+" finished after "+duration+ " ms");
    }

    /*
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object retVal = joinPoint.proceed();

    stopWatch.stop();

    StringBuffer logMessage = new StringBuffer();
    logMessage.append(joinPoint.getTarget().getClass().getName());
    logMessage.append(".");
    logMessage.append(joinPoint.getSignature().getName());
    logMessage.append("(");
    // append args
    Object[] args = joinPoint.getArgs();
    for (int i = 0; i < args.length; i++) {
      logMessage.append(args[i]).append(",");
    }
    if (args.length > 0) {
      logMessage.deleteCharAt(logMessage.length() - 1);
    }

    logMessage.append(")");
    logMessage.append(" execution time: ");
    logMessage.append(stopWatch.getTotalTimeMillis());
    logMessage.append(" ms");
    log.info(logMessage.toString());

    return retVal;
    */
  }
}
