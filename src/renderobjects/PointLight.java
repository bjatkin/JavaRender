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
public class PointLight extends LightObject {
    private Vector3 location;
    
    public PointLight(Vector3 location, double intensity){
        this(location, intensity, new RGBA());
    }
    
    public PointLight(Vector3 location, double intensity, RGBA color){
        super(intensity, color);
        this.location = location;
    }
    
    public Vector3 getLocation(){
        return this.location;
    }
    
    public void setLocation(Vector3 newLocation){
        this.location = newLocation;
    }
    
    @Override
    public double getDiffuseIntensityAtPoint(Vector3 point, Vector3 normal){
        Vector3 LightRay = this.getLocation().Subtract(point);
        double NormalDotLightRay = normal.DotProduct(LightRay);
        if (NormalDotLightRay > 0){
            return this.getIntensity()*NormalDotLightRay/(normal.Length()* LightRay.Length());
        }
        return 0;
    }
    
    @Override
    public double getSpecularIntensityAtPoint(Vector3 point, Vector3 viewpoint, Vector3 normal, double specular){
        if (specular == -1){return 0;}
        Vector3 LightRay = this.getLocation().Subtract(point);
        Vector3 reflection = normal.Multiply(normal.DotProduct(LightRay) * 2).Subtract(LightRay);
        double RefelectDotView = reflection.DotProduct(viewpoint);
        if (RefelectDotView > 0) {
            return this.getIntensity()*Math.pow(RefelectDotView/(reflection.Length()*viewpoint.Length()), specular);
        }
        return 0;
    }
}


