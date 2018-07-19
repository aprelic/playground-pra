package com.avaloq.avaloqpra.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggersUtil {

  private static final Logger log = LoggerFactory.getLogger(LoggersUtil.class);

  private static final String PACKAGE_NAME = "com.avaloq.avaloqpra";

  @RequestMapping(value = "/log/{level}", method = RequestMethod.POST)
  public String setLogLevel(@PathVariable("level") String level) {
    // , @RequestParam(value="package") String packageName) throws Exception {
    log.info("Log level: " + level);
    log.info("Package name: " + PACKAGE_NAME);
    String retVal = setLogLevel(level, PACKAGE_NAME);
    return retVal;
  }

  @RequestMapping(value = "/log", method = RequestMethod.GET)
  public String getLogLevel() { // , @RequestParam(value="package") String packageName) throws Exception {
    // log.info("Log level: " + level);
    log.info("Package name: " + PACKAGE_NAME);
    String level = getLogLevel(PACKAGE_NAME);
    return level;
  }

  public String getLogLevel(String packageName) {
    /*
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    return loggerContext.getLogger(packageName).getLevel().toString();
    */
    return null;
  }

  public String setLogLevel(String level, String packageName) {
    /*
    String retVal;
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    if (level.equalsIgnoreCase("DEBUG")) {
      loggerContext.getLogger(packageName).setLevel(Level.DEBUG);
      retVal = "ok";

    } else if (level.equalsIgnoreCase("TRACE")) {
      loggerContext.getLogger(packageName).setLevel(Level.TRACE);
      retVal = "ok";

    } else if (level.equalsIgnoreCase("INFO")) {
      loggerContext.getLogger(packageName).setLevel(Level.INFO);
      retVal = "ok";

    } else if (level.equalsIgnoreCase("WARN")) {
      loggerContext.getLogger(packageName).setLevel(Level.INFO);
      retVal = "ok";

    } else if (level.equalsIgnoreCase("ERROR")) {
      loggerContext.getLogger(packageName).setLevel(Level.INFO);
      retVal = "ok";

    } else {
      log.error("Not a supported level: " + level);
      retVal = "Error, not a supported level: " + level;
    }
    return retVal;
    */
    return null;
  }
}
