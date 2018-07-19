package com.avaloq.avaloqpra.service;

import com.avaloq.avaloqpra.domain.Counterparty;
import com.avaloq.avaloqpra.repository.CounterpartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CounterpartyService {

  private static final Logger log = LoggerFactory.getLogger(CounterpartyService.class);
  private final CounterpartyRepository counterpartyRepository;

  @Autowired
  public CounterpartyService(CounterpartyRepository counterpartyRepository) {
    this.counterpartyRepository = counterpartyRepository;
  }

  public List<Counterparty> findCounterpartyByAvaloqKey(Long key) {
    log.debug("findCounterpartyByAvaloqKey");
    return counterpartyRepository.findByExternalId(key);
  }

}
