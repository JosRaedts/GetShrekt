/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.misc;

import com.photoshop.models.imgdata.Filter;
import com.photoshop.models.imgdata.Imgdata;
import com.photoshop.models.photo.Photo;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.*;

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
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

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

    public static BufferedImage loadImage(String filename)
    {
        InputStream in = null;
        BufferedImage img = null;
        try {
            in = new FileInputStream(filename);
            img = ImageIO.read(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    public static void modding(Imgdata imgdata, Photo photo, Environment env, String location, int id) throws IOException {
        File theDir = new File(location);

        if (!theDir.exists()) {
            theDir.mkdir();
        }

        BufferedImage lowimg = loadImage(env.getProperty("uploadDir") + "low/" + photo.getLowResURL());
        BufferedImage lowdest = lowimg.getSubimage((int)imgdata.getX(), (int)imgdata.getY(), (int)imgdata.getWidth(), (int)imgdata.getHeight());
        int lowwidth = lowimg.getWidth();
        int lowheight = lowimg.getHeight();

        BufferedImage highimg = loadImage(env.getProperty("uploadDir")  + photo.getHighResURL());
        int highwidth = highimg.getWidth();
        int highheight = highimg.getHeight();
        System.out.println(imgdata.getWidth());
        System.out.println(highwidth);
        Double highScaleWidth = Double.valueOf(highwidth) / Double.valueOf(lowwidth);
        System.out.println(highScaleWidth);
        Double highScaleHeight = Double.valueOf((highheight) / Double.valueOf(lowheight));
        System.out.println(imgdata.getWidth()*highScaleWidth);
        BufferedImage highdest = highimg.getSubimage((int)(Double.valueOf(imgdata.getX())*highScaleWidth), (int)(Double.valueOf(imgdata.getY())*highScaleHeight), (int)(Double.valueOf(imgdata.getWidth())*highScaleWidth), (int)(Double.valueOf(imgdata.getHeight())*highScaleHeight));

        BufferedImage thumbimg = loadImage(env.getProperty("uploadDir") + "thumb/" + photo.getThumbnailURL());
        int thumbwidth = thumbimg.getWidth();
        int thumbheight = thumbimg.getHeight();
        Double thumbScaleWidth = Double.valueOf(thumbwidth) / Double.valueOf(lowwidth);
        System.out.println("pizza"+thumbScaleWidth+"-"+thumbwidth+"-"+lowwidth);
        Double thumbScaleHeight = Double.valueOf(thumbheight) / Double.valueOf(lowheight);
        BufferedImage thumbdest = thumbimg.getSubimage((int)(Double.valueOf(imgdata.getX())*thumbScaleWidth), (int)(Double.valueOf(imgdata.getY())*thumbScaleHeight), (int)(Double.valueOf(imgdata.getWidth())*thumbScaleWidth), (int)(Double.valueOf(imgdata.getHeight())*thumbScaleHeight));
        
        if(imgdata.getFilter() == Filter.SEPIA)
        {
            lowdest = toSepia(lowdest, 50);
            highdest = toSepia(highdest, 50);
            thumbdest = toSepia(highdest, 50);
        }
        else if(imgdata.getFilter() == Filter.BW)
        {
            lowdest = toGrayScale(lowdest);
            highdest = toGrayScale(highdest);
            thumbdest = toGrayScale(thumbdest);
        }
        
        ImageIO.write(lowdest, "jpg", new File(location+"/lowres.jpg"));
        ImageIO.write(highdest, "jpg", new File(location+"/highres.jpg"));
        ImageIO.write(thumbdest, "jpg", new File(location+"/thumb.jpg"));
    }
}
