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
public class CentralBank {
    private static CentralBank instance;
    private final List<Bank> banks = new ArrayList<>();
    private final List<BankAccount> allAccounts = new ArrayList<>();
    
    private CentralBank(){
        
    }
    public static CentralBank getInstance(){
        if (instance == null) {
            instance = new CentralBank();
        }
        return instance;
    }
    
    public void registerBank(Bank bank) {
        banks.add(bank);
        allAccounts.addAll(bank.getAccounts());
    }
    
    public Bank createBank(String name, String cod) {
        Bank bank = new Bank(name, cod);
        registerBank(bank);
        return bank;
    }
    
    public List<PixKey> getAllPixKeys() {
        List<PixKey> allKeys = new ArrayList<>();
        
        for (Bank bank : banks) {
            for (BankAccount account : bank.getAccounts()) {
                allKeys.addAll(account.getPixKeys());
            }
        }

        return allKeys;
    }
    
    
    public BankAccount findAccountByPixKey(String pixKey) {
        for (PixKey key : getAllPixKeys()) {
            if (key.getKey().equals(pixKey)) {
                return key.getBankAccount();
            }
        }
        return null;
    }
    
    
    public BankAccount findAccountByNumber(String accountNumber) {
        for (Bank bank : banks) {
            for (BankAccount account : bank.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }
    
    public List<Bank> getBanks() {
        return this.banks;
    }
    
    
    
    
}
   