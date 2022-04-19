import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
public class Compare {
	public static final String delimiter = ",";
	private static final String COMMA_DELIMITER = ",";
	public static ArrayList<String> VerticesA=new ArrayList<String>();
	public static ArrayList<String> VerticesB=new ArrayList<String>();
	public static int[][] A;
	public static int[][] B;
	public static int nA,nB;	
	
	public static void main(String[] args) throws IOException {        
		String csvFileA = "1650304013734.csv";
		String csvFileB = "1650305552189.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileA))) {
		    String line;int i=-1;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        List<String> lst=Arrays.asList(values);
		        if (i==-1) {
		        	nA=lst.size();
		        	for(int j=0;j<nA;j++)
		        		VerticesA.add(lst.get(j));
		        	A=new int[nA][nA];
		        }
		        else {
		        	for(int j=0;j<nA;j++)
		        		A[i][j]=Integer.parseInt(lst.get(j));
		        }
//		        System.out.println(lst);
		        i++;
		    }
		}
		System.out.println(nA);
		System.out.println(VerticesA);
		for (int i = 0; i < nA; i++) {
            for (int j = 0; j < nA; j++)
                System.out.print(A[i][j]+" ");
            System.out.println();
        }
		///-------------------
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileB))) {
		    String line;int i=-1;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        List<String> lst=Arrays.asList(values);
		        if (i==-1) {
		        	nB=lst.size();
		        	for(int j=0;j<nB;j++)
		        		VerticesB.add(lst.get(j));
		        	B=new int[nB][nB];
		        }
		        else {
		        	for(int j=0;j<nB;j++)
		        		B[i][j]=Integer.parseInt(lst.get(j));
		        }
//		        System.out.println(lst);
		        i++;
		    }
		}
		System.out.println(nB);
		System.out.println(VerticesB);
		for (int i = 0; i < nB; i++) {
            for (int j = 0; j < nB; j++)
                System.out.print(B[i][j]+" ");
            System.out.println();
        }
		///-Compare
		if (nA!=nB) {
			System.out.println("Number of vertices in two graph is DIFFERENT");
			return;
		}
		System.out.println("Number of vertices in two graph is EQUAL");
		int Checker=0;
		for(int i=0;i<nA;i++) {
			boolean id=VerticesB.contains(VerticesA.get(i));
			if (id==false) {
				System.out.println("Vertice " + VerticesA.get(i)+" is not exists in Graph B");
				Checker=1;
			}
		}
		for(int i=0;i<nB;i++) {
			boolean id=VerticesA.contains(VerticesB.get(i));
			if (id==false) {
				System.out.println("Vertice " + VerticesB.get(i)+" is not exists in Graph A");
				Checker=1;
			}
		}
		if (Checker==1) return;
		
		for(int i=0;i<nA;i++) 
			for(int j=0;j<nA;j++)
				if ((A[i][j]==1) && (B[i][j]==0))
					System.out.println("Edge ("+i+","+j+") exists in Graph A, but not exists in Graph B");
		for(int i=0;i<nB;i++) 
			for(int j=0;j<nB;j++)
				if ((B[i][j]==1) && (A[i][j]==0))
					System.out.println("Edge ("+i+","+j+") exists in Graph B, but not exists in Graph A");
	}
}
