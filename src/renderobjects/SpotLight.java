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
public class SpotLight extends LightObject {
    private Vector3 direction;
    
    public SpotLight(Vector3 direction, double intensity){
        this(direction, intensity, new RGBA());
    }
    
    public SpotLight(Vector3 direction, double intensity, RGBA color){
        super(intensity, color);
        this.direction = direction;
    }
    
    public Vector3 getDirection(){
        return this.direction;
    }
    
    public void setDirection(Vector3 newDirection){
        this.direction = newDirection;
    }
    
    @Override
    public double getDiffuseIntensityAtPoint(Vector3 point, Vector3 normal){
        Vector3 LightRay = this.direction;
        double NormalDotLightRay = normal.DotProduct(LightRay);
        if (NormalDotLightRay > 0){
            return this.getIntensity()*NormalDotLightRay/(normal.Length()* LightRay.Length());
        }
        return 0;
    }
    
    @Override
    public double getSpecularIntensityAtPoint(Vector3 point, Vector3 viewpoint, Vector3 normal, double specular){
        if (specular == -1){return 0;}
        Vector3 reflection = normal.Multiply(normal.DotProduct(this.direction) * 2).Subtract(this.direction);
        double RefelectDotView = reflection.DotProduct(viewpoint);
        if (RefelectDotView > 0) {
            return this.getIntensity()*Math.pow(RefelectDotView/(reflection.Length()*viewpoint.Length()), specular);
        }
        return 0;
    }
}
