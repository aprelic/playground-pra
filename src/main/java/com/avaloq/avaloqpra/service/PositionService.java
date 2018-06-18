package com.avaloq.avaloqpra.service;

import com.avaloq.avaloqpra.domain.Counterparty;
import com.avaloq.avaloqpra.domain.ImpairmentStage;
import com.avaloq.avaloqpra.domain.Position;
import com.avaloq.avaloqpra.repository.CounterpartyRepository;
import com.avaloq.avaloqpra.repository.PositionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@Transactional
public class PositionService {

  private static final Logger logger = LoggerFactory.getLogger(PositionService.class);

  private final TransactionTemplate transactionTemplate; // used only because @Transactional does not hve effect in in @PostConstruct
  private final PositionRepository positionRepository;
  private final CounterpartyRepository counterpartyRepository;

  @Autowired
  public PositionService(TransactionTemplate transactionTemplate,
      PositionRepository positionRepository, CounterpartyRepository counterpartyRepository) {
    this.transactionTemplate = transactionTemplate;
    this.positionRepository = positionRepository;
    this.counterpartyRepository = counterpartyRepository;
  }

  // initialize some data on application startup
  @PostConstruct
  public void init() {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        Counterparty counterparty = new Counterparty(1001L, "CNTPRTY_1001",
            "Counterparty Name 1001");
        Position position = new Position(2001L
            , "Position 2001"
            , "CHF"
            , LocalDate.of(2020, Month.NOVEMBER, 15)
            , ImpairmentStage.STAGE_2
            , counterparty);
        List<BigDecimal> pdVector = new ArrayList<BigDecimal>();
        pdVector.add(BigDecimal.ONE);
        pdVector.add(BigDecimal.ZERO);
        pdVector.add(BigDecimal.TEN);
        position.setPdVector(pdVector);
        counterpartyRepository.save(counterparty);
        positionRepository.save(position);

        logger.info("Counterparty created", counterparty);
        logger.info("Position created", position);
      }
    });
  }

  public Position save(Position position) {
    return positionRepository.save(position);
  }

  public Position findPositionById(Long id) {
    return positionRepository.findById(id).orElse(null); // or, use Optional?
  }

  public List<Position> findByCounterpartyAvaloqKey(Long counterpartyAvaloqKey) {
    return positionRepository.findByCounterpartyExternalId(counterpartyAvaloqKey);
  }

  public List<Position> findByCounterparty(Counterparty counterparty) {
    return positionRepository.findByCounterparty(counterparty);
  }
}
