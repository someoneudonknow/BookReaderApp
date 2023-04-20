/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Blob;
/**
 *
 * @author admin
 */
public class UserModel {
    private int id;
    private String userName;
    private String password;
    private String phoneNumber;
    private Blob avatar;
    private boolean isManager;
    private int managerId;
    
    private UserModel() {}

    public UserModel(int id, String userName, String password, String phoneNumber, Blob avatar, boolean isManager, int managerId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.isManager = isManager;
        this.managerId = managerId;
    }

    public UserModel(String userName, String password, String phoneNumber, Blob avatar, int managerId) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.isManager = false;
        this.managerId = managerId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "UserModel{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", avatar=" + avatar + ", isManager=" + isManager + ", managerId=" + managerId + '}';
    }
}
