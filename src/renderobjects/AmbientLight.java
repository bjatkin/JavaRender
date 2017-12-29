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
public class AmbientLight extends LightObject{
    //Nothing new to add here yet
    public AmbientLight(double intensity){
        this(intensity, new RGBA());
    }
    
    public AmbientLight(double intensity, RGBA color){
        super(intensity, color);
    }
    
    @Override
    public double getDiffuseIntensityAtPoint(Vector3 point, Vector3 normal){
        return this.getIntensity();
    }
    
    @Override
    public double getSpecularIntensityAtPoint(Vector3 point, Vector3 viewpoint, Vector3 normal, double specular){
        return 0;
    }
}
