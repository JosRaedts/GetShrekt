/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import com.photoshop.models.imgdata.Imgdata;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component
public class ImageManager {

    public ImageManager() {
        
    }
    
    
    public BufferedImage resize(Image originalImage, int newHeight , int newWidth) {
        BufferedImage resizeImage = null;
        if (originalImage != null) {
            resizeImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizeImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();
        }
        return resizeImage;
    }    
    
    public static BufferedImage toGrayScale(BufferedImage img) {
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(img, gray);
        
        return gray;
    }

    public static BufferedImage toSepia(BufferedImage img, int sepiaIntensity) {

        BufferedImage sepia = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        // Play around with this.  20 works well and was recommended
        //   by another developer. 0 produces black/white image
        int sepiaDepth = 20;

        int w = img.getWidth();
        int h = img.getHeight();

        WritableRaster raster = sepia.getRaster();

        // We need 3 integers (for R,G,B color values) per pixel.
        int[] pixels = new int[w * h * 3];
        img.getRaster().getPixels(0, 0, w, h, pixels);

        //  Process 3 ints at a time for each pixel.  Each pixel has 3 RGB
        //    colors in array
        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i];
            int g = pixels[i + 1];
            int b = pixels[i + 2];

            int gry = (r + g + b) / 3;
            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            // Darken blue color to increase sepia effect
            b -= sepiaIntensity;

            // normalize if out of bounds
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            pixels[i] = r;
            pixels[i + 1] = g;
            pixels[i + 2] = b;
        }
        raster.setPixels(0, 0, w, h, pixels);

        return sepia;
    }
    
    public static BufferedImage crop(Imgdata imgdata, BufferedImage img, String type)
    {
        BufferedImage dest = null;
        if(type == "lowres")
        {
            dest = img.getSubimage((int)imgdata.getX(), (int)imgdata.getY(), (int)imgdata.getWidth(), (int)imgdata.getHeight());
        }  
        else if(type == "normal")
        {
            //scale
        }
        else if(type == "thumb")
        {
            //scale
        }
        return dest;
    }
}
