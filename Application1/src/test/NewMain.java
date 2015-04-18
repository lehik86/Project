/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      File file=new File("oui.txt");
      File temp=new File("macDB.txt");
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            BufferedWriter bw=new BufferedWriter(new FileWriter(temp));
            String line="";
            while( (line=br.readLine())!=null){
              if(line.contains(("base 16"))){
                 bw.write(line.replaceAll("\\s+\\s","").replace("(base 16)", " ")+"\n");
              }
            
            
            
            }
            br.close();
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
