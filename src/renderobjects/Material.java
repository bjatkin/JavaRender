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
public class Material {
    public int Specular;
    public double Reflective;
    public RGBA Color;

    public Material(){
        this(new RGBA(), -1, 0);
    }

    public Material(RGBA color, int specular, double reflect){
        this.Specular = specular;
        this.Reflective = reflect;
        this.Color = color;
    }  
}
