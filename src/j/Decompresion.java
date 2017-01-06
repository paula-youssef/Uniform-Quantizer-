package j;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Decompresion {
	
	public static int width=0;
    public static int height=0;
    public int arrayListSize=0;
    public int[][] pixels;
    ArrayList<Info> infList = new ArrayList<Info>() ;

	public void readFile(String bath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(bath));
		/*String data = br.readLine();*/
		/*while(br.readLine() != null)
			data += br.readLine();*/
		 try {
			 /*Scanner sc = new Scanner(new File(bath));
			 sc.useDelimiter("|");
			 String line;*/
		        
		        width = Integer.parseInt(br.readLine());
//			 	line=sc.nextLine();
//		        System.out.println(line);
		        height = Integer.parseInt(br.readLine());
	/*	        System.out.println(height);
		    	line=sc.nextLine();
		        System.out.println(line);*/
		        arrayListSize = Integer.parseInt(br.readLine());
		    /*	line=sc.nextLine();
		        System.out.println(line);
		        System.out.println(arrayListSize);
		       */
		        pixels = new int [width][height];
		        
		        for ( int i = 0 ; i < arrayListSize ; i++){
		        	Info ob = new Info();
		        	ob.name = Integer.parseInt(br.readLine());
		        	//System.out.println(ob.name);
		        	ob.start = Double.parseDouble(br.readLine());
		        	//System.out.println(ob.start);
		        	ob.end = Double.parseDouble(br.readLine());
		        	//System.out.println(ob.end);
		        	ob.mid = Double.parseDouble(br.readLine());
		        	//System.out.println(ob.mid);
		        	infList.add(ob);
		        }
		        
		        int n=0;
		       // System.out.print(width);
		        //System.out.print(height);
		        String Line = "" ;
		       for( int w = 0 ; w < width ; w++ ){
	            	for( int h = 0 ; h < height ; h++ ){
	            		if(br.readLine() != null){
	            			Line = br.readLine();
	            			n = Integer.parseInt(  Line );
	            		//System.out.println( "Read : " + Line );
	            		}
	            		for( int lis = 0 ; lis < infList.size() ; lis++ ){
	            			if(n == infList.get(lis).name)
	            				pixels[w][h] =(int)infList.get(lis).mid; 
	            				//System.out.print(pixels[w][h]);
	            			
	            			}
	            		}
		       		}
		    } finally {
		        br.close();
		    }
			
	}
	//-----------------------------------------------------------------
    //write image
    public void writeImage()
    {
        File fileout=new File("D:\\study\\3rd year\\multimedia\\assi\\New folder\\j\\lena2.jpg");
        BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

        for(int x=0;x<width ;x++)
        {
            for(int y=0;y<height;y++)
            {
                image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
            }
        }
        try
        {
            ImageIO.write(image2, "jpg", fileout);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
