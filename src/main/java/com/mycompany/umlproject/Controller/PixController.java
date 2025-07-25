/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject.Controller;

import com.mycompany.umlproject.Bank;
import com.mycompany.umlproject.BankAccount;
import com.mycompany.umlproject.CentralBank;
import com.mycompany.umlproject.Factory.BankFactory;



public class PixController {
    private final CentralBank cb = CentralBank.getInstance();
    private BankAccount loggedAccount;

    public PixController() {
         if (cb.getBanks().isEmpty()) {
            initializeData();
        } else {
             ///because i dont have a login
            this.loggedAccount = cb.findAccountByNumber("7777");
        }
    }

    private void initializeData() {
       BankFactory bf = new BankFactory();
       Bank bank = bf.createBank("Banespa", "123");
       Bank bank2 = bf.createBank("Sicred", "739");
       
       cb.registerBank(bank);
       cb.registerBank(bank2);
       
       this.loggedAccount = bank.createAccount("Rogerio","7777",1000.0);
       
       BankAccount ronaldoacc = bank2.createAccount("Ronaldo", "8787", 200.0);
       BankAccount celsoacc = bank.createAccount("Celso", "9999", 0);
       
       loggedAccount.createPixKey("rogerio@unesp.br");
       ronaldoacc.createPixKey("ronaldo@unesp.br");
       celsoacc.createPixKey("celso@unesp.br");
    }

    
    public String getLoggedAccountInfo() {
        //bank responsability
        return loggedAccount.getBank().getAccountInfo(loggedAccount);
    }

        public String getLoggedTransactionHistoryText() { 
        //bankAccount responsability
         return loggedAccount.getFormattedTransactionHistory();
    }
    
        
    // first layer of the transaction operation, used to validate and fetch the pix key//
    public boolean sendPix(String targetKey, double value) {
       if(value <= 0 || targetKey == null || targetKey.isEmpty()){
           return false;
       }
        BankAccount targetAccount = cb.findAccountByPixKey(targetKey);
       
        if(targetAccount == null){
            return false;
        }
        boolean success = loggedAccount.transfer(targetAccount, value);
        return success;
    }
    
    ///////ESta aqui apenas pelo teste, vai sair dps///////////////////
    public String getAvailableKeysHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
          .append("<h3>Versão de Teste</h3>")
          .append("<p>Chaves Pix disponíveis:</p><ul>");
        
        cb.getAllPixKeys().forEach(pk -> 
            sb.append("<li><b>").append(pk.getKey()).append("</b></li>")
        );
        
        sb.append("</ul></body></html>");
        return sb.toString();
    }
    ////////////////////////////////////////////////////////////////////
}
