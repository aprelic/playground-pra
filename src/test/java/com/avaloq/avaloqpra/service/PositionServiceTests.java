package com.avaloq.avaloqpra.service;

import com.avaloq.avaloqpra.domain.ImpairmentStage;
import com.avaloq.avaloqpra.domain.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionServiceTests {

  @Autowired
  private PositionService positionService;

  // @Autowired
  // private CounterpartyService counterpartyService;

  @Test
  public void shoudFindPositionInitializedOnStartup() {
    // List<Counterparty> counterparties = counterpartyService.findCounterpartyByAvaloqKey(2001L);
    // assertThat(counterparties.size(), equalTo(1));
    // Counterparty counterparty = counterparties.get(0);

    // List<Position> positions = positionService.findByCounterparty(counterparty);
    List<Position> positions = positionService.findByCounterpartyAvaloqKey(1001L);
    assertThat(positions.size(), equalTo(1));

    Position position = positions.get(0);
    assertThat(position.getAvaloqKey(), equalTo(2001L));
    assertThat(position.getImpairmentStage(), equalTo(ImpairmentStage.STAGE_2));
    assertThat(position.getMaturityDate(), equalTo(LocalDate.of(2020, Month.NOVEMBER, 15)));
  }
}
