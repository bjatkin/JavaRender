/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarender;

import java.io.File;

/**
 *
 * @author brandon
 */
public class JavaRender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Render render = new Render(new File("/Users/brandon/Desktop/Render Engine/Monkey.obj"));
        render.renderFrame().SaveImage();
    }
    
}