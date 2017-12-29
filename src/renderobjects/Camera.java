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
public class Camera {
    public Vector3 Position;
    public double[][] Rotation;
    public double ViewportHeight;
    public double ViewportWidth;
    public double ProjectionPlaneDistance;
    public int recursionDepth;
    
    public Camera(Vector3 origin){
        this.Position = origin;
        this.Rotation = new double[][]{new double[]{1,0,0}, new double[]{0,1,0}, new double[]{0,0,1}};
        this.ViewportHeight = 1;
        this.ViewportWidth = 1;
        this.ProjectionPlaneDistance = 1;
        this.recursionDepth = 1;
    }   
}
