/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject.Controller;

import com.mycompany.umlproject.Bank;
import com.mycompany.umlproject.BankAccount;
import com.mycompany.umlproject.Factory.BankFactory;
import com.mycompany.umlproject.PixKey;
import java.util.ArrayList;
import java.util.List;

public class PixController {
    private BankAccount loggedAccount;
    private List<PixKey> allPixKeys;
    private List<Bank> banks;
    private List<BankAccount> accounts;

    public PixController() {
        initializeData();
    }

    private void initializeData() {
        // initialize variables//

        banks = new ArrayList<>();
        accounts = new ArrayList<>();
        allPixKeys = new ArrayList<>();
        
        // create banks
        createBanks();
        
        //create accounts
        createAccounts();
        
        // create pix keys
        createPixKeys();
    }

    private void createBanks() {
        BankFactory bf = new BankFactory();
        banks.add(bf.createBank("Banespa", "123"));
        banks.add(bf.createBank("Sicred", "739"));
        
    }


    private void createAccounts() {
        Bank banespa = banks.get(0);
        Bank sicred = banks.get(1);
                
        BankAccount acc1 = banespa.createAccount("Rogerio", "77777", 1000.0);
        BankAccount acc2 = sicred.createAccount("Ronaldo", "87878", 200.0);
        BankAccount acc3 = banespa.createAccount("Celso", "9999", 0.00);
        if (acc1 != null) accounts.add(acc1);
        if (acc2 != null) accounts.add(acc2);
        if (acc3 != null) accounts.add(acc3);

        this.loggedAccount = accounts.get(0);

        /*
        banespa.createAccount("Rogerio", "77777", 1000.0);
        sicred.createAccount("Ronaldo", "87878", 200.0);
        banespa.createAccount("Celso", "9999", 0.00);
        */
        
        //definy logged account
        //this.loggedAccount = accounts.get(0);
    }

    
    
    private void createPixKeys() {
        BankAccount ronaldoAccount = accounts.get(1);
        
        //bankAccount responsability
        ronaldoAccount.createPixKey("ronaldo@unesp.br");
        allPixKeys.addAll(ronaldoAccount.getPixKeys());
        BankAccount celsoAccount = accounts.get(2);
        celsoAccount.createPixKey("celso@unesp.br");
        allPixKeys.addAll(celsoAccount.getPixKeys());   
    }
    
    
    public String getAccountInfoHTML() {
        //bankAccount responsability
        return loggedAccount.getAccountInfoHTML(loggedAccount);
    }

        public String getTransactionHistoryText() { 
        //bankAccount responsability
         return loggedAccount.getFormattedTransactionHistory();
    }
    
        
    // first layer of the transaction operation, used to validate and fetch the pix key//
    public boolean sendPix(String targetKey, double value) {
       if(value <= 0 || targetKey == null || targetKey.isEmpty()){
           return false;
       }
       PixKey targetPixKey = null;
        for (PixKey key : allPixKeys) {
            if (key.getKey().equals(targetKey)) {
                targetPixKey = key;
                break;
            }
        }
        if(targetPixKey == null){
            return false;
        }
        BankAccount sourceAccount = loggedAccount;
        BankAccount targetAccount = targetPixKey.getBankAccount();
        //origin bank
        Bank sourceBank = sourceAccount.getBank();
        
        //bank responsability
        boolean success = sourceBank.transfer(sourceAccount, targetAccount, value);
        return success;
    }
    
    
    public String getAvailableKeysHTML() {
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
    

}
