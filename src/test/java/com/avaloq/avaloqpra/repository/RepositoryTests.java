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
import com.avaloq.avaloqpra.domain.ImpairmentStage;
import com.avaloq.avaloqpra.domain.Position;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

  @Autowired
  private CounterpartyRepository counterpartyRepository;

  @Autowired
  private PositionRepository positionRepository;

  @Autowired
  private EntityManager em;

  @Test
  public void shouldSaveCounterpartyAndPosition() {
    Counterparty counterparty = new Counterparty(100L, "CNTPRTY.100", "Counterparty 100");

    counterpartyRepository.save(counterparty);
    assertThat(counterpartyRepository.findByExternalId(100L).get(0).getName())
        .isEqualTo("Counterparty 100");
    assertThat(counterpartyRepository.findBySymbolicKey("CNTPRTY.100").get(0).getName())
        .isEqualTo("Counterparty 100");
    assertThat(counterpartyRepository.findByName("Counterparty 100").get(0).getName())
        .isEqualTo("Counterparty 100");

    Position position = new Position(200L, "Position 200 in 100", "CHF",
        LocalDate.of(2030, Month.APRIL, 10), ImpairmentStage.STAGE_1, counterparty);
    positionRepository.save(position);
    assertThat(positionRepository.findByExternalId(200L).get(0).getName())
        .isEqualTo("Position 200 in 100");

    Position anotherPosition = new Position(201L, "Position 201 in 100", "EUR",
        LocalDate.of(2020, Month.JANUARY, 31), ImpairmentStage.STAGE_1, counterparty);
    positionRepository.save(anotherPosition);
    assertThat(positionRepository.findByCounterparty(counterparty).size()).isEqualTo(2);

    assertThat(positionRepository.countDistinctByCounterparty(counterparty)).isEqualTo(2);
  }

  @Test
  public void shouldTestEqualityBasedOnNaturalId() {
    Counterparty counterparty = new Counterparty(300L,
        "CNPRTY_300",
        "Test counterparty 300");
    Position position = new Position(301L,
        "Test position 301",
        "CHF",
        LocalDate.of(2019, Month.NOVEMBER, 12),
        ImpairmentStage.STAGE_1,
        counterparty);
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
