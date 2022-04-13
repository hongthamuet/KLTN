package analysis.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ScriptAnalysis {
	public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("I:\\KLTN\\Script");
        File[] children = dir.listFiles();
        for (File file : children) {
           String path=file.getAbsolutePath();  
           System.out.println(path);   
           FileInputStream istream=new FileInputStream(file);
//         BufferedReader in = new BufferedReader(
//        		   new InputStreamReader(
//                              new FileInputStream(istream), "UTF8"));
           InputStreamReader istream1 = new InputStreamReader(istream, StandardCharsets.UTF_8);
           BufferedReader istream2 = new BufferedReader(istream1);
           Scanner scanner=new Scanner(istream2);
           try {
                while (scanner.hasNextLine()) {
                    String s=scanner.nextLine();  
                    s=s.trim();
                    if (s.indexOf("repo.")==0){
                
                        int x1=s.indexOf(".",0);
                        int x2=s.indexOf(".",x1+1);
                        int x3=s.indexOf(".",x2+1);
                        
                        String obj=s.substring(x2+1, x3);
                        String method=s.substring(x3+1, s.length()-1);
                        System.out.println(obj + " " + method);
     
               
                    }
                }
                System.out.println();
            } finally {
                
            }
        }
    }

}

