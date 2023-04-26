/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import java.awt.event.MouseAdapter;
import other.SetButton;
import views.MainView;
import views.items.ButtonItem;

/**
 *
 * @author ADMIN
 */
public class ButtonViewController {
    ButtonItem buttonItem;
    MainView mainView;

    public ButtonViewController(ButtonItem buttonItem, MainView mainView) {
        this.buttonItem = buttonItem;
        this.mainView = mainView;
        this.buttonItem.getButton().setText(this.buttonItem.getName());
        SetButton.SetBtnMain events = new SetButton.SetBtnMain(this.buttonItem,this.mainView,this.buttonItem.getName());
        this.buttonItem.onButton(events);
    }
    
}
