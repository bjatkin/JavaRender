/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileparsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import renderobjects.Vector3;
import renderobjects.OBJMesh;
import renderobjects.Face;
import renderobjects.RGBA;
import renderobjects.Material;

/**
 *
 * @author brandon
 */
public class objParser {
    private static OBJMesh ConstructObject(ArrayList<Vector3> vertexes, ArrayList<int[]> faces){
        Vector3[] v = vertexes.toArray(new Vector3[vertexes.size()]);
        
        //Triagulate the mesh
        for (int i = 0; i < faces.size(); i++){
            if (faces.get(i).length > 3){
                for (int index = 2; index < faces.get(i).length - 1; index++){
                    faces.add(new int[]{faces.get(i)[0], faces.get(i)[index], faces.get(i)[index + 1]});
                }
                faces.set(i, new int[]{faces.get(i)[0], faces.get(i)[1], faces.get(i)[2]});
            }
        }
        
        //Create the array of faces
        Face[] f = new Face[faces.size()];
        int count = 0;
        for (int[] i : faces){
            f[count] = new Face(vertexes.get(i[0]), vertexes.get(i[1]), vertexes.get(i[2]), new RGBA(255, 180, 180, 180));
            count++;
        }
        
        //Generate the OBJMesh
        OBJMesh ret = new OBJMesh(f);
        ret.Mat = new Material(new RGBA(255, 180, 180, 180), 10, 0);
        return ret;
    }
    
    public static OBJMesh ParseObjFile(File data){
        ArrayList<Vector3> vertexes = new ArrayList<>();
        ArrayList<Vector3> normals = new ArrayList<>();
        ArrayList<int[]> faces = new ArrayList<>();
        ArrayList<int[]> faceNormals = new ArrayList<>();
        OBJMesh retObject = null;
        try{
            FileReader fileReader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                line = line.trim();
                char firstChar = line.charAt(0);
                if (firstChar == '#'){
                    continue;
                }
                if (firstChar == 'v'){
                    //We found a vertex or vertex normal
                    if (line.charAt(1) == 'n'){
                        //Vertex Normal
                        String[] Vectors = line.substring(3).split(" ");
                        normals.add(new Vector3(Double.valueOf(Vectors[0]), Double.valueOf(Vectors[1]), Double.valueOf(Vectors[2])));
                    } else {
                        //Regular Vertex
                        String[] Points = line.substring(2).split(" ");
                        //System.out.println(Arrays.toString(Points));
                        vertexes.add(new Vector3(Double.valueOf(Points[0]), Double.valueOf(Points[1]), Double.valueOf(Points[2])));
                    }
                        
                }
                if (firstChar == 'f'){
                    //This is our list of faces
                    String[] FacesAndNormals = line.substring(2).split(" ");
                    int[] Faces = new int[FacesAndNormals.length];
                    int[] Normals = new int[FacesAndNormals.length];
                    int[] Normals2 = new int[FacesAndNormals.length];
                    
                    int count = 0;
                    for (String s : FacesAndNormals){
                        Faces[count] = Integer.valueOf(s.split("/")[0]) - 1;
                        //Normals[count] = Integer.valueOf(s.split("/")[1]);
                        Normals2[count] = Integer.valueOf(s.split("/")[2]) - 1;
                        count++;
                    }
                    faces.add(Faces);
                    faceNormals.add(Normals2);
                }
            }
            retObject = ConstructObject(vertexes, faces);
        }catch(Exception e){
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return retObject;
        
    }
}
