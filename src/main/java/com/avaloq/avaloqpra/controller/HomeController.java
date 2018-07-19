/*
 * Copyright Avaloq Licence AG. All rights reserved.
 * Avaloq Licence AG
 * Schwerzistrasse 6 | CH-8807 Freienbach | Switzerland
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms
 * of the license agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.avaloqpra.controller;

import com.avaloq.avaloqpra.aspect.LogExecTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HomeController {

  private static final Logger log = LoggerFactory.getLogger(HomeController.class);
  private int counter = 0;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @LogExecTime
  public String processHomeRequest() {
    log.debug("Get home request for processing {}", ++counter);
    return "Home Request was processed!";
  }

}
