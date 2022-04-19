import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JScrollBar;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
 
public class Test extends Canvas {
	static List<Integer> starts=new LinkedList<>();
	static List<Integer> ends=new LinkedList<>();
//	static List<Integer> colors=new LinkedList<>();	
	static int radius=50;
	static int height=200;
	static Dictionary listOBJ=new Hashtable();
	static Dictionary list2=new Hashtable<>();
	static String pre=null,curr;
	static List<Integer> X=new LinkedList<Integer>();
	static List<Integer> Y=new LinkedList<Integer>();
	static double xx,yy;
	
	public Test() {
		
	}
	
	public void PaintCircle(Graphics g,int x,int y,int r) {
		setForeground(Color.RED);
		g.fillOval(x, y, r, r);
	}
	
	public void drawArrowLine(Graphics g, double x1, double y1, double x2, double y2) {
		double height=10;
		double width=4;
		double dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - height, xn = xm, ym = width, yn = -width, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;/*from w  ww.java  2s  . c om*/
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = { (int) x2, (int) xm, (int) xn };
        int[] ypoints = { (int) y2, (int) ym, (int) yn };     
        g.setColor(Color.black);
       
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
	
	public void GetMoveVector(double x,double y) {
		double delta=5;
		xx=-y;yy=x;
		double D=Math.sqrt(x*x+y*y);
		xx=delta*xx/D;yy=delta*yy/D;
	}
	
	public static int isPrime(int n) {
		if (n==1) return 0;
		for(int i=2;i<=Math.sqrt(n);i++)
			if (n%i==0) return 0;
		return 1;
	}
	
	public static int NextPrime(int n) {
		while(isPrime(n)==0) n++;
		return n;
	}
	
    public void paint(Graphics g) {   
    	int N=NextPrime(listOBJ.size());
    	int dist=700/N;
    	int dist2=1400/N;
    	System.out.println(dist+" "+dist2);
    	for(int i=0;i<listOBJ.size();i++) {
//    		for(int i=0;i<N;i++) {
    		g.setColor(Color.ORANGE);
    		X.add(i*dist2);Y.add((i*i%N)*dist);
    		PaintCircle(g, i*dist2, (i*i%N)*dist, radius);
    		if (list2.get(i)!=null) {
    			g.setColor(Color.BLACK);
    			g.drawString(list2.get(i).toString(), i*dist2, (i*i%N)*dist+radius+10);
    		}
    	}
    	
//    	drawArrowLine(g, 0,0,40,50,Color.RED);
    	
    	for(int i=0;i<starts.size();i++) {
//    		try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    		
    		int x=starts.get(i);
    		int y=ends.get(i);
//    		int clrs=colors.get(i);
//    		int h=150-10*clrs;
//    		Color color=Color.WHITE;
//    		if (clrs==0) color=Color.BLACK;
//    		if (clrs==1) color=Color.RED;	
//    		if (clrs==2) color=Color.BLUE;	
//    		if (clrs==3) color=Color.MAGENTA;  
    		
//    		int clr=clrs+1;
    		
//    		if (x!=y) GetMoveVector(X.get(y)-X.get(x), Y.get(y)-Y.get(x));    			
    		
    		if (x!=y)
    			drawArrowLine(g,X.get(x)+radius/2, Y.get(x)+radius/2, X.get(y)+radius/2, Y.get(y)+radius/2);   		
//    		else
//    			if (x<y)
//    				drawArrowLine(g,X.get(x)+radius/2-clr*xx, Y.get(x)+radius/2-clr*yy, X.get(y)+radius/2-clr*xx, Y.get(y)+radius/2-clr*yy);
    		if (x==y) {    			
    			g.drawArc(X.get(x), Y.get(x)-radius, radius, radius, 0, 360);
    		}
    	}	
    }    
    
    public static void main(String[] args) throws FileNotFoundException {
    	File dir = new File("I:\\Eclipse\\JavaApplication2\\New Folder");
        File[] children = dir.listFiles();int xx=0;
        
        for (File file : children) {
//            System.out.println(file.getAbsolutePath());
        	pre=null;
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
                        String obj=s.substring(x2+1, x3);curr=obj;
                        String method=s.substring(x3+1, s.length()-1);
                        
//                        lst.add(obj);
                        if (listOBJ.get(obj)==null){
                            int sz=listOBJ.size()+1;
                            listOBJ.put(obj, sz);
                            list2.put(sz, obj);
                        }                      
                        System.out.println(obj+" "+method);
                        if (pre!=null){
//                            System.out.println(pre+"------>"+obj);
                            int aa=(int) (listOBJ.get(pre));
                            int bb=(int) (listOBJ.get(obj));
                            starts.add(aa);
                            ends.add(bb);
//                            colors.add(xx);
                        }
                        else {
                        	int aa=0;
                        	int bb=(int)(listOBJ.get(obj));
                        	starts.add(aa);
                        	ends.add(bb);
//                        	colors.add(xx);                        	
                        }
                        pre=obj;                        
                    }
                }
                int aa=(int)(listOBJ.get(curr));
                starts.add(aa);
                ends.add(1000000);
//                colors.add(xx);
            } finally {
                
            }
//            lst.add("End");
//            System.out.println(lst);
            
            xx++;
        }
    	
        listOBJ.put("Begin!", 0);
        list2.put(0, "Begin!");
        
        int sz=listOBJ.size();
        listOBJ.put("End!!", sz);
        list2.put(sz, "End!!");
        
        for (int i=0;i<ends.size();i++)
        	if (ends.get(i)== 1000000)
        		ends.set(i, sz);
        
        
//        for (Enumeration k = listOBJ.keys(); k.hasMoreElements();)
//        {        	
//            System.out.println("Keys in Dictionary : " + k.nextElement()+" "+listOBJ.get(k.nextElement()));
//        }
    	
//    	starts.add(0);ends.add(1);colors.add(0);
//    	starts.add(2);ends.add(1);colors.add(0);
//    	starts.add(4);ends.add(6);colors.add(1);
//    	starts.add(0);ends.add(5);colors.add(1);
//    	starts.add(8);ends.add(5);colors.add(2);
//    	starts.add(9);ends.add(9);colors.add(2);
    	Test m = new Test();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(1500, 800);       
        f.setVisible(true);           
    }
} 