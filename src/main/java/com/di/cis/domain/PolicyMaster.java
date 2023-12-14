package com.di.cis.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PolicyMaster.
 */
@Table("policy_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PolicyMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("purpuse_name")
    private String purpuseName;

    @Column("policy_name")
    private String policyName;

    @Column("charges_type")
    private String chargesType;

    @Column("interest_rate")
    private Double interestRate;

    @Column("number_of_installments")
    private Integer numberOfInstallments;

    @Column("penalty_rate_of_interest")
    private Double penaltyRateOfInterest;

    @Column("installment_duration")
    private Integer installmentDuration;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PolicyMaster id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurpuseName() {
        return this.purpuseName;
    }

    public PolicyMaster purpuseName(String purpuseName) {
        this.setPurpuseName(purpuseName);
        return this;
    }

    public void setPurpuseName(String purpuseName) {
        this.purpuseName = purpuseName;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public PolicyMaster policyName(String policyName) {
        this.setPolicyName(policyName);
        return this;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getChargesType() {
        return this.chargesType;
    }

    public PolicyMaster chargesType(String chargesType) {
        this.setChargesType(chargesType);
        return this;
    }

    public void setChargesType(String chargesType) {
        this.chargesType = chargesType;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }

    public PolicyMaster interestRate(Double interestRate) {
        this.setInterestRate(interestRate);
        return this;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getNumberOfInstallments() {
        return this.numberOfInstallments;
    }

    public PolicyMaster numberOfInstallments(Integer numberOfInstallments) {
        this.setNumberOfInstallments(numberOfInstallments);
        return this;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Double getPenaltyRateOfInterest() {
        return this.penaltyRateOfInterest;
    }

    public PolicyMaster penaltyRateOfInterest(Double penaltyRateOfInterest) {
        this.setPenaltyRateOfInterest(penaltyRateOfInterest);
        return this;
    }

    public void setPenaltyRateOfInterest(Double penaltyRateOfInterest) {
        this.penaltyRateOfInterest = penaltyRateOfInterest;
    }

    public Integer getInstallmentDuration() {
        return this.installmentDuration;
    }

    public PolicyMaster installmentDuration(Integer installmentDuration) {
        this.setInstallmentDuration(installmentDuration);
        return this;
    }

    public void setInstallmentDuration(Integer installmentDuration) {
        this.installmentDuration = installmentDuration;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PolicyMaster)) {
            return false;
        }
        return getId() != null && getId().equals(((PolicyMaster) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PolicyMaster{" +
            "id=" + getId() +
            ", purpuseName='" + getPurpuseName() + "'" +
            ", policyName='" + getPolicyName() + "'" +
            ", chargesType='" + getChargesType() + "'" +
            ", interestRate=" + getInterestRate() +
            ", numberOfInstallments=" + getNumberOfInstallments() +
            ", penaltyRateOfInterest=" + getPenaltyRateOfInterest() +
            ", installmentDuration=" + getInstallmentDuration() +
            "}";
    }
}
