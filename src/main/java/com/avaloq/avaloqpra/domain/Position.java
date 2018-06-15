/*
 * Copyright Avaloq Licence AG. All rights reserved.
 * Avaloq Licence AG
 * Schwerzistrasse 6 | CH-8807 Freienbach | Switzerland
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms
 * of the license agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.avaloqpra.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(name = "PRA_ECL_POS")
public class Position {

  @Id
  @NotNull
  @GeneratedValue
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pra_ecl_pos_seq") // sequence must manually generated
  //@SequenceGenerator(name = "pra_ecl_pos_seq", sequenceName = "pra_ecl_pos_seq", allocationSize = 100) // dflt value is 50
  private Long id;

  @NotNull
  //@Column(name = "avq_key")
  private Long avaloqKey;

  @NotNull
  @Size(max = 500)
  private String name;

  @NotNull
  @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  //@JoinColumn(name = "cntprty_id")
  private Counterparty counterparty;

  protected Position() { // JPA
  }

  public Position(
      @NotNull Long avaloqKey,
      @NotNull @Size(max = 500) String name,
      @NotNull Counterparty counterparty) {
    this.avaloqKey = avaloqKey;
    this.name = name;
    this.counterparty = counterparty;
  }

  @Override
  public String toString() {
    return "Position{" +
        "id=" + id +
        ", avaloqKey=" + avaloqKey +
        ", name='" + name + '\'' +
        ", counterparty=" + counterparty +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(avaloqKey, position.avaloqKey); // && Objects.equals(counterparty.getAvaloqKey(), position.counterparty.getAvaloqKey())?
  }

  @Override
  public int hashCode() {
    return Objects.hash(avaloqKey); // , counterparty.getAvaloqKey());
  }

  public Long getId() {
    return id;
  }

  public Long getAvaloqKey() {
    return avaloqKey;
  }

  public String getName() {
    return name;
  }

  public Counterparty getCounterparty() {
    return counterparty;
  }
}
