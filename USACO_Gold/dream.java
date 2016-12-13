/*
ID: ayush941
LANG: JAVA
TASK: dream

*
*Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=575
*Algorithm: Use shortest paths algorithm handling the state with 5 parameters as follows
*(Use a priority queue for time efficiency)
*
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

import week1011.tract.pair;

public class dream {
	
	static class pair{
	    private final int x;
	    private final int y;
	    private final int z;
	    private final int dir;
	    private final int count;
	    public pair(int a, int b, int odor, int slide, int c) {
	        x = a;
	        y = b;
	        z = odor;
	        dir=slide;
	        count=c;
	    }

	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof pair)) return false;
	        pair key = (pair) o;
	        return x == key.x && y == key.y && z == key.z;
	    } 
	    
	    public int hashCode() {
	        int result = x;
	        result = 31 * result + y;
	        return result;
	    }
	}
	//0=no odor, 1= odor
	//0=up, 1=down, 2=right, 3=left
	public void bfs(){
		q.add(new pair(0, 0, 0, 1, 0));
		boolean[][][][] visited=new boolean[N][M][2][4];
		while(!q.isEmpty() && !visited[N-1][M-1][0][0] && !visited[N-1][M-1][0][1] && !visited[N-1][M-1][0][2] && !visited[N-1][M-1][0][3] && !visited[N-1][M-1][1][0] && !visited[N-1][M-1][1][1] && !visited[N-1][M-1][1][2] && !visited[N-1][M-1][1][3]){
			pair cur=q.remove();
			System.out.println(cur.x+" "+cur.y+" "+cur.z+" "+" "+cur.dir+" "+cur.count);
			if(cur.x==N-1 && cur.y==M-1){
				sol=cur.count;
			}
			if(visited[cur.x][cur.y][cur.z][cur.dir]){
				continue;
			}
			visited[cur.x][cur.y][cur.z][cur.dir]=true;
			if(grid[cur.x][cur.y]==0){
				continue;
			}
			if(grid[cur.x][cur.y]==1){
				if(cur.x>0 && !visited[Math.max(0, cur.x-1)][cur.y][cur.z][0]){
					q.add(new pair(cur.x-1, cur.y, cur.z, 0, cur.count+1));
				//	visited[Math.max(0, cur.x-1)][cur.y][cur.z][0]=true;
				}
				if(cur.y>0 && !visited[cur.x][Math.max(cur.y-1, 0)][cur.z][3]){
					q.add(new pair(cur.x, cur.y-1, cur.z, 3, cur.count+1));
				//	visited[cur.x][Math.max(cur.y-1, 0)][cur.z][3]=true;
				}
				if(cur.x<N-1 && !visited[Math.min(cur.x+1, N-1)][cur.y][cur.z][1]){
					q.add(new pair(cur.x+1, cur.y, cur.z, 1, cur.count+1));
				//	visited[Math.min(cur.x+1, N-1)][cur.y][cur.z][1]=true;
				}
				if(cur.y<N-1 && !visited[cur.x][Math.min(cur.y+1, M-1)][cur.z][2]){
					q.add(new pair(cur.x, cur.y+1, cur.z, 2, cur.count+1));
				//	visited[cur.x][Math.min(cur.y+1, M-1)][cur.z][2]=true;
				}
			}
			if(grid[cur.x][cur.y]==2){
				if(cur.x>0 && !visited[Math.max(0, cur.x-1)][cur.y][1][0]){
					q.add(new pair(cur.x-1, cur.y, 1, 0, cur.count+1));
				//	visited[Math.max(0, cur.x-1)][cur.y][1][0]=true;
				}
				if(cur.y>0 && !visited[cur.x][Math.max(cur.y-1, 0)][1][3]){
					q.add(new pair(cur.x, cur.y-1, 1, 3, cur.count+1));
				//	visited[cur.x][Math.max(cur.y-1, 0)][1][3]=true;
				}
				if(cur.x<N-1 && !visited[Math.min(cur.x, N-1)][cur.y][1][1]){
					q.add(new pair(cur.x+1, cur.y, 1, 1, cur.count+1));
				//	visited[Math.min(cur.x, N-1)][cur.y][1][1]=true;
				}
				if(cur.y<N-1 && !visited[cur.x][Math.min(cur.y, M-1)][1][2]){
					q.add(new pair(cur.x, cur.y+1, 1, 2, cur.count+1));
				//	visited[cur.x][Math.min(cur.y, M-1)][1][2]=true;
				}
			}
			if(grid[cur.x][cur.y]==3){
				if(cur.z==1){
					if(cur.x>0 && !visited[Math.max(0, cur.x-1)][cur.y][1][0]){
						q.add(new pair(cur.x-1, cur.y, 1, 0, cur.count+1));
				//		visited[Math.max(0, cur.x-1)][cur.y][1][0]=true;
					}
					if(cur.y>0 && !visited[cur.x][Math.max(cur.y-1, 0)][1][3]){
						q.add(new pair(cur.x, cur.y-1, 1, 3, cur.count+1));
				//		visited[cur.x][Math.max(cur.y-1, 0)][1][3]=true;
					}
					if(cur.x<N-1 && !visited[Math.min(cur.x, N-1)][cur.y][1][1]){
						q.add(new pair(cur.x+1, cur.y, 1, 1, cur.count+1));
				//		visited[Math.min(cur.x, N-1)][cur.y][1][1]=true;
					}
					if(cur.y<N-1 && !visited[cur.x][Math.min(cur.y, M-1)][1][2]){
						q.add(new pair(cur.x, cur.y+1, 1, 2, cur.count+1));
				//		visited[cur.x][Math.min(cur.y, M-1)][1][2]=true;
					}
				}
			}
			if(grid[cur.x][cur.y]==4){
				if(cur.dir==0){
					if(cur.x>0){
						q.add(new pair(cur.x-1, cur.y, 0, cur.dir, cur.count+1));
					}
				}
				if(cur.dir==1){
					if(cur.x<N-1){
						q.add(new pair(cur.x+1, cur.y, 0, cur.dir, cur.count+1));
					}
				}
				if(cur.dir==2){
					if(cur.y<M-1){
						q.add(new pair(cur.x, cur.y+1, 0, cur.dir, cur.count+1));
					}
				}
				if(cur.dir==3){
					if(cur.y>0){
						q.add(new pair(cur.x, cur.y-1, 0, cur.dir, cur.count+1));
					}
				}
			}
		}
	}
	
	static int N; static int M;
	static int[][] grid;
	static Queue<pair> q = new LinkedList<pair>();
	static int sol;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			FileInputStream fis=null;	
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;
			dream test=new dream();
			
			try{
				fis= new FileInputStream("/Users/sunitaagarwal/javaproject/week1011/src/week1011/comehome.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("/Users/sunitaagarwal/javaproject/week1011/src/week1011/comehome.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));

				String[] in=br.readLine().split(" ");
				N=Integer.parseInt(in[0]);
				M=Integer.parseInt(in[1]);
				grid=new int[N][M];
				
				for(int i=0;i<N;i++){
					String[] row=br.readLine().split(" ");;
					for(int j=0;j<M;j++){
						grid[i][j]=Integer.parseInt(row[j]);
						//System.out.println(i+" "+j+" "+grid[i][j]);
					}
				}
				
				test.bfs();
				br1.write(Integer.toString(sol));
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
