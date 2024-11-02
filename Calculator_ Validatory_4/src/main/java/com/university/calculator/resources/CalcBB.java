package com.university.calculator.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.lang.Math;

@Named
@RequestScoped
public class CalcBB {
    private Double loanAmount;
    private Double interestRate;
    private Integer numberOfMonths;
    private Double result;

    @Inject
    FacesContext ctx;

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Double getResult() {
        return result;
    }   
        
    public boolean doTheMath() {
            double monthlyInterestRate = this.interestRate / 1200;
            
            result = (this.loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate,this.numberOfMonths))
                    /(Math.pow((1 + monthlyInterestRate), this.numberOfMonths) - 1);
            
            result = (double)Math.round(result * 100) / 100;
            
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
            return true;
    }
    
    public String calc() {
        if (doTheMath()) {
            return "showresult";
        }
        return null;
    }
    
    public String calc_AJAX() {
        if (doTheMath()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
        }
        return null;
    }

    public String info() {
            return "info"; 
    }
}
