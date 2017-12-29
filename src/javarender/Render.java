/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarender;

import fileparsers.objParser;
import java.io.File;
import renderobjects.*;

/**
 *
 * @author brandon
 */
public class Render {
    Image Canvas = new Image(1028, 1028, new RGBA(255, 255, 255, 255));
    Camera camera = new Camera(new Vector3(0,0,0));
    RayTracer rayTracer;
    
    public Render(File dataFile){
        /*SolidObject[] g = {objParser.ParseObjFile(dataFile)};
        LightObject[] l = {new PointLight(new Vector3(0,0,0), 0.7)};
        rayTracer = new RayTracer(g, l);*/
        
        //CODE TO TEST THE ENGINE WHILE WE DONT HAVE A WORKING FILE
        SolidObject[] testg = {new Sphere(new Vector3(0, -1, 3), 1, new Material(new RGBA(255, 255, 0, 0), 500, 0.2)),
                               new Sphere(new Vector3(2, 0, 4), 1, new Material(new RGBA(255, 0, 0, 255), 500, 0.3)),
                               new Sphere(new Vector3(-2, 0, 4), 1, new Material(new RGBA(255, 0, 255, 0), 10, 0.4)),
                               new Sphere(new Vector3(0, -5001, 0), 5000, new Material(new RGBA(255, 255, 255, 0), 1000, 0.5))};
        
        LightObject[] testl = {new AmbientLight(0.2),
                               new PointLight(new Vector3(2, 1, 0), 0.6),
                               new SpotLight(new Vector3(1, 4, 4), 0.2)};
        
        rayTracer = new RayTracer(testg, testl);
        camera = new Camera(new Vector3(3, 0, 1));
        camera.Rotation = new double[][]{new double[]{0.7071, 0, -0.7071}, new double[]{0,1,0}, new double[]{0.7071,0,0.7071}};
    }
    
    public Image renderFrame(){
        int Height = Canvas.getHeight();
        int Width = Canvas.getWidth();
        
        for (int x = -Height/2; x < Height/2; x++) {
            for (int y = Width/2; y > -Width/2; y--){
                Vector3 Viewport = CanvasToViewport(x, y).MultiplyMV(camera.Rotation);
                RGBA color = rayTracer.traceRay(camera.Position, Viewport, 1, Double.MAX_VALUE, camera.recursionDepth);
                Canvas.PutPixel(x + Height/2, -y + Width/2, color);
            }
        }
        return this.Canvas;
    }
    
    public Vector3 CanvasToViewport(int x, int y){
        int Height = Canvas.getHeight();
        int Width = Canvas.getWidth();
        return new Vector3((double)x*camera.ViewportWidth/Width, (double)y*camera.ViewportHeight/Height, camera.ProjectionPlaneDistance);
    }
}
