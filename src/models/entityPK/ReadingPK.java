/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entityPK;

/**
 *
 * @author trang
 */
public class ReadingPK {

    private int user_id;
    private int chapter_id;

    public ReadingPK() {
    }

    public ReadingPK(int user_id, int chapter_id) {
        this.user_id = user_id;
        this.chapter_id = chapter_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

}
