/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

/**
 *
 * @author daza
 */
public class BankAccount {
    private String holder;
    private String accountNumber;
    private double balance;
    private Bank bank;
    
    
    public BankAccount(String holder, String accountNumber, double balance, Bank bank){
        this.holder = holder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bank = bank;
    }
    
    
   public boolean debit(double value){
        if(balance >= value){
            balance -= value;
            return true;
        }else{
            return false;
        }
    }
    
    public void deposit(double value){
        if(value < 0){
            System.out.println("Negative value cannot be deposited!!!!");
        }else{
            balance += value;
        }
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
    
    
    
    
    
}
