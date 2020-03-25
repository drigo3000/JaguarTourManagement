/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.photo;

import com.swevolution.jsf.webutils.JsfUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.imgscalr.Scalr;

/**
 *
 * @author Rxkx
 */
@Named
@Stateless
public class PhotoManagerUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PhotoManagerUtil.class.getName());

    public void uploadReportPhoto(InputStream is, String fileName, String path) {
        try {
            createFolderStructure(path);
            BufferedImage originalImage = ImageIO.read(is);
            BufferedImage highResImage = Scalr.resize(originalImage,
                    Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    640, Scalr.OP_ANTIALIAS);

            //File originalFile = new File(configuracionOperadora.getPathPhotos()+ fileName);
            File highResFile = new File(path
                    + fileName);

            //ImageIO.write(originalImage, "jpg", originalFile);
            ImageIO.write(highResImage, "jpg", highResFile);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void uploadSearchPhoto(InputStream is, String fileName, String path) {

        try {
            createFolderStructure(path);
            BufferedImage originalImage = ImageIO.read(is);

            BufferedImage lowResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    260, Scalr.OP_ANTIALIAS);
            BufferedImage midResImage = Scalr.resize(originalImage,
                    Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH,
                    360, Scalr.OP_ANTIALIAS);
            BufferedImage highResImage = Scalr.resize(originalImage,
                    Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    640, Scalr.OP_ANTIALIAS);
            BufferedImage hdResImage = Scalr.resize(originalImage,
                    Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    1200, Scalr.OP_ANTIALIAS);

            //File originalFile = new File(configuracionOperadora.getPathPhotos()+ fileName);
            File lowResFile = new File(path
                    + "low_search_" + fileName);
            File midResFile = new File(path
                    + "mid_search_" + fileName);
            File highResFile = new File(path
                    + "high_search_" + fileName);
            File hdResFile = new File(path
                    + "hd_search_" + fileName);

            //ImageIO.write(originalImage, "jpg", originalFile);
            ImageIO.write(lowResImage, "jpg", lowResFile);
            ImageIO.write(midResImage, "jpg", midResFile);
            ImageIO.write(highResImage, "jpg", highResFile);
            ImageIO.write(hdResImage, "jpg", hdResFile);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void uploadPhotoList(InputStream is, String fileName, String path, String dif) {
        try {
            createFolderStructure(path);
            BufferedImage originalImage = ImageIO.read(is);

            BufferedImage iconResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    96, Scalr.OP_ANTIALIAS);
            BufferedImage lowResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    260, Scalr.OP_ANTIALIAS);
            BufferedImage midResImage = Scalr.resize(originalImage,
                    Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH,
                    640, Scalr.OP_ANTIALIAS);
            BufferedImage highResImage = Scalr.resize(originalImage,
                    Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    960, Scalr.OP_ANTIALIAS);
            BufferedImage hdResImage = Scalr.resize(originalImage,
                    Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    1600, Scalr.OP_ANTIALIAS);

            //File originalFile = new File(configuracionOperadora.getPathPhotos()+ fileName);
            File iconResFile = new File(path
                    + "icon_" + dif + "_" + fileName);
            File lowResFile = new File(path
                    + "low_" + dif + "_" + fileName);
            File midResFile = new File(path
                    + "mid_" + dif + "_" + fileName);
            File highResFile = new File(path
                    + "high_" + dif + "_" + fileName);
            File hdResFile = new File(path
                    + "hd_" + dif + "_" + fileName);

            //ImageIO.write(originalImage, "jpg", originalFile);
            ImageIO.write(iconResImage, "jpg", iconResFile);
            ImageIO.write(lowResImage, "jpg", lowResFile);
            ImageIO.write(midResImage, "jpg", midResFile);
            ImageIO.write(highResImage, "jpg", highResFile);
            ImageIO.write(hdResImage, "jpg", hdResFile);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void deleteFiles(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    public String getSaveLocationFolder(String entity, String login) {
        StringBuilder sb = new StringBuilder(JsfUtil.getParameterValue("pathResources"));
        sb.append(entity).append("/");
        if (login != null && !login.isEmpty()) {
            sb.append(login).append("/");
        }
        String path = sb.toString();
        createFolderStructure(path);
        return path;
    }

    private void createFolderStructure(String path) {
        File folders = new File(path);
        if (!folders.exists()) {
            folders.mkdirs();
        }
    }
}
