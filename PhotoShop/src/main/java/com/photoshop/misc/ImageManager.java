/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author pc
 */
public class ImageManager {

    public ImageManager() {

    }
    
    
    public static BufferedImage resize(BufferedImage originalImage, int imageType, int newWidth, int newHeight) {
        BufferedImage resizeImage = null;
        if (originalImage != null) {
            resizeImage = new BufferedImage(newWidth, newHeight, imageType);
            Graphics2D g = resizeImage.createGraphics();
            g.drawImage(resizeImage, 0, 0, newWidth, newHeight, null);
            g.dispose();
        }
        return resizeImage;
    }
}
