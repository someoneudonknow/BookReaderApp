/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package other;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class SetButton {
    
    public static class SetBtnBack extends MouseAdapter{
        JLabel btnBack;
        
        public SetBtnBack(JLabel btnBack) {
            this.btnBack = btnBack;
            this.btnBack.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(0, 204, 102));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(0,153,51));
        }
    }
    
    public static class SetBtnBackB extends MouseAdapter{
        JLabel btnBack;
        
        public SetBtnBackB(JLabel btnBack) {
            this.btnBack = btnBack;
            this.btnBack.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(255,255,255));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(242,242,242));
        }
    }
    
}
