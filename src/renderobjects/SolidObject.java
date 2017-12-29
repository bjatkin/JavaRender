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
public abstract class SolidObject {
    private Vector3 originCache;
    private Vector3 rayCache;
    private Vector3 interceptPointCache;
    private Vector3 interceptNormalCache;
    private double closestDistanceCache;
    
    Vector3 location;
    double[][] rotation;
    double scale;
    
    //Check Cache Status
    private boolean isClosestDistanceCached(Vector3 origin, Vector3 ray){
        return rayCache != null && 
                ray.Equals(this.rayCache) && 
                originCache != null &&
                origin.Equals(this.originCache) && 
                this.closestDistanceCache != 0;
    }

    private boolean isInterceptPointCached(Vector3 origin, Vector3 ray){
        return rayCache != null && 
                ray.Equals(this.rayCache) && 
                originCache != null &&
                origin.Equals(this.originCache) && 
                interceptPointCache != null;
    }
    
    private boolean isInterceptNormalCached(Vector3 origin, Vector3 ray){
        return rayCache != null &&
                ray.Equals(this.rayCache) && 
                originCache != null &&
                origin.Equals(this.originCache) && 
                interceptNormalCache != null;
    }
    
    //Get the Cache Status
    Double getCachedClosestDistance(Vector3 origin, Vector3 ray){
        if (isClosestDistanceCached(origin, ray)){
            return this.closestDistanceCache;
        }
        return null;
    }
    
    Vector3 getCachedInterceptPoint(Vector3 origin, Vector3 ray){
        if (isInterceptPointCached(origin, ray)){
            return this.interceptPointCache; 
        }
        return null;
    }
    
    Vector3 getCachedInterceptNormal(Vector3 origin, Vector3 ray){
        if (isInterceptNormalCached(origin, ray)){
            return this.interceptNormalCache;
        }
        return null;
    }
    
    //Update the Cache data
    void cacheClosestDistance(Vector3 origin, Vector3 ray, double distance){
        this.originCache = origin;
        this.rayCache = ray;
        this.closestDistanceCache = distance;
    }
    
    void cacheInterceptPoint(Vector3 origin, Vector3 ray, Vector3 point){
        this.originCache = origin;
        this.rayCache = ray;
        this.interceptPointCache = point;
    }
    
    void cacheInterceptNormal(Vector3 origin, Vector3 ray, Vector3 normal){
        this.originCache = origin;
        this.rayCache = ray;
        this.interceptNormalCache = normal;
    }
    
    //Abstract Methods
    abstract public Double getClosestIntercept(Vector3 origin, Vector3 ray, double min, double max);
    abstract public Vector3 getInterceptPoint(Vector3 origin, Vector3 ray, double min, double max);
    abstract public Vector3 getInterceptNormal(Vector3 origin, Vector3 ray, double min, double max);
    abstract public Material getMatAtIntercept(Vector3 origin, Vector3 ray);
    
}