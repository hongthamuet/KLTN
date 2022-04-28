import java.awt.BasicStroke;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Test extends Canvas {
	
	
	
	static List<Integer> starts=new ArrayList<>();
	static List<Integer> ends=new ArrayList<>();	
	static int[][] A;
	
	static List<Canh> edge=new ArrayList<>();
	static List<String> vertices=new ArrayList<>();
	static HashSet<String> v=new HashSet<>();
	
	static int radius=20;
	static Dictionary listOBJ=new Hashtable();	
	static String pre=null,curr;
	static List<Integer> X=new ArrayList<Integer>();
	static List<Integer> Y=new ArrayList<Integer>();	
	static String begin=" Begin";
	static String end="~End";
	
	public void PaintCircle(Graphics g,int x,int y,int r) {
		setForeground(Color.RED);
		g.fillOval(x, y, r, r);
	}
	
	public void drawArrowLine(Graphics g, double x1, double y1, double x2, double y2) {
		double height=8;
		double width=3;
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
    	int z=0;
    	System.out.println("Size="+listOBJ.size());
    	for (int i=0;i<listOBJ.size();i++)
    		z=Math.max(z, i*i%N);
    	System.out.println(z);
    	int dist=700/z;
    	int dist2=1400/(N-1);
    	for(int i=0;i<vertices.size();i++) {  		
    		if (i==0)
    			g.setColor(Color.RED);
    		else
    			if (i==N-1)
    				g.setColor(Color.BLUE);
    			else g.setColor(Color.GREEN);
    		int x=i*dist2;
    		int y=(i*i%N)*dist;
    		X.add(x);Y.add(y);
    		
    		PaintCircle(g, x, y, radius);    		
			g.setColor(Color.BLACK);
			int indexx=0;if ((i==0) || (i==vertices.size()-1)) indexx=1;
			g.drawString(vertices.get(i).substring(indexx), x, y+radius+10);
    		
    	}
    	
    	for(int i=0;i<starts.size();i++) {
    		
    		int x=starts.get(i);
    		int y=ends.get(i);    			
    		
    		if (x!=y)
    			drawArrowLine(g,X.get(x)+radius/2, Y.get(x)+radius/2, X.get(y)+radius/2, Y.get(y)+radius/2);   		
    	}	
    }    
    
    public static void main(String[] args) throws IOException, FileNotFoundException {
    	File dir = new File("I:\\KLTN\\Script");
        File[] children = dir.listFiles();
        
        v.add(begin);v.add(end);
        
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
                        int x1=s.indexOf(".",0);
                        int x2=s.indexOf(".",x1+1);
                        int x3=s.indexOf(".",x2+1);
                        String obj=s.substring(x2+1, x3);curr=obj;
                        
                        v.add(obj);
                        
                        if (pre!=null)                       
                            edge.add(new Canh(pre,obj));                       
                        else 
                        	edge.add(new Canh(begin,obj));
//                        	
                        pre=obj;                        
                    }
                }
                edge.add(new Canh(curr,end));
                
            } finally {
            	scanner.close();
            }
            scanner.close();
        }    	
        
        Iterator<String> I=v.iterator();
        while(I.hasNext()) {
        	vertices.add(I.next());
        }
        
        Collections.sort(vertices);
        for(int i=0;i<vertices.size();i++) {
//        	System.out.println(vertices.get(i));
        	listOBJ.put(vertices.get(i),i);
        }               
        
        int N=vertices.size();
        A=new int[N][N];
        
        for(int i=0;i<edge.size();i++) {
        	int x=(int) listOBJ.get(edge.get(i).u);
        	int y=(int) listOBJ.get(edge.get(i).v);
        	if (x!=y) {
        		starts.add(x); ends.add(y);
        		A[x][y]=1;
        	}
        }
        
//        Write CSV
        String sec=Long.toString(System.currentTimeMillis()); 
        String filename=sec+".csv";
//        FileOutputStream ostream=new FileOutputStream(datetime+".csv");
        FileWriter writer=new FileWriter(filename);
        
//        BufferedWriter writer=Files.newBufferedWriter(Paths.get(datetime+".csv"));
        // Write Header
        ArrayList<String> lst=new ArrayList<>();        
        for(int i=0;i<N;i++)
        	if ((i==0) || (i==N-1)) lst.add(vertices.get(i).substring(1));  
        	else lst.add(vertices.get(i));        
        String str=String.join(",", lst);
        System.out.println(str);
        writer.write(str+"\n");
        // Write Table
        for(int i=0;i<N;i++) {
        	lst=new ArrayList<>();
        	for(int j=0;j<N;j++) {
        		int x=A[i][j];
        		String t=Integer.toString(x);
        		lst.add(t);
        	}
        	str=String.join(",", lst);
            System.out.println(str);
            writer.write(str+"\n");
        }
        //                      
        writer.close();
        System.out.println(vertices);
        System.out.println(starts);
        System.out.println(ends);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(A[i][j]+" ");
            System.out.println();
        }
                     
    	Test m = new Test();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(1500, 800);       
        f.setVisible(true);           
    }
} 