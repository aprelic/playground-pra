package com.avaloq.avaloqpra.service;

import com.avaloq.avaloqpra.domain.Counterparty;
import com.avaloq.avaloqpra.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyService(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    public List<Counterparty> findCounterpartyByAvaloqKey(Long key) {
        return counterpartyRepository.findByAvaloqKey(key);
    }

}
