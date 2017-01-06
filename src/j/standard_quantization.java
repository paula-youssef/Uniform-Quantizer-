package j;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class standard_quantization {
	
	public static int width=0;
    public static int height=0;
    public static double MSE = 0;
    public static int[][] pixels;
    public static int[][] pixelRange;
    ArrayList<Info> infList = new ArrayList<Info>() ;
    
  //Read image-------------------------------------------------------
    
    public static void readImage(String filePath)
    {
        File file=new File(filePath);
        BufferedImage image=null;
        try
        {
            image=ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

          width=image.getWidth();
          height=image.getHeight();
        pixels = new int[height][width];

        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                int rgb=image.getRGB(x, y);
                int alpha=(rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x]=r;
            }
        }
    }
//-----------------------------------------------------------------
    public void checkRange(int levelNum){
    	double RangSize = (256/(double)levelNum);
    	double i = 0;
    	
    	for(int l = 0; l < levelNum ; l++ ){
	    	Info ob = new Info(i,(i+RangSize),l);
	    		ob.mid();
	    		infList.add(ob);
	    		i += RangSize;
	    		}
    	
//   	for (int s = 0 ; s < infList.size();s++){
//    		System.out.println(infList.get(s).start);
//   		System.out.println(infList.get(s).end);
//   		System.out.println(infList.get(s).mid);
//   	}
    	
/*    	System.out.println(width);
    	System.out.println(height);
*/   	
   	pixelRange = new int[width][height];
   	
    	for( int w = 0 ; w < width ; w++ ){
    		for( int h = 0 ; h < height ; h++ ){
    			for( int list = 0 ; list < infList.size() ; list++){
    				if(pixels[w][h] >= (infList.get(list)).start && pixels[w][h] < (infList.get(list)).end){
    					(pixelRange[w][h]) =(int) ((infList.get(list)).mid);
    					}
    				}
    			}
    		}
    	
	   	for( int w = 0 ; w < width ; w++ ){
	    		for( int h = 0 ; h < height ; h++ ){
	    			MSE += Math.pow((pixels[w][h]-pixelRange[w][h]),2);
	    		}
	    	}
	   	
   	 MSE = (MSE/(width*height));
   	 
   	 System.out.println("The MSE =  "+MSE);
    }
    
  
    
    public void writeToFile(){
    	try {
            File file = new File("compressedData.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            
            output.write(String.valueOf(width));
            output.newLine();;
            output.write(String.valueOf(height));
            output.newLine();
            output.write(String.valueOf(infList.size()));
            output.newLine();
            //output.write("|");
            
            for(int i = 0 ; i < infList.size() ; i++){
            	output.write(String.valueOf(infList.get(i).name));
            	output.newLine();
            	output.write(String.valueOf(infList.get(i).start));
            	output.newLine();
            	output.write(String.valueOf(infList.get(i).end));
            	output.newLine();
            	output.write(String.valueOf(infList.get(i).mid));
            	output.newLine();
            	}
            
            //output.write("||");
            
            for( int w = 0 ; w < width ; w++ ){
            	for( int h = 0 ; h < height ; h++ ){
            		for( int lis = 0 ; lis < infList.size() ; lis++ ){
            			if( pixelRange[w][h] == infList.get(lis).mid){
            				output.write(String.valueOf(infList.get(lis).name));
            				output.newLine();
            			}
            		}
            	}
            }
            output.close();
    	}catch ( IOException e ) {
            e.printStackTrace();
         }
    }

}
