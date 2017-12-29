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
public class Vector3 {
    public double x;
    public double y;
    public double z;
    
    public Vector3(double x, double y, double z){
        Set(x ,y, z);
    }
    
    public final void Set(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean Equals(Vector3 v){
        return (v.x == this.x && v.y == this.y && v.z == this.z);
    }
    
    public Vector3 Add(Vector3 adder){
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = x + adder.x;
        retVector.y = y + adder.y;
        retVector.z = z + adder.z;
        return retVector;
    }
    
    public Vector3 Subtract(Vector3 subtractor){
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = x - subtractor.x;
        retVector.y = y - subtractor.y;
        retVector.z = z - subtractor.z;
        return retVector;
    }
    
    public Vector3 Subtract(double subtractor){
        return Subtract(new Vector3(subtractor, subtractor, subtractor));
    }
    
    public double DotProduct(Vector3 Product){
        return this.x * Product.x + y * Product.y + z * Product.z;
    }
    
    public Vector3 CrossProduct(Vector3 Product){
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = this.y*Product.z - this.z*Product.y;
        retVector.y = this.z*Product.x - this.x*Product.z;
        retVector.z = this.x*Product.y - this.y*Product.x;
        return retVector;
    }
    
    public Vector3 Multiply(double multiplyer){
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = x * multiplyer;
        retVector.y = y * multiplyer;
        retVector.z = z * multiplyer;
        return retVector;
    }
    
    public Vector3 MultiplyMV(double[][] multiplyer){
        double[] retMat = new double[]{0, 0, 0,};
        double[] vec = new double[]{x, y, z};
        
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                retMat[row] += vec[col] * multiplyer[row][col];
            }
        }
        
        return new Vector3(retMat[0], retMat[1], retMat[2]);
    }
    
    public Vector3 Divide(double Divisor){
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = x / Divisor;
        retVector.y = y / Divisor;
        retVector.z = z / Divisor;
        return retVector;
    }
    
    public Vector3 SqrtRoot(){
        if (x < 0 || y < 0 || z < 0){ return null; }
        Vector3 retVector = new Vector3(0, 0, 0);
        retVector.x = Math.sqrt(x);
        retVector.y = Math.sqrt(y);
        retVector.z = Math.sqrt(z);
        return retVector;
    }
    
    public double Length(){
        double retVal;
        retVal = x*x + y*y + z*z;
        retVal = Math.sqrt(retVal);
        return retVal;
    }
    
    @Override
    public String toString(){
        return "{" + this.x + ", " + this.y + ", " + this.z + "}";
    } 
}
