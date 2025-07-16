/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.umlproject;

import GUI.Form;
import javax.swing.UIManager;

/**
 *
 * @author daza
 */
public class UMLProject {

    public static void main(String[] args) {
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
        try {
            // Fallback para o Nimbus (mais moderno)
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    java.awt.EventQueue.invokeLater(() -> {
        new Form().setVisible(true);
    });
    }
}
