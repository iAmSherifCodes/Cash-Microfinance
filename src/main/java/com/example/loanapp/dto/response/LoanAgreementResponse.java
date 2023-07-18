package com.example.loanapp.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanAgreementResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private BigDecimal loanAmount;
//    private double interestRate;
//    private LocalDate startDate;

    private String repaymentPreference;
    private String loanTenure;
    private BigDecimal amountPerPaymentPeriod;
//    private String paymentMethod;
//    private LocalDate endDate;
    private String errorMessage;


    @Override
    public String toString(){
//        LocalDate lEndDate = LocalDate.ofEpochDay(startDate.getYear() + Integer.parseInt(loanTenure));
        final StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t\t\t\t\t\tLOAN AGREEMENT");
        sb.append("\n\nPARTIES\n");

        sb.append("\n=> This Loan Agreement (herein referred to as the \"AGREEMENT\" is entered into on\n")
                .append(LocalDate.now()).append(" (the \"Effective Date\")")
                .append(" by and between ").append(firstName).append(" ").append(lastName).append(", with an address of ")
                .append(address)
                .append("(hereinafter referred to as the \"BORROWER\"), and CASH MICROFINANCE LTD., with an address of 12A, Jones\n")
                .append("Crescent, MaryLand, Lagos (hereinafter referred to as the \"LENDER\").\n")
                .append("Both BORROWER and LENDER shall be collectively referred to as the \"PARTIES\".");

        sb.append("\n\nINFORMATION DETAILS OF PARTIES");
        sb.append("\n=> Details of BORROWER");
        sb.append("\n- Name ->").append(" ").append(firstName).append(" ").append(lastName);
        sb.append("\n- Email Address ->").append(" ").append(email);
        sb.append("\n- Phone Number ->").append(" ").append(phoneNumber);
        sb.append("\n- Contact Address ->").append(" ").append(address);

        sb.append("\n\n=> Details of LENDER\n" +
                "- Name -> CASH MICROFINANCE LTD.\n" +
                "- Email Address -> contactcentre@cashmicrofinance.com | customerservice@cashmicrofinance.com\n" +
                "- Phone Number -> +234-8077223366 | 070-000111165 | +234-9006200000\n" +
                "- Contact Address -> 35A, Jones Crescent, MaryLand, Lagos.");

        sb.append("""


                TERMS OF AGREEMENT
                => The PARTIES agree that the loan information set below is accurate;""");
        sb.append("\n\n- Approved Loan Amount ->").append(" ").append(loanAmount);
//        sb.append("\n- Interest Rate ->").append(" ").append(interestRate);
        sb.append("\n- Loan Tenure ->").append(" ").append(loanTenure);
        sb.append("\n- Preferred Repayment Option ->").append(" ").append(repaymentPreference);
//        sb.append("\n- Preferred Repayment Method ->").append(" ").append(paymentMethod);
        sb.append("\n- Amount expected per payment period ->").append(" N").append(amountPerPaymentPeriod);
//        sb.append("\n- Start date of first repayment ->").append(" ").append(startDate);
//        sb.append("\n- End date of last repayment ->").append(" ").append(lEndDate);
        sb.append("\n- Loan default fee -> 1% of existing balance per payment period.");

        sb.append("\n\n\nENDORSEMENTS\n\n\n");
        sb.append("".repeat(25)).append(" ".repeat(30)).append("".repeat(25)).append("\n");
        sb.append("\t").append(firstName).append(" ").append(lastName).append(" ".repeat(46)).append("Sherif Awofiranye\n");
        sb.append("\t").append("BORROWER").append(" ".repeat(45)).append("Team Lead, CASH MICROFINANCE");

        return sb.toString();
    }
}
