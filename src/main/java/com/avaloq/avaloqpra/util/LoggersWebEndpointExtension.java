package com.avaloq.avaloqpra.util;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggersWebEndpointExtension extends LoggersEndpoint {

  private static final Logger log = LoggerFactory.getLogger(LoggersUtil.class);

  private static final String PACKAGE_NAME = "com.avaloq.avaloqpra";

  public LoggersWebEndpointExtension(LoggingSystem loggingSystem) {
    super(loggingSystem);
  }

  @RequestMapping(value = "/log2/{level}", method = RequestMethod.POST)
  public String setLogLevel(@PathVariable("level") String level) {
    // , @RequestParam(value="package") String packageName) throws Exception {
    log.info("Log level: " + level);
    log.info("Package name: " + PACKAGE_NAME);
    String retVal = setLogLevel(level, PACKAGE_NAME);
    return retVal;
  }

  @RequestMapping(value = "/log2", method = RequestMethod.GET)
  public String getLogLevel() { // , @RequestParam(value="package") String packageName) throws Exception {
    // log.info("Log level: " + level);
    log.info("Package name: " + PACKAGE_NAME);
    String level = getLogLevel(PACKAGE_NAME);
    return level;
  }

  public String getLogLevel(String packageName) {
    Map<String, Object> map = super.loggers();

    if (map.containsKey(packageName)) {
      // super.loggers().get(packageName);
      return null; // map(packageName).
    }

    /*
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    return loggerContext.getLogger(packageName).getLevel().toString();
    */
    return null;
  }

  public String setLogLevel(String Level, String packageName) {
    // super.loggingSystem.setLogLevel();
    return null;
  }

}
