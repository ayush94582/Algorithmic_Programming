/*
ID: ayush941
LANG: JAVA
TASK: lightson
*/
/*
Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=570
Algorithm: DFS with visited array and note that 3 possible change in states.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class lightson {
	 
	static class pair {

		//constructs a key object that is an ordered pair (x,y)
	    private final int x;
	    private final int y;

	    public pair(int a, int b) {
	        x = a;
	        y = b;
	    }

	    //comparing two keys
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
	
	public boolean neighbor(pair a, boolean[][] visited){
		return (!visited[a.x][a.y] && (visited[a.x+1][a.y] || visited[a.x-1][a.y] || visited[a.x][a.y+1] || visited[a.x][a.y-1]));
	}
	
	public void solve(int cx, int cy){
		visited[cx][cy]=true;
		ArrayList<pair> cur=switches[cx][cy];
		for(int i=0;i<cur.size();i++){
			lit[cur.get(i).x][cur.get(i).y]=true;
			if(neighbor(cur.get(i), visited)){
				solve(cur.get(i).x, cur.get(i).y);
			}
		}
		if(lit[cx+1][cy] && !visited[cx+1][cy]){
			solve(cx+1, cy);
		}
		if(lit[cx-1][cy] && !visited[cx-1][cy]){
			solve(cx-1, cy);
		}
		if(lit[cx][cy+1] && !visited[cx][cy+1]){
			solve(cx, cy+1);
		}
		if(lit[cx][cy-1] && !visited[cx][cy-1]){
			solve(cx, cy-1);
		}
	}

	static int N;
	static int M;
	static boolean[][] visited;
	static boolean[][] lit;
	static ArrayList<pair>[][] switches;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		lightson test=new lightson();
		

		try{
			fis= new FileInputStream("lightson.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("lightson.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
		
			String[] in=br.readLine().split(" ");
			N=Integer.parseInt(in[0]);
			M=Integer.parseInt(in[1]);
			switches=new ArrayList[N+2][N+2];
			
			for(int i=0;i<N+2;i++){
				for(int j=0;j<N+2;j++){
					switches[i][j]=new ArrayList<pair>();
				}
			}
			for(int i=0;i<M;i++){
				String[] temp=br.readLine().split(" ");
				switches[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])].add(new pair(Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
			}
			
			visited=new boolean[N+2][N+2];
			lit=new boolean[N+2][N+2];
			lit[1][1]=true;
			test.solve(1,1);
			
			int count=0;
			for(int i=0;i<lit.length;i++){
				for(int j=0;j<lit.length;j++){
					if(lit[i][j]){
						count++;
					}
				}
			}
			
			br1.write(Integer.toString(count));
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
