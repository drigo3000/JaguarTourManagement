/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.photo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class PhotoFileUploadController implements Serializable {

    private List<UploadedFile> uploadedFiles;

    @PostConstruct
    public void init() {
        reset();
    }

    public void remove(UploadedFile file) {
        uploadedFiles.remove(file);
    }

    public void upload(FileUploadEvent event) {
        uploadedFiles.add(event.getFile());
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public void reset() {
        uploadedFiles = new ArrayList<>();
    }

}
