/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PixController {
    private BankAccount loggedAccount;
    private List<PixKey> allPixKeys;
    private List<String> transactionHistory;
    private List<Bank> banks;
    private List<BankAccount> accounts;

    public PixController() {
        initializeData();
    }

    private void initializeData() {
        
        ///INITIALIZE VARIABLES///
        ///
        ///
        // initialize banks
        banks = new ArrayList<>();
        banks.add(new Bank("Banespa", "123"));
        banks.add(new Bank("Sicred", "739"));

        // initialize account banks
        accounts = new ArrayList<>();
        Bank banespa = banks.get(0);
        Bank sicred = banks.get(1);
        
        BankAccount rogerio = new BankAccount("Rogerio", "77777", 1000.0, banespa);
        BankAccount ronaldo = new BankAccount("Ronaldo", "87878", 200.0, sicred);
        BankAccount celso = new BankAccount("Celso", "9999", 0.00, banespa);
        
        accounts.add(rogerio);
        accounts.add(ronaldo);
        accounts.add(celso);
        
        //logged account
        this.loggedAccount = rogerio;
        
        // initialize pix keys
        allPixKeys = new ArrayList<>();
        allPixKeys.add(new PixKey("ronaldo@unesp.br", ronaldo));
        allPixKeys.add(new PixKey("celso@unesp.br", celso));
        
        
        transactionHistory = new ArrayList<>();
    }

    public String getAccountInfoHTML() {
        return "<html>" +
               "<b>Banco:</b> " + loggedAccount.getBank().getName() + "<br>" +
               "<b>Código:</b> " + loggedAccount.getBank().getCod() + "<br>" +
               "<b>Titular:</b> " + loggedAccount.getHolder() + "<br>" +
               "<b>Conta:</b> " + loggedAccount.getAccountNumber() + "<br>" +
               String.format("<b>Saldo:</b> R$ %.2f", loggedAccount.getBalance()) +
               "</html>";
    }
    
    public List<String> getAvailableKeys() {
        List<String> keys = new ArrayList<>();
        for(PixKey pk : allPixKeys) {
            keys.add(pk.getKey());
        }
        return keys;
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
    
    public String getTransactionHistoryText() {
        StringBuilder sb = new StringBuilder();
        for(String transaction : transactionHistory) {
            sb.append(transaction).append("\n");
        }
        return sb.toString();
    }
    
    public boolean sendPix(String targetKey, double value) {
        PixKey target = null;
        
        for(PixKey pk : allPixKeys){
            if(pk.getKey().equals(targetKey)){
                target = pk;
                break;
            }
        }
        
        if (target == null) return false;
        if (value <= 0) return false;
        if (target.getBankAccount() == loggedAccount) return false;
        
        if(loggedAccount.debit(value)){
            target.getBankAccount().deposit(value);
            String transaction = String.format("[%s] Enviado R$ %.2f para chave: %s", 
                    new Date(), value, targetKey);
            transactionHistory.add(transaction);
            return true;
        }
        return false;
    }
    
    public void updateAccountBalance(double newBalance) {
        loggedAccount.setBalance(newBalance);
    }
}
