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
public class BankAccount {
    private String holder;
    private String accountNumber;
    private double balance;
    private Bank bank;
    private List<PixKey> pixKeys = new ArrayList<>();
    private List<Transaction> transactionHistory = new ArrayList<>();
    
    
    public BankAccount(String holder, String accountNumber, double balance, Bank bank){
        this.holder = holder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bank = bank;
    }
    
    
    public String toString(){
       return "Holder: " + holder + " | Account Number: " + accountNumber + 
       " | Balance: " + balance + " | Bank: " + bank.getName() + " | Cod: " + bank.getCod();
    }
    
    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public PixKey createPixKey(String key) {
            PixKey newKey = new PixKey(key, this);
            pixKeys.add(newKey);
            return newKey;
    }

    public List<PixKey> getPixKeys() {
        return new ArrayList<>(pixKeys);
    }
    
    public boolean debit(double amount) {
        if (amount >= 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("Valor inválido para depósito");
        }
    }
    
    
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
    
   public String getFormattedTransactionHistory() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactionHistory) {
            String details = t.getDetails();
            if (!details.isEmpty()) {
                sb.append(details).append("\n");
            }
        }
        return sb.toString();
    }

       
     public String getAccountInfoHTML(BankAccount account) {
        return "<html>" +
               "<b>Banco:</b> " + getBank().getName()+ "<br>" +
               "<b>Código:</b> " + getBank().getCod() + "<br>" +
               "<b>Titular:</b> " + getHolder() + "<br>" +
               "<b>Conta:</b> " +  getAccountNumber() + "<br>" +
               String.format("<b>Saldo:</b> R$ %.2f", getBalance()) +
               "</html>";
    }
    
}
