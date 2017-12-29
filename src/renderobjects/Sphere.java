/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderobjects;

import java.util.Arrays;

/**
 *
 * @author brandon
 */
public class Sphere extends SolidObject{
    public double Radius;
    public Material Mat;
    
    public Sphere(Vector3 center, double radius, Material mat){
        this.location = center;
        this.Radius = radius;
        this.Mat = mat;
    }
    
    @Override
    public Double getClosestIntercept(Vector3 origin, Vector3 ray, double min, double max){
        Double distance = super.getCachedClosestDistance(origin, ray);
        if (distance != null){
            return distance;
        }
        
        Vector3 C = this.location;
        double r = this.Radius;
        Vector3 OC = origin.Subtract(C);
        double[] Intersections = new double[2];
        
        double k1 = ray.DotProduct(ray);
        double k2 = 2 * ray.DotProduct(OC);
        double k3 = OC.DotProduct(OC) - r * r;
        
        double discriminant = k2 * k2 - 4*k1*k3;
        if (discriminant < 0){
            return null;
        }
        
        Intersections[0] = (-k2 + Math.sqrt(discriminant)) / (2*k1);
        Intersections[1] = (-k2 - Math.sqrt(discriminant)) / (2*k1);
        //System.out.println(Arrays.toString(Intersections));
        
        distance = (Intersections[0] > min && Intersections[0] < max) ? Intersections[0] : Double.MAX_VALUE;
        distance = (Intersections[1] > min && Intersections[1] < max && Intersections[1] < distance) ? Intersections[1] : distance;
        //System.out.println(distance);
        //double Distance = Intersections[0] < Intersections[1] ? Intersections[0] : Intersections[1];
        super.cacheClosestDistance(origin, ray, distance);
        return distance;
    }

    @Override
    public Vector3 getInterceptPoint(Vector3 origin, Vector3 ray, double min, double max){
        Vector3 retPoint = super.getCachedInterceptPoint(origin, ray);
        if (retPoint != null){
            return retPoint;
        }
        
        return origin.Add(ray.Multiply(this.getClosestIntercept(origin, ray, min, max)));
    }
    
    @Override
    public Vector3 getInterceptNormal(Vector3 origin, Vector3 ray, double min, double max){
        Vector3 normal = getInterceptPoint(origin, ray, min, max).Subtract(location);
        return normal.Divide(normal.Length());
    }
    
    @Override
    public Material getMatAtIntercept(Vector3 origin, Vector3 ray){
        return this.Mat;
    }
}
