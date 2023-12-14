package com.di.cis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SchduleMaster.
 */
@Table("schdule_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SchduleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("installment_number")
    private Integer installmentNumber;

    @Column("reducing_balance")
    private BigDecimal reducingBalance;

    @Column("principle_amount")
    private BigDecimal principleAmount;

    @Column("interest")
    private BigDecimal interest;

    @Column("total_installment")
    private BigDecimal totalInstallment;

    @Column("due_date")
    private Long dueDate;

    @Column("remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SchduleMaster id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInstallmentNumber() {
        return this.installmentNumber;
    }

    public SchduleMaster installmentNumber(Integer installmentNumber) {
        this.setInstallmentNumber(installmentNumber);
        return this;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public BigDecimal getReducingBalance() {
        return this.reducingBalance;
    }

    public SchduleMaster reducingBalance(BigDecimal reducingBalance) {
        this.setReducingBalance(reducingBalance);
        return this;
    }

    public void setReducingBalance(BigDecimal reducingBalance) {
        this.reducingBalance = reducingBalance != null ? reducingBalance.stripTrailingZeros() : null;
    }

    public BigDecimal getPrincipleAmount() {
        return this.principleAmount;
    }

    public SchduleMaster principleAmount(BigDecimal principleAmount) {
        this.setPrincipleAmount(principleAmount);
        return this;
    }

    public void setPrincipleAmount(BigDecimal principleAmount) {
        this.principleAmount = principleAmount != null ? principleAmount.stripTrailingZeros() : null;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public SchduleMaster interest(BigDecimal interest) {
        this.setInterest(interest);
        return this;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest != null ? interest.stripTrailingZeros() : null;
    }

    public BigDecimal getTotalInstallment() {
        return this.totalInstallment;
    }

    public SchduleMaster totalInstallment(BigDecimal totalInstallment) {
        this.setTotalInstallment(totalInstallment);
        return this;
    }

    public void setTotalInstallment(BigDecimal totalInstallment) {
        this.totalInstallment = totalInstallment != null ? totalInstallment.stripTrailingZeros() : null;
    }

    public Long getDueDate() {
        return this.dueDate;
    }

    public SchduleMaster dueDate(Long dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public SchduleMaster remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchduleMaster)) {
            return false;
        }
        return getId() != null && getId().equals(((SchduleMaster) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchduleMaster{" +
            "id=" + getId() +
            ", installmentNumber=" + getInstallmentNumber() +
            ", reducingBalance=" + getReducingBalance() +
            ", principleAmount=" + getPrincipleAmount() +
            ", interest=" + getInterest() +
            ", totalInstallment=" + getTotalInstallment() +
            ", dueDate=" + getDueDate() +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
