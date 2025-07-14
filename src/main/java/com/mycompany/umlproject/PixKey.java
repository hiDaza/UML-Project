/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

/**
 *
 * @author daza
 */
public class PixKey {
    private String key;
    private BankAccount bankAccount;
    
    
    public PixKey(String key, BankAccount bankAccount){
        this.key = key;
        this.bankAccount = bankAccount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    
}
