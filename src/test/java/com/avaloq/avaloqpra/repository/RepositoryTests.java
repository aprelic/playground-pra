/*
 * Copyright Avaloq Licence AG. All rights reserved.
 * Avaloq Licence AG
 * Schwerzistrasse 6 | CH-8807 Freienbach | Switzerland
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms
 * of the license agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.avaloqpra.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.avaloq.avaloqpra.domain.Counterparty;
import com.avaloq.avaloqpra.domain.Position;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

  @Autowired
  CounterpartyRepository counterpartyRepository;

  @Autowired
  PositionRepository positionRepository;

  @Test
  public void shouldSaveCounterpartyAndPosition() {
    Counterparty counterparty = new Counterparty(100L, "CNTPRTY.100", "Counterparty 100");

    counterpartyRepository.save(counterparty);
    assertThat(counterpartyRepository.findByAvaloqKey(100L).get(0).getName())
        .isEqualTo("Counterparty 100");
    assertThat(counterpartyRepository.findBySymbolicKey("CNTPRTY.100").get(0).getName())
        .isEqualTo("Counterparty 100");
    assertThat(counterpartyRepository.findByName("Counterparty 100").get(0).getName())
        .isEqualTo("Counterparty 100");

    Position position = new Position(200L, "Position 200 in 100", counterparty);
    positionRepository.save(position);
    assertThat(positionRepository.findByAvaloqKey(200L).get(0).getName())
        .isEqualTo("Position 200 in 100");

    Position anotherPosition = new Position(201L, "Position 201 in 100", counterparty);
    positionRepository.save(anotherPosition);
    assertThat(positionRepository.findByCounterparty(counterparty).size()).isEqualTo(2);

    assertThat(positionRepository.countDistinctByCounterparty(counterparty)).isEqualTo(2);
  }

  @Autowired
  EntityManager em;

  @Test
  public void shouldTestEqualityBasedOnNaturalId() {
    Counterparty counterparty =  new Counterparty(300L, "CNPRTY_300", "Test counterparty 300");
    Position position = new Position(301L, "Test position 301", counterparty);
    counterpartyRepository.save(counterparty);
    positionRepository.save(position);
    Long id = position.getId();

    // row fetched twice result in identical Java object, when in the same session, which are identical
    Position position1 = em.find(Position.class, id);
    Position position2 = em.find(Position.class, id);
    assertThat(position1.equals(position2));

    // row fetched twice result in different Java objects, when in different sessions => make sure to implement equals() for business-aware equality
    //Position position3 = doInJPA(emf, entityManager -> { return entityManager.find(Position.class, id); });
    //Position position4 = doInJPA(emf, entityManager -> { return entityManager.find(Position.class, id); });
    //assertThat(position3.equals(position4));
  }
}
