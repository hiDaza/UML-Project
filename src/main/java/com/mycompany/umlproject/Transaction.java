/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

/**
 *
 * @author daza
 */
import java.util.Date;
import java.util.UUID;


public class Transaction {
    
    private String id;
    private Date time;
    private double amount;
    private BankAccount sourceAccount;
    private BankAccount targetAccount;
    private String type;
    private String description;
    public static final String PIX_SEND = "PIX_SEND";
    public static final String PIX_RECEIVE = "PIX_RECEIVE";
    
    public Transaction(BankAccount source, BankAccount target, double amount, String type) {
        this.id = generateId();
        this.time = new Date();
        this.sourceAccount = source;
        this.targetAccount = target;
        this.amount = amount;
        this.type = type;
        this.description = generateDescription();
    }
    
    private String generateId() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID.substring(0,5);
}
    
    private String generateDescription() {
        if (type.equals(PIX_SEND)) {
            return String.format("Envio de R$%.2f para %s (%s)",
                            amount,
                            targetAccount.getHolder(),
                            targetAccount.getAccountNumber());
        }
    
        else {
            return String.format("Operação: %s | Valor: R$%.2f", type, amount);
        }
    }

    public String getDetails() {
        if (type.equals(PIX_SEND)) {
            return String.format("[%s] %s | ID: %s", 
                            time, description, id);
        }
         return "";
    }
    

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }
    
    public BankAccount getTargetAccount() {
        return targetAccount;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getType() {
        return type;
    }
    
    ///third layer of the transaction that performs the operation itself of debiting the account or depositing into the other///
    public static boolean executeTransfer(BankAccount source, 
                                         BankAccount target, 
                                         double amount, 
                                         String type) {
        
        if (source == null || target == null || amount <= 0) {
            return false;
        }
        
        if (source.getBalance() < amount) {
            return false;
        }
        
        try {
            source.setBalance(source.getBalance() - amount);
            
            target.setBalance(target.getBalance() + amount);
            
            Transaction sourceTransaction = new Transaction(source, target, amount,PIX_SEND);
            Transaction targetTransaction = new Transaction(source, target, amount,PIX_RECEIVE);
            
            source.addTransaction(sourceTransaction);
            target.addTransaction(targetTransaction);
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}