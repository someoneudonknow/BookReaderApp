/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.view;

import views.AddInforChapter;

/**
 *
 * @author ADMIN
 */
public class AddInforChapterController {
    AddInforChapter inforChapter;

    public AddInforChapterController(AddInforChapter inforChapter, int book_id) {
        this.inforChapter = inforChapter;
        
        this.inforChapter.onBtnConfirm(e -> {
            createChapter();
            this.inforChapter.dispose();
        });
        
        this.inforChapter.onBtnCancel(e -> {
            this.inforChapter.dispose();
        });
        
        this.inforChapter.onBtnAddFile(e -> {
        
        });
        
        this.inforChapter.start();
    }
    
    public void createChapter() {
        
    }
}
