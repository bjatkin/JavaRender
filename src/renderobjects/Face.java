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
public class Face {
    public Vector3 A;
    public Vector3 B;
    public Vector3 C;
    public RGBA Color;
    
    public Face(Vector3 a, Vector3 b, Vector3 c, RGBA color){
        this.A = a;
        this.B = b;
        this.C = c;
        this.Color = color;
    }
    
    public boolean PointOnPlane(Vector3 Point){
        //Use barycentric coodinates to check if the point is on the line
        Vector3 u = B.Subtract(A);
        Vector3 v = C.Subtract(A);
        Vector3 n = u.CrossProduct(v);
        Vector3 w = Point.Subtract(A);
        double Gama = (u.CrossProduct(w).DotProduct(n))/n.DotProduct(n);
        double Beta = (w.CrossProduct(v).DotProduct(n))/n.DotProduct(n);
        double Alpha = 1 - Gama - Beta;
        
        return (Alpha >= 0 && Alpha <= 1 && Beta >= 0 && Beta <= 1 && Gama >= 0 && Gama <= 1);
    }
}
