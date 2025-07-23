/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
 *
 * @author daza
 */
public class Bank {
    private String name;
    private String cod;
    private List<BankAccount> accounts = new ArrayList<>();
    private List<String> transactionHistory = new ArrayList<>();
    
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

    public List<BankAccount> getAccounts() {
        return accounts;
    }
    
       public boolean debit(BankAccount account, double amount){
        if(amount >= 0 && account.getBalance() >= amount){
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            return true;
        }else{
            return false;
        }
    }
    
    public void deposit(BankAccount account, double amount){
        if(amount <= 0){
            System.out.println("Negative value cannot be deposited!!!!");
        }else{
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
        }
    }
    
    public void receiveTransfer(BankAccount target, double amount){
        if(accounts.contains(target)){
            deposit(target,amount);
        }
    }
    
    private void registerTransfer(BankAccount source, BankAccount target, double amount) {
        String transaction = String.format("[%s] Transferência de R$ %.2f | %s -> %s",
                new Date(), amount, 
                source.getAccountNumber(), 
                target.getAccountNumber());
        
        transactionHistory.add(transaction);
    }
    
    //historico que tava no pixController responsabilidade do banco agora
    public String getTransactionHistoryText() {
        StringBuilder sb = new StringBuilder();
        for(String transaction : transactionHistory) {
            sb.append(transaction).append("\n");
        }
        return sb.toString();
    }
    
    
    public boolean transfer(BankAccount source, BankAccount target, double amount){
        if(source == null || target == null || amount <= 0){
            return false;
        }   
        
        if(!accounts.contains(source)){
            return false;
        }
        
        if(debit(source,amount)){
            if(accounts.contains(target)){
                deposit(target,amount);
            }else{
                target.getBank().receiveTransfer(target,amount);
            }
            
            registerTransfer(source, target, amount);
            return true;
        }
        return false;
        
    }
    
     public String getAccountInfoHTML(BankAccount account) {
        return "<html>" +
               "<b>Banco:</b> " + getName() + "<br>" +
               "<b>Código:</b> " + getCod() + "<br>" +
               "<b>Titular:</b> " + account.getHolder() + "<br>" +
               "<b>Conta:</b> " + account.getAccountNumber() + "<br>" +
               String.format("<b>Saldo:</b> R$ %.2f", account.getBalance()) +
               "</html>";
    }
    
    public String getAvailableKeysHTML(List<PixKey> allPixKeys) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='width: 300px; padding:10px;'>")
          .append("<h3>Versão de Teste</h3>")
          .append("<p>Chaves Pix disponíveis:</p><ul>");
        
        for(PixKey pk : allPixKeys) {
            sb.append("<li><b>").append(pk.getKey()).append("</b></li>");
        }
        
        sb.append("</ul></body></html>");
        return sb.toString();
    }
    
    public List<String> getAvailableKeys(List<PixKey> allPixKeys) {
        List<String> keys = new ArrayList<>();
        for(PixKey pk : allPixKeys) {
            keys.add(pk.getKey());
        }
        return keys;
    }
   
}
    
    

