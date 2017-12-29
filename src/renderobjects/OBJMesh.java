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
public class OBJMesh extends SolidObject{
    private final Face[] Triangles;
    
    public Vector3 Translation;
    public double[][] Rotation;
    public double Scale;
    
    public Material Mat;
    
    public OBJMesh(Face[] tris){
        this.Triangles = tris;
    }
    
    public void Translate(Vector3 translation){
        Translation.Add(translation);
    }
    
    public void Scale(double scale){
        Scale *= scale;
    }
    
    private Vector3 calculateNormal(Face f){
        Vector3 A = f.A;
        Vector3 B = f.B;
        Vector3 C = f.C;
        Vector3 N = B.Subtract(A);
        Vector3 M = C.Subtract(A);
        return N.CrossProduct(M);
    }
    
    private Face findClosestFace(Vector3 origin, Vector3 ray){
        double Distance = Double.MAX_VALUE;
        Face retFace = null;
        for (Face f : Triangles){
            //Calculate a point on the face and a normal for the plane
            Vector3 Normal = calculateNormal(f);
            
            //Check if the ray is perpendicular to the face
            if (ray.DotProduct(Normal) == 0){
                continue;
            }
            
            //Find the intersection on the plane
            double intercept = f.A.Subtract(origin).DotProduct(Normal)/ray.DotProduct(Normal);

            Vector3 Point = origin.Add(ray.Multiply(Distance));

            if (!f.PointOnPlane(Point)){
                continue;
            }
            
            //If we got here we have a match
            //Check if this face is closer than the last collision
            if (intercept < Distance){
                Distance = intercept;
                retFace = f;
            }
        }
        return retFace;
    }
    
    @Override
    public Double getClosestIntercept(Vector3 origin, Vector3 ray, double min, double max){
        Double Distance = super.getCachedClosestDistance(origin, ray);
        if (Distance != null){
            return Distance;
        }

        //In case not intercept is found
        Distance = null;
        
        //Find the closest face
        Face f = findClosestFace(origin, ray);
        
        if (f != null){
            //Find the normal of face f
            Vector3 normal = calculateNormal(f);

            //Find the intersection on face f
            Distance = f.A.Subtract(origin).DotProduct(normal)/ray.DotProduct(normal);

            //Cache the normal and distance for later use
            super.cacheInterceptNormal(origin, ray, normal);
            super.cacheClosestDistance(origin, ray, Distance);
        }
        
        return Distance;
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
        Vector3 retNormal = super.getCachedInterceptNormal(origin, ray);
        if (retNormal != null){
            return retNormal;
        }
        
        return calculateNormal(findClosestFace(origin, ray));
    }
    
    @Override
    public Material getMatAtIntercept(Vector3 origin, Vector3 ray){
        return this.Mat;
    }
}
