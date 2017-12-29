/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderobjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author brandon
 */
public class Image {
    private BufferedImage JpegImage;
    private int Width;
    private int Height;
    
    public Image(int width, int height){
        this(width, height, new RGBA());
    }
    
    public Image(int width, int height, RGBA color){
        this.JpegImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.Width = width;
        this.Height = height;
        for (int w = 0; w < width; w++){
          for (int h = 0; h < height; h++){
              JpegImage.setRGB(w, h, color.GetColor());
          }  
        }
    }
    
    public int getWidth(){
        return Width;
    }
    
    public int getHeight(){
        return Height;
    }
    
    public boolean SaveImage(String FilePath){
        try{
            File tempFile = new File(FilePath);
            ImageIO.write(JpegImage, "jpg", tempFile);
        }catch(IOException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public void PutPixel(int x, int y, RGBA color){
        if (x < JpegImage.getWidth() && x >= 0 && y < JpegImage.getWidth() && y >= 0){
        JpegImage.setRGB(x, y, color.GetColor());
        }
    }
    
    public boolean SaveImage(){
        return SaveImage("/Users/brandon/Desktop/Default_Image.jpg");
    }
}  