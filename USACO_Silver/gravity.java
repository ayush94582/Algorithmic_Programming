/*
ID: ayush941
LANG: JAVA
TASK: gravity
*/
/*
Problem Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=282
Algorithm: Make methods for "falling" and note that this is a "shortest-paths" graph theory problem.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class gravity {
	 
	private class pair {

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
	
	public boolean adj(pair a, pair b){
		return (Math.abs(a.y-b.y)==1 && a.x==b.x);
	}
	
	private class compare implements Comparator<pair>
	{
	    public int compare(pair x, pair y)
	    {
	    	return distance[x.x][x.y]-distance[y.x][y.y];
	    }
	}

	//+1=down, -1=up
	public pair fall(pair p, int dir, int endx, int endy, int N){	
		while(arr[p.x+dir][p.y] && p.x<N && p.x>=0 && !(p.x==endx && p.y==endy)){
			p=new pair(p.x+dir, p.y);
			if(p.x>=N || p.x<0){
				return new pair(-1,-1);
			}
		}
		return new pair(p.x, p.y);
	}
	
	//k=-1 if left, 0 flip, 1 if right; dir=+1 down, -1 up
	public pair action(pair p, int k, int dir, int endx, int endy, int N){
		if(k==-1){
			return fall(new pair(p.x, p.y-1), dir, endx, endy, N);
		}
		if(k==0){
			return fall(new pair(p.x, p.y), -dir, endx, endy, N);
		}
		if(k==1){
			return fall(new pair(p.x, p.y+1), dir, endx, endy, N);
		}
		
		return new pair(0,0);
	}
	
	//start at a stable position
	public void Djk(int startx, int starty, int endx, int endy, ArrayList<Integer> nodex, ArrayList<Integer> nodey, int N, int M){
		
		pair[] nodes=new pair[nodex.size()];
		for(int i=0;i<nodex.size();i++){
			nodes[i]=new pair(nodex.get(i), nodey.get(i));
		}

		distance=new int[N][M];
		for(int i=0;i<distance.length;i++){
			for(int j=0;j<M;j++){
				distance[i][j]=Integer.MAX_VALUE;
			}
		}
			Comparator<pair> comparator = new compare();	
			PriorityQueue<pair> q=new PriorityQueue<pair>(10, comparator);
			
			distance[startx][starty]=1;
			boolean[][] visited=new boolean[N][M];
			q.add(new pair(startx, starty));
		
			
			while(!q.isEmpty()){
				pair current=q.poll();
				System.out.println("BITCH "+current.x+" "+current.y);
				visited[current.x][current.y]=true;
				int dir=1;
				if(distance[current.x][current.y]%2==1){
					dir=-1;
				}
				pair a=action(current, 1, dir, endx, endy, N);
				pair b=action(current, 0, dir, endx, endy, N);
				pair c=action(current, -1, dir, endx, endy, N);
				if(!visited[a.x][a.y] && !a.equals(new pair(-1,-1)))
				q.add(a); 
				if(!visited[b.x][b.y] && !b.equals(new pair(-1,-1)))
				q.add(b); 
				if(!visited[c.x][c.y] && !c.equals(new pair(-1,-1)))
				q.add(c);
				
				if(distance[a.x][a.y]>distance[current.x][current.y]){
					distance[a.x][a.y]=distance[current.x][current.y];
				}
				if(distance[c.x][c.y]>distance[current.x][current.y]){
					distance[c.x][c.y]=distance[current.x][current.y];
				}
				if(distance[b.x][b.y]>distance[current.x][current.y]+1){
					distance[b.x][b.y]=distance[current.x][current.y]+1;
				}
			}
			
		}	
	
	static int[][] distance;
	static boolean[][] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		gravity test=new gravity();

		try{
			fis= new FileInputStream("gravity.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("gravity.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			int M, N;
			int startx=0, starty=0, endx=0, endy=0;
			ArrayList<Integer> nodex=new ArrayList<Integer>(), nodey=new ArrayList<Integer>();

			
			String[] in=br.readLine().split(" ");
			N=Integer.parseInt(in[0]);
			M=Integer.parseInt(in[1]);
			arr=new boolean[N][M];

			for(int i=0;i<N;i++){
				String temp=br.readLine();
				for(int j=0;j<M;j++){				
					if(temp.substring(j, j+1).equals(".") || temp.substring(j, j+1).equals("C") || temp.substring(j, j+1).equals("D")){
						arr[i][j]=true;
						if(i>0 && !arr[i-1][j]){
							nodex.add(i);
							nodey.add(j);
						}
					}
					else if(i>0 && temp.substring(j, j+1).equals("#") && arr[i-1][j]){
						nodex.add(i-1);
						nodey.add(j);
					}
					
					if(temp.substring(j, j+1).equals("C")){
						startx=i;
						starty=j;
					}
					if(temp.substring(j, j+1).equals("D")){
						endx=i;
						endy=j;
					}
					
				}
			}
			
			test.Djk(startx, starty, endx, endy, nodex, nodey, N, M);
			
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
