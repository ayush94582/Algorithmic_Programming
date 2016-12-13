/*
ID: ayush941
LANG: JAVA
TASK: fencedin

*
*Link: http://usaco.org/index.php?page=viewproblem2&cpid=623
*Algorithm: Build the Minimum Spanning Tree (MST) using Prim's Algorithm (could also use Kruskal's)
*Define the graph G as the lower left vertex of each square
*
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class fencedin {
	
	static class pair{
	    private final int x;
	    private final int y;

	    public pair(int a, int b) {
	        x = a;
	        y = b;

	    }

	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof pair)) return false;
	        pair key = (pair) o;
	        return x == key.x && y == key.y;
	    } 
	    
	    public int hashCode() {
	        int result = x;
	        result = 31 * result + y;
	        return result;
	    }
	}
	
	static class edge{
	    private final int n1;
	    private final int n2;
	    private final int cost;

	    public edge(int a, int b, int c) {
	        n1 = a;
	        n2 = b;
	        cost=c;
	    }

	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof pair)) return false;
	        edge key = (edge) o;
	        return n1 == key.n1 && n2 == key.n2 && cost == key.cost;
	    } 
	    
	    public int hashCode() {
	        int result = n1;
	        result = 31 * result + n2;
	        return result;
	    }
	}
	
	public void Primm(){
		reach.add(new pair(0,0));
		for(int i=0;i<=N;i++){
			for(int j=0;j<=M && !(i==0 && j==0);j++){
				unreach.add(new pair(i, j));
			}
		}
		used[0][0]=true;
		while(!unreach.isEmpty()){
			int min=Integer.MAX_VALUE;
			edge minedge=null;
			for(int i=0;i<reach.size();i++){
				pair temp=reach.get(i);
				for(int j=0;j<adj.get(temp).size();j++){
					if(min>adj.get(temp).get(j).cost && !used[adj.get(temp).get(j).n1][adj.get(temp).get(j).n2]){
						min=adj.get(temp).get(j).cost;
						minedge=adj.get(temp).get(j);
					}
				}
			}
			length+=min;
			reach.add(new pair(minedge.n1, minedge.n2));
			used[minedge.n1][minedge.n2]=true;
		//	System.out.println(minedge.n1+" "+minedge.n2);
			unreach.remove(new pair(minedge.n1, minedge.n2));
		}
	}
	
	static int A; static int B;
	static int N; static int M;
	static int[] yarr;
	static int[] xarr;
	static Map<pair, List<edge>> adj=new HashMap<pair, List<edge>>();
	static ArrayList<pair> reach=new ArrayList<pair>();
	static ArrayList<pair> unreach=new ArrayList<pair>();
	//static Set<pair> MST=new HashSet<pair>();
	static boolean[][] used;
	static long length=0; 
	
	public static void main(String[] args) throws IOException {
			FileInputStream fis=null;	
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;
			fencedin test=new fencedin();
			
			try{
				fis= new FileInputStream("/Users/sunitaagarwal/javaproject/week1011/src/week1011/comehome.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("/Users/sunitaagarwal/javaproject/week1011/src/week1011/comehome.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));

				String[] in=br.readLine().split(" ");
				A=Integer.parseInt(in[0]);
				B=Integer.parseInt(in[1]);
			
				N=Integer.parseInt(in[2]);
				M=Integer.parseInt(in[3]);
				yarr=new int[M+2];
				xarr=new int[N+2];
				
				for(int i=1;i<=N;i++){
					xarr[i]=Integer.parseInt(br.readLine());
				}
				yarr[0]=0; xarr[0]=0;
				yarr[yarr.length-1]=B; 	xarr[xarr.length-1]=A;
				for(int i=1;i<=M;i++){
					yarr[i]=Integer.parseInt(br.readLine());
				}
				for(int i=0;i<=N;i++){
					for(int j=0;j<=M;j++){
						adj.put(new pair(i, j), new LinkedList<edge>());
					}
				}
				Arrays.sort(yarr);
				Arrays.sort(xarr);
				for(int i=0;i<=N;i++){
					for(int j=0;j<=M;j++){
						if(i>0){
							adj.get(new pair(i,j)).add(new edge(i-1 , j, yarr[j+1]-yarr[j]));
						}
						if(j>0){
							adj.get(new pair(i,j)).add(new edge(i , j-1, xarr[i+1]-xarr[i]));
						}
						if(i<N){
							adj.get(new pair(i,j)).add(new edge(i+1 , j, yarr[j+1]-yarr[j]));
						}
						if(j<M){
							adj.get(new pair(i,j)).add(new edge(i , j+1, xarr[i+1]-xarr[i]));
						}
					}
				}
				System.out.println("DICKING");
				used=new boolean[N+1][M+1];
			//	test.Primm();
			//	System.out.println(length);
		
	}catch(Exception E){
    	E.getStackTrace();
    	}finally{
    	if(br!= null)
    		br.close();
    		
    	if(isr!=null)
    		isr.close();
    	
    	if(fis != null)
    		fis.close();
    	if(br1 != null)
    		br1.close();
    	
    }
    }
}
