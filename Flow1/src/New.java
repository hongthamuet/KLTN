import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class New {
	
	public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
//        String workingDir = System.getProperty("user.dir");
//        System.out.println("Current working directory : " + workingDir);
//        Dictionary listOBJ=new Hashtable();
//        String pre=null,metpre=null;  
//        List<String> lst=new ArrayList<String>();
        
        File dir = new File("I:\\Eclipse\\JavaApplication2\\Records");
        File[] children = dir.listFiles();
        for (File file : children) {
//            System.out.println(file.getAbsolutePath());
            String path=file.getAbsolutePath();  
            System.out.println(path);   
            FileInputStream istream=new FileInputStream(file);
            Scanner scanner=new Scanner(istream);
            try {
                while (scanner.hasNextLine()) {
                    String s=scanner.nextLine();  
                    s=s.trim();
                    if (s.indexOf("repo.")==0){
//                        int z=s.indexOf("repo.");
//                        System.out.println("+"+Integer.toString(z)+s+"+");
//                        System.out.println(s);
                        int x1=s.indexOf(".",0);
                        int x2=s.indexOf(".",x1+1);
                        int x3=s.indexOf(".",x2+1);
//                        System.out.println(Integer.toString(x1)+" "+Integer.toString(x2)+" "+Integer.toString(x3));
                        String obj=s.substring(x2+1, x3);
                        String method=s.substring(x3+1, s.length()-1);
                        
//                        lst.add(obj);
                        System.out.println(obj+" "+method);
////                        if (pre!=null){
////                            System.out.println(pre+"---("+metpre+")"+"--->"+obj);
////                        }
////                        pre=obj;metpre=method;
//                        if (listOBJ.get(obj)==null){
//                            int sz=listOBJ.size()+1;
//                            listOBJ.put(obj, sz);
//                        }
                    }
                }
            } finally {
                
            }
//            lst.add("End");
//            System.out.println(lst);
//            for (Enumeration k = listOBJ.keys(); k.hasMoreElements();)
//            {
//                System.out.println("Keys in Dictionary : " + k.nextElement());
//            }s
        }
    }
}
