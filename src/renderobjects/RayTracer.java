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
public class RayTracer {
    SolidObject[] Objects;
    LightObject[] Lights;
    
    public RayTracer(SolidObject[] solidObjectsList, LightObject[] lightsList){
        this.Objects = solidObjectsList;
        this.Lights = lightsList;
    }
    
    public RGBA traceRay(Vector3 Origin, Vector3 Viewport, double tMin, double tMax, int depth){
        double distance = Double.MAX_VALUE;
        RGBA retColor = new RGBA();
        SolidObject closestObject = findClosestObject(Origin, Viewport, tMin, tMax);
        
        if (closestObject != null){
            Material material = closestObject.getMatAtIntercept(Origin, Viewport);
            Vector3 normal = closestObject.getInterceptNormal(Origin, Viewport, tMin, tMax);
            Vector3 point = closestObject.getInterceptPoint(Origin, Viewport, tMin, tMax);
            //System.out.println((closestObject instanceof Sphere) ? "Sphere" : "Mesh");
            retColor = material.Color.Multiply(ComputeLighting(
                            point, 
                            normal, 
                            Viewport.Multiply(-1), closestObject.getMatAtIntercept(Origin, Viewport).Specular)
                    );
            
            //The material is not reflective or we reached the max depth
            if (material.Reflective <= 0 || depth <= 0){
                return retColor;
            }
            
            // Compute the reflected color
            Vector3 reflectRay = reflectRay(Viewport.Multiply(-1), normal);
            RGBA reflectedColor = traceRay(point, reflectRay, 0.001, Double.MAX_VALUE, depth - 1);
        
            return retColor.Multiply(1 - material.Reflective).Add(reflectedColor.Multiply(material.Reflective));
        }
        
        return retColor;
    }
    
    private Vector3 reflectRay(Vector3 Ray, Vector3 Normal){
        return Normal.Multiply(Normal.DotProduct(Ray) * 2).Subtract(Ray);
    }
    
    private SolidObject findClosestObject(Vector3 Origin, Vector3 Viewport, double tMin, double tMax){
        double distance = Double.MAX_VALUE;
        SolidObject closestObject = null;
        for (SolidObject o : Objects){
            Double d = o.getClosestIntercept(Origin, Viewport, tMin, tMax);
            if (d != null && d < distance && d > tMin && d < tMax){
                //System.out.println(d + " Success");
                distance = d;
                closestObject = o;
            }
        }
        //System.out.println("Return");
        return closestObject;
    }
    
    private double ComputeLighting(Vector3 Point, Vector3 Normal, Vector3 Viewpoint, int specular){
        double intensity = 0.0;
        
        for (LightObject l : Lights){
            if (l instanceof AmbientLight){
                intensity += l.getDiffuseIntensityAtPoint(Point, Normal);
            } else {                
                //Shadow check
                Vector3 Point2 = (l instanceof SpotLight) ? 
                                    ((SpotLight)l).getDirection() : ((PointLight)l).getLocation().Subtract(Point);
                double MaxDistance = (l instanceof SpotLight) ? Double.MAX_VALUE : 1.0;
                //if (l instanceof PointLight){
                //    System.out.println("Loc " + ((PointLight)l).getLocation());
                //}
                //System.out.println(((l instanceof PointLight)?"point":"directional")+" "+Point + " " + Point2 + " " + MaxDistance);
                //System.out.println(((l instanceof PointLight)?"point":"directional")+" "+Point2);
                //System.out.println("Start");
                SolidObject shadow = findClosestObject(Point, Point2, 0.001, MaxDistance);
                
                if (shadow != null){  
                    //System.out.println(shadow.getClosestIntercept(Point, Point2, 0.001, MaxDistance));
                    continue;
                }
                
                //Diffuse
                intensity += l.getDiffuseIntensityAtPoint(Point, Normal);
                
                //Specular
                intensity += l.getSpecularIntensityAtPoint(Point, Viewpoint, Normal, specular);
                //System.out.println(intensity);
            }
        }
        return intensity;
    }
}
