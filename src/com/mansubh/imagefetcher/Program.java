/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mansubh.imagefetcher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mansubh
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the model images url:");
        try{
        String webcontent = Grabber.get(input.nextLine());
 
       String imagepattern = "<img src=\\\"graphics/model/(.*?)\\.jpg\"(.*?)/>";
       Pattern pattern = Pattern.compile(imagepattern);
            Matcher matcher = pattern.matcher(webcontent);
             int counter = 1;
            while(matcher.find()){
                String imagepath ="http://cybersansar.com/graphics/model/"+ matcher.group(1)+".jpg";
                imagepath=imagepath.replace("/thumb", "");
                System.out.println(imagepath);
               
               writetoFile(imagepath, "image_" + counter + ".jpg" );
               counter++;
                
                
                
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void writetoFile (String url,String filename) throws IOException{
        URL myurl = new URL(url);
        URLConnection conn = myurl.openConnection();
        InputStream is = conn.getInputStream();
        OutputStream os = new FileOutputStream("/home/mansubh/cyberpics/"+filename);
        byte[] b = new byte[2048];
        int i;
        while((i=is.read(b)) != -1){
            
            os.write(b, 0, i);
            
        }
        System.out.println("Extracting..");
        os.close();
       is.close();
        
    }
    
}
