/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author daza
 */
public class Bank {
    private String name;
    private String cod;
    private List<BankAccount> accounts = new ArrayList<>();
    
    public Bank(String name, String cod){
        this.name = name;
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    public BankAccount createAccount(String holder, String accountNumber, double balance) {
        BankAccount account = new BankAccount(holder, accountNumber, balance, this);
        accounts.add(account);
        return account;
    }

    ///second layer of the transaction, currently simple for representation only, but would be responsible for banking rules//
    public boolean transfer(BankAccount source, BankAccount target, double amount){
        if (!accounts.contains(source)) {
            return false;
        }
        return Transaction.executeTransfer(source, target, amount, "SEND");
    }
   
}
    
    

