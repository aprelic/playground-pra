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

import com.avaloq.avaloqpra.domain.Counterparty;
import com.avaloq.avaloqpra.domain.Position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByCounterparty(Counterparty counterparty);

    // nested properties example
    List<Position> findByCounterpartyAvaloqKey(Long counterpartyAvaloqKey);

    List<Position> findByAvaloqKey(Long avaloqKey);

    Integer countDistinctByCounterparty(Counterparty counterparty);
}
