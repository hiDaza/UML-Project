/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.umlproject;

import java.util.List;

/**
 *
 * @author daza
 */
public class PixController {
    private BankAccount origin;
    
    
    public PixController(BankAccount origin){
        this.origin = origin;
    }
    
    public boolean sendPix(String targetKey, double value, List<PixKey> allkeys){
        PixKey target = null;
        
       
        
        for(PixKey pk : allkeys){
            if(pk.getKey().equals(targetKey)){
                target = pk;
                break;
            }
        }
         if(value <= 0){
            System.out.println("Invalid value!!!");
            return false;
        }
         
         if (target.getBankAccount() == this.origin) {
        System.out.println("Cannot send PIX to your own account!");
        return false;
        }
        if(target == null){
            System.out.println("Pix key not found!!!!");
            return false;
        }
        
        if(origin.debit(value)){
            target.getBankAccount().deposit(value);
            System.out.println("Pix sent successfully!");
            System.out.println("LOG: Sent R$" + value + " from " + origin.getHolder() + " to " + target.getBankAccount().getHolder());
            return true;
        }else{
            System.out.println("INSUFFICIENT BALANCE!");
            return false;
        }
    }
}
