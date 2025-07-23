/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject.Factory;

import com.mycompany.umlproject.Bank;

/**
 *
 * @author daza
 */
public class BankFactory {
    
    private boolean isValidName(String name){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        return true;
    }
    
    private boolean isValidCode(String cod){
        if(cod == null || !cod.matches("\\d{3}")){
            return false;
        }
        return true;
    }
    
     public Bank createBank(String name, String cod) {
        if(!isValidName(name)){
             throw new IllegalArgumentException("Nome do banco inválido.");
        }
        if(!isValidCode(cod)){
            throw new IllegalArgumentException("Código do banco deve ter 3 dígitos");
        }
        return new Bank(name, cod);
    }
}
