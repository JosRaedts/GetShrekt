/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component
public class ImageManager {

    public ImageManager() {
        
    }
    
    
    public BufferedImage resize(Image originalImage, int newWidth, int newHeight) {
        BufferedImage resizeImage = null;
        if (originalImage != null) {
            resizeImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizeImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();
        }
        return resizeImage;
    }
    
    public void testresize()
    {
//        Image testpicture;
//        testpicture = Toolkit.getDefaultToolkit().createImage("C:\Users\pc\Pictures");
//        System.out.println(testpicture.getHeight(null));
//        System.out.println(testpicture.getWidth(null));
//        BufferedImage bufftestpicture = (BufferedImage) testpicture;
//        resize(bufftestpicture, 100, 100);
//        System.out.println(bufftestpicture.getHeight());
//        System.out.println(bufftestpicture.getWidth());
    }
}
