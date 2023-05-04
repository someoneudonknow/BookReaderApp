/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.ChapterModel;
import other.Rules;
import other.Validate;
import views.AddInforChapter;

public class AddInforChapterController {

    private AddInforChapter inforChapter;
    private int currentBookId;
    private String docReadFromFile;
    
    public AddInforChapterController(AddInforChapter inforChapter, int book_id) {
        this.inforChapter = inforChapter;
        this.currentBookId = book_id;
        this.inforChapter.getTxtSerial().setEnabled(false);
        this.inforChapter.getTxtSerial().setEditable(false);
        
        this.inforChapter.onAutoUpdateClick(e -> {
            boolean isSelected = this.inforChapter.getAutoUpdateBtn().isSelected();
            this.inforChapter.getTxtSerial().setEnabled(!isSelected);
            this.inforChapter.getTxtSerial().setEditable(!isSelected);
        });

        this.inforChapter.onBtnCancel(e -> {
            this.inforChapter.dispose();
        });

        this.inforChapter.onBtnAddFile(e -> {
            handleAddFile();
        });

        this.inforChapter.start();
    }

    public ChapterModel getData() {
        return new ChapterModel(this.inforChapter.getTxtTitle().getText(), -1, this.docReadFromFile, this.currentBookId);
    }

    private void handleAddFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("image", "txt");
        fileChooser.setFileFilter(textFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(null, "Save");

        if (x == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            String filePath = selectedImage.getAbsolutePath();
            if (filePath.endsWith("txt")) {
                this.inforChapter.getFilePathReview().setText(filePath);
                try {
                    this.docReadFromFile = Files.readString(Path.of(filePath));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occur while reading a file, please try again!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sai định dạng file!");
            }
        }
    }

    public boolean isValid(int lastestChap) {
        String title = this.inforChapter.getTxtTitle().getText();
        String serial = this.inforChapter.getTxtSerial().getText();
        String doc = this.docReadFromFile;

        Rules[] titleRules = {new Rules("Please enter this field", Rules.IS_REQUIRED)};

        Rules[] serialRules = {new Rules("Please enter this field", Rules.IS_REQUIRED),
            new Rules("This field must be a number", Rules.IS_NUMBER), 
            new Rules("Value must be lower than " + (lastestChap + 2), Rules.IS_NUMBER_LOWER_THAN, (lastestChap + 2)),
            new Rules("Value must be greater than 0", Rules.IS_NUMBER_GREATER_THAN, 0)};

        Rules[] docRules = {new Rules("Please choose a document", Rules.IS_REQUIRED)};

        Validate titleValidate = new Validate(title, titleRules);
        Validate serialValidate = new Validate(serial, serialRules);
        Validate docValidate = new Validate(doc, docRules);

        inforChapter.getTitleErrorMessage().setText(titleValidate.getErrorMessage());
        inforChapter.getContentErrorMessage().setText(docValidate.getErrorMessage());

        boolean isSerialValid = true;
        String checkBoxErrString = "";

        if (!this.inforChapter.getAutoUpdateBtn().isSelected()) {
            isSerialValid = serialValidate.isValid();
            checkBoxErrString = serialValidate.getErrorMessage();
        }

        inforChapter.getSerialErrorMessage().setText(checkBoxErrString);
        if (titleValidate.isValid() && isSerialValid && docValidate.isValid()) {
            return true;
        }
        return false;
    }
}
