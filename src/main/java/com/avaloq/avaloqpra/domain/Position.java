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

import com.avaloq.avaloqpra.converter.BigDecimalListConverter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POS")
public class Position {

  @Id
  @NotNull
  //@GeneratedValue
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pos_id_seq")
  @SequenceGenerator(name = "pos_id_seq", sequenceName = "pos_id_seq", allocationSize = 100)
  private Long id;

  @NotNull
  //@Column(name = "extl_id")
  private Long externalId;

  @NotNull
  @Size(max = 500)
  private String name;

  private LocalDate maturityDate;

  @NotNull
  @Size(max = 3)
  private String currencyCode;

  // not really sure we want to use these @Enumerated,
  // one cannot use FK's to ensure only correct values ae used
  // columns take more space
  // more effort for migrations, values in the database mst be kept in sync with the code
  @NotNull
  @Enumerated(EnumType.STRING)
  private ImpairmentStage impairmentStage;

  @NotNull
  @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "counterparty_id")
  private Counterparty counterparty;

  @Convert(converter = BigDecimalListConverter.class)
  private List<BigDecimal> pdVector;

  public List<BigDecimal> getPdVector() {
    return pdVector;
  }

  public void setPdVector(List<BigDecimal> pdVector) {
    this.pdVector = pdVector;
  }

  protected Position() { // JPA
  }

  public Position(
      @NotNull Long externalId,
      @NotNull @Size(max = 500) String name,
      @NotNull @Size(max = 3) String currencyCode,
      LocalDate maturityDate,
      @NotNull ImpairmentStage impairmentStage,
      @NotNull Counterparty counterparty) {
    this.externalId = externalId;
    this.name = name;
    this.maturityDate = maturityDate;
    this.currencyCode = currencyCode;
    this.impairmentStage = impairmentStage;
    this.counterparty = counterparty;
  }

  @Override
  public String toString() {
    return "Position{" +
        "id=" + id +
        ", externalId=" + externalId +
        ", name='" + name + '\'' +
        ", maturityDate=" + maturityDate +
        ", currencyCode=" + currencyCode +
        ", impairmentStage=" + impairmentStage +
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
    return Objects.equals(externalId,
        position.externalId); // && Objects.equals(counterparty.getExternalId(), position.counterparty.getExternalId())?
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId); // , counterparty.getExternalId());
  }

  public Long getId() {
    return id;
  }

  public Long getExternalId() {
    return externalId;
  }

  public String getName() {
    return name;
  }

  public Counterparty getCounterparty() {
    return counterparty;
  }

  public LocalDate getMaturityDate() {
    return maturityDate;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public ImpairmentStage getImpairmentStage() {
    return impairmentStage;
  }
}
