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
public abstract class LightObject {
    private double intensity;
    private RGBA color;
    
    public LightObject(double intensity){
        this(intensity, new RGBA());
    }
    
    public LightObject(double intensity, RGBA color){
        this.intensity = intensity;
        this.color = color;
    }
    
    public double getIntensity(){
        return this.intensity;
    }
    
    public RGBA getColor(){
        return this.color;
    }
    
    public void setIntensity(double intensity){
        this.intensity = intensity;
    }
    
    public void setColor(RGBA newColor){
        this.color = newColor;
    }
    
    public double brighten(double amount){
        this.intensity += amount;
        return this.intensity;
    }
    
    public double darken (double amount){
        this.intensity -= amount;
        return this.intensity;
    }
    
    public abstract double getDiffuseIntensityAtPoint(Vector3 point, Vector3 normal);
    public abstract double getSpecularIntensityAtPoint(Vector3 point, Vector3 viewpoint, Vector3 normal, double specular);
}
