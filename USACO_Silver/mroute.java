/*
ID: ayush941
LANG: JAVA
TASK: mroute
*/
/*
Link: http://usaco.org/index.php?page=viewproblem2&cpid=210
Algorithm: Run Djkstra's in a clever way using cap system.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class mroute {
	static int N;
	static int[][] path;
	static int[][] capacity;
	static int[][] ch;
	static int[] distance;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		mroute test=new mroute();

		try{
			fis= new FileInputStream("mroute.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("mroute.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));

			String[] a=br.readLine().split(" ");
			N=Integer.parseInt(a[0]);
			path=new int[N+1][N+1];
			capacity=new int[N+1][N+1];
			int m=Integer.parseInt(a[1]);
			int X=Integer.parseInt(a[2]);

			for(int i=0;i<path.length;i++){
				Arrays.fill(path[i], -1);
				Arrays.fill(capacity[i], -1);
			}

			int maxcap=0;
			for(int i=0;i<m;i++){
				String[] temp=br.readLine().split(" ");
				path[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]=Integer.parseInt(temp[2]);
				path[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])]=Integer.parseInt(temp[2]);
				capacity[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]=Integer.parseInt(temp[3]);
				capacity[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])]=Integer.parseInt(temp[3]);
				
				if(maxcap<Integer.parseInt(temp[3])){
					maxcap=Integer.parseInt(temp[3]);
				}
		}
			
			distance=new int[N+1];
			ch=new int[N+1][N+1];
			
			double min=Integer.MAX_VALUE;
			for(int cap=1;cap<=maxcap;cap++){
				visited=new boolean[N+1];
				Arrays.fill(distance, Integer.MAX_VALUE);
				distance[1]=0;
				test.setcap(cap);
				test.Djk(1);
				double check=(double) distance[N]+X/cap;
				if(check<min){
					min=check;
				}
			}
			
			br1.write(Double.toString(min));
			br1.newLine();
			br1.flush();

			
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
	public void Djk(int cur){
		//write base case
		if(visited[N]){
			return;
		}
		if(cur==0){
			return;
		}
		
		visited[cur]=true;
		int min=Integer.MAX_VALUE;
		int ind=0;
		for(int i=1;i<ch.length;i++){
			if(ch[cur][i]>0 && distance[i]>distance[cur]+path[cur][i] && !visited[i]){
				//System.out.println(cur+" "+i);
				distance[i]=distance[cur]+path[cur][i];
				if(distance[i]<min){
					min=distance[i];
					ind=i;
				}
			}
		}
		Djk(ind);
	}
	
	public void setcap(int cap){
		
		for(int i=0;i<ch.length;i++){
			Arrays.fill(ch[i], -1);
		}
		for(int i=1;i<capacity.length;i++){
			for(int j=1;j<capacity.length;j++){
				if(capacity[i][j]>0 && capacity[i][j]>=cap){
					ch[i][j]=capacity[i][j];
				}
			}
		}
	}
}
