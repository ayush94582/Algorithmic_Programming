/*
ID: ayush941
LANG: JAVA
TASK: ccski

*Link: http://usaco.org/index.php?page=viewproblem2&cpid=380
*Algorithm: Binary search through all possible D after creating a "Reachable" function
*that detects whether D is enough or not (Runtime: O(MN log R))
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ccski {
	
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
	
	public boolean reachable(int D){
			int i=0;
			pair source=waypoint.get(0);
			boolean[][] visited=new boolean[N][M];
			q.add(source);
			
			while(!q.isEmpty()){
				pair cur=q.remove();
				if(visited[cur.x][cur.y]){
					continue;
				}
				visited[cur.x][cur.y]=true;
				if(cur.x>0){
					if(Math.abs(elevations[cur.x-1][cur.y]-elevations[cur.x][cur.y])<=D && !visited[cur.x-1][cur.y]){
						q.add(new pair(cur.x-1, cur.y));
					}
				}
				if(cur.x<N-1){
					if(Math.abs(elevations[cur.x+1][cur.y]-elevations[cur.x][cur.y])<=D && !visited[cur.x+1][cur.y]){
						q.add(new pair(cur.x+1, cur.y));
					}
				}
				if(cur.y>0){
					if(Math.abs(elevations[cur.x][cur.y-1]-elevations[cur.x][cur.y])<=D && !visited[cur.x][cur.y-1]){
						q.add(new pair(cur.x, cur.y-1));
					}
				}
				if(cur.y<M-1){
					if(Math.abs(elevations[cur.x][cur.y+1]-elevations[cur.x][cur.y])<=D && !visited[cur.x][cur.y+1]){
						q.add(new pair(cur.x, cur.y+1));
					}
				}
			}
			
			for(int j=i+1;j<waypoint.size();j++){
				if(!visited[waypoint.get(j).x][waypoint.get(j).y]){
					return false;
				}
			}
		
		return true;
	}
	
	static int N, M;
	static int[][] elevations;
	static ArrayList<pair> waypoint=new ArrayList<pair>();
	static Queue<pair> q = new LinkedList<pair>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		ccski test=new ccski();

		try{
			fis= new FileInputStream("ccski.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("ccski.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			N=Integer.parseInt(in[0]);
			M=Integer.parseInt(in[1]);
			elevations=new int[N][M];
			
			int max=-1;
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				for(int j=0;j<M;j++){
					elevations[i][j]=Integer.parseInt(temp[j]);
					if(elevations[i][j]>max){
						max=elevations[i][j];
					}
				}
			}
			
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				for(int j=0;j<temp.length;j++){
					if(Integer.parseInt(temp[j])==1){
						waypoint.add(new pair(i, j));
					}
				}
			}
			
			int low=0;
			int high=max;
			int middle=(low+high)/2;
			
			while(low<=high){
				middle=(low+high)/2;
				boolean b=test.reachable(middle);
				if(b){
					high=middle-1;
				}
				if(!b){
					low=middle+1;
				}
			}
			boolean l=test.reachable(middle-1);
			boolean c=test.reachable(middle);
			if(l){
				br1.write(Integer.toString(middle-1));
			}
			if(!l && c){
				br1.write(Integer.toString(middle));
			}
			if(!l && !c){
				br1.write(Integer.toString(middle+1));
			}
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
	
}
