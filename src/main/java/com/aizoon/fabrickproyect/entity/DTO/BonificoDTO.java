package com.aizoon.fabrickproyect.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class BonificoDTO {

    private Creditor creditor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creditor {

        private String name;
        private Account account;
        private Address address;

    }

    private String executionDate;
    private String uri;
    private String description;
    private Long amount;
    private String currency;
    @JsonProperty("isUrgent")
    private boolean isUrgent;
    @JsonProperty("isInstant")
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;

    public BonificoDTO(String nameParameter, String accountCodeParameter,String descriptionParameter,
                         Long amountParameter,String currencyParameter, String executionDateParameter){
        this.creditor = new Creditor(nameParameter, new Account(accountCodeParameter), new Address());
        this.isUrgent = false;
        this.isInstant = false;
        this.description = descriptionParameter;
        this.amount = amountParameter;
        this.currency = currencyParameter;
        this.executionDate = executionDateParameter;
        this.taxRelief = new TaxRelief();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Account {
        private String accountCode;
        private String bicCode;

        public Account(String accountCodeParameter) {
            this.accountCode = accountCodeParameter;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String address;
        private String city;
        private String countryCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaxRelief {
        private String taxReliefId;
        @JsonProperty("isCondoUpgrade")
        private boolean isCondoUpgrade;
        private String creditorFiscalCode;
        private String beneficiaryType;

        private NaturalPersonBeneficiary naturalPersonBeneficiary;
        private LegalPersonBeneficiary legalPersonBeneficiary;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NaturalPersonBeneficiary {
        private String fiscalCode1;
        private String fiscalCode2;
        private String fiscalCode3;
        private String fiscalCode4;
        private String fiscalCode5;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LegalPersonBeneficiary {
        private String fiscalCode;
        private String legalRepresentativeFiscalCode;
    }
}
