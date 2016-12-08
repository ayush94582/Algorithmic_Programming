/*
ID: ayush941
LANG: JAVA
TASK: tractor
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class tractor {
	
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
	
	public void floodfill(int D){
		int compnum=0;
		int[][] component=new int[N][N];
		visited=new boolean[N][N];
		
		for(int i=0;i<N;i++){
			Arrays.fill(component[i], -2);
		}

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(!visited[i][j]){
					component[i][j]=compnum;
					q.add(new pair(i,j));
					int comp=0;
					while(!q.isEmpty()){
						pair cur=q.remove();
						if(cur.x!=0){
							if(Math.abs(heights[cur.x-1][cur.y]-heights[cur.x][cur.y])<=D && !visited[cur.x-1][cur.y]){
								visited[cur.x-1][cur.y]=true;
								q.add(new pair(cur.x-1, cur.y));
								component[cur.x-1][cur.y]=component[cur.x][cur.y];
							}
						}
						if(cur.y!=0){
							if(!visited[cur.x][cur.y-1] && Math.abs(heights[cur.x][cur.y-1]-heights[cur.x][cur.y])<=D){
							visited[cur.x][cur.y-1]=true;
							q.add(new pair(cur.x, cur.y-1));
							component[cur.x][cur.y-1]=component[cur.x][cur.y];
							}
						}
						if(cur.x!=N-1){
							if(!visited[cur.x+1][cur.y] && Math.abs(heights[cur.x+1][cur.y]-heights[cur.x][cur.y])<=D){
							visited[cur.x+1][cur.y]=true;
							q.add(new pair(cur.x+1, cur.y));
							component[cur.x+1][cur.y]=component[cur.x][cur.y];
							}
						}
						if(cur.y!=N-1){
							if(!visited[cur.x][cur.y+1]  && Math.abs(heights[cur.x][cur.y+1]-heights[cur.x][cur.y])<=D){
							visited[cur.x][cur.y+1]=true;
							q.add(new pair(cur.x, cur.y+1));
							component[cur.x][cur.y+1]=component[cur.x][cur.y];
							}
						}
					compnum++;
					comp++;
				}
				if(comp>maxi){
					maxi=comp;
				}
			}
		}
	}
}
	
	
	static int N;
	static int[][] heights;
	static boolean[][] visited;
	static int nodesvisited=0;
	static int maxi=-1;
	static Queue<pair> q = new LinkedList<pair>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			FileInputStream fis=null;	
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;
			tractor test=new tractor();
			
			try{
				fis= new FileInputStream("tractor.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("tractor.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));

				N=Integer.parseInt(br.readLine());
				
				int max=-1;
				int min=Integer.MAX_VALUE;
				heights=new int[N][N];
				

				for(int i=0;i<N;i++){
					String[] in=br.readLine().split(" ");
					for(int j=0;j<N;j++){
						heights[i][j]=Integer.parseInt(in[j]);
						if(heights[i][j]<min){
							min=heights[i][j];
						}
						if(heights[i][j]>max){
							max=heights[i][j];
						}
					}
				}
		
				
				int maxdif=max-min;
				//binary search based on maxdif
				int low=0;
				int high=maxdif;
				int lastmiddle=0;
				
				while(high>low){
					q.clear();
					int middle=(low+high)/2;
					test.floodfill(middle);
					int temp=maxi;
					maxi=0;
					if(temp>=(N*N+1)/2){
						high=middle-1;
					}
					else{
						low=middle+1;
					}
					lastmiddle=middle;
				} 
				
				br1.write(Integer.toString(high));
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
