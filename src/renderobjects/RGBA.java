/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderobjects;

/**
 *
 * @author brandon
 */
public class RGBA {
    private int Alpha;
    private int Red;
    private int Green;
    private int Blue;
    
    public RGBA (){
        SetColor(255,0,0,0);
    }
    
    public RGBA (int alpha, int red, int green, int blue){
        SetColor(alpha, red, green, blue);
    }
    
    public final void SetColor(int Alpha, int Red, int Green, int Blue){
        this.Alpha = Alpha;
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
    }
    public int GetColor(){
        return ((Alpha<<24) | (Red<<16) | (Green<<8) | Blue);
    }
    
    public RGBA Multiply(double multiplyer){
        RGBA retColor = new RGBA(Alpha, Red, Green, Blue);
        retColor.Red = (int)((double)this.Red * multiplyer);
        retColor.Red = retColor.Red > 255 ? 255 : retColor.Red;
        retColor.Green = (int)((double)this.Green * multiplyer);
        retColor.Green = retColor.Green > 255 ? 255 : retColor.Green;
        retColor.Blue = (int)((double)this.Blue * multiplyer);
        retColor.Blue = retColor.Blue > 255 ? 255 : retColor.Blue;
        return retColor;
    }
    
    public RGBA Add(RGBA color){
        RGBA retColor = new RGBA(Alpha, Red, Green, Blue);
        retColor.Red = (int)((double)this.Red + color.Red);
        retColor.Red = retColor.Red > 255 ? 255 : retColor.Red;
        retColor.Green = (int)((double)this.Green + color.Green);
        retColor.Green = retColor.Green > 255 ? 255 : retColor.Green;
        retColor.Blue = (int)((double)this.Blue + color.Blue);
        retColor.Blue = retColor.Blue > 255 ? 255 : retColor.Blue;
        return retColor;
    }
    
    @Override
    public String toString(){
        return "A: "+Alpha+", R: "+Red+", G: "+Green+", B: "+Blue;
    } 
}
