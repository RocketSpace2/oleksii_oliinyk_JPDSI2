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
    private String loanAmount;
    private String interestRate;
    private String numberOfMonths;
    private Double result;

    @Inject
    FacesContext ctx;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(String numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Double getResult() {
        return result;
    }   
        
    public boolean doTheMath() {
        int numberOfMonths;
        
        try{
            numberOfMonths = Integer.parseInt(this.numberOfMonths);
        }catch(Exception e){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Liczba misięcy musi być liczbą całkowitą", null));
            return false;
        }
        
        try {
            double loanAmount = Double.parseDouble(this.loanAmount);
            double monthlyInterestRate = Double.parseDouble(this.interestRate) / 1200;
            
            
            result = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate,numberOfMonths))
                    /(Math.pow((1 + monthlyInterestRate), numberOfMonths) - 1);
            
            result = (double)Math.round(result * 100) / 100;
            
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
            return true; 
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów \"Kwota kredytu\" "
                    + "albo \"Ilość rat miesięcznych\"", null));
            return false; 
        }

    }
    
    public String calc() {
        if (doTheMath()) {
            return "showresult";
        }
        return null;
    }

    public String info() {
            return "info"; 
    }
}
