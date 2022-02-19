/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multibandimageclustering2;
import ALI.*;
/**
 *
 * @author user
 */
public class MultibandImageClustering2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        VectorLib vlib = new VectorLib();
        ImageLib img =new ImageLib();
        
        int[][][] rgb_colors=null;
        String srcdir="C:\\Users\\user\\Desktop\\multiband_images/";
        String srcfile;
        
        int[] size = img.getSize(srcdir+"gb0.gif");
        int[][] gray_colors=null;
        int[] gray_data=null;
        int n=size[0]*size[1];
        int[][] metadata=new int[n][6];
        
        for(int i=0; i<6; i++)
        {
            srcfile=srcdir+"gb"+String.valueOf(i)+".gif";
            rgb_colors=img.getRGB(srcfile);
            
            gray_colors=img.RGB_to_Gray_Color(rgb_colors);
            gray_data=img.Image_to_Data(gray_colors);
            
            for(int j=0; j<n; j++)
                metadata[j][i]=gray_data[j];
        }
        
        ClusteringLib clib=new ClusteringLib();
        int[] clusters=clib.Clustering("average",metadata,4);
        
        int[][][] output_colors=img.Data_to_RGBImage(clusters,size[0],size[1]);
        img.saveImage(output_colors,srcdir+"output.gif","gif");
    }
    
}
