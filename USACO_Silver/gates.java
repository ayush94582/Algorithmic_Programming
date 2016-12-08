/*
ID: ayush941
LANG: JAVA
TASK: gates
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


public class gates {
	
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
	
	public void floodfill(){
		for(int i=1002-N;i<1002+N;i++){
			for(int j=1002-N;j<1002+N;j++){
				if(!visited[i][j]){
					q.add(new pair(i, j));
					compnum++;
					while(!q.isEmpty()){
						pair cur=q.remove();
						visited[cur.x][cur.y]=true;
						if(!isFence[cur.x][cur.y][0] && cur.x>0 && !visited[cur.x-1][cur.y]){
							q.add(new pair(cur.x-1, cur.y));
							visited[cur.x-1][cur.y]=true;
						}
						if(cur.x!=2004){
							if(!isFence[cur.x+1][cur.y][0] && !visited[cur.x+1][cur.y]){
								q.add(new pair(cur.x+1, cur.y));
								visited[cur.x+1][cur.y]=true;
							}
						}
						if(cur.y!=2004){
							if(!isFence[cur.x][cur.y+1][2] && !visited[cur.x][cur.y+1]){
								q.add(new pair(cur.x, cur.y+1));
								visited[cur.x][cur.y+1]=true;
							}
						}
						if(!isFence[cur.x][cur.y][2] && cur.y>0){
							if(!visited[cur.x][cur.y-1]){
								q.add(new pair(cur.x, cur.y-1));
								visited[cur.x][cur.y-1]=true;
							}
						}
					}
				}
			}
		}
	}
	
	
	static int N;
	static boolean[][][] isFence;
	static boolean[][] visited;
	static int compnum=0;

	static Queue<pair> q = new LinkedList<pair>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			FileInputStream fis=null;	
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;
			gates test=new gates();
			
			try{
				fis= new FileInputStream("gates.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("gates.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));

				N=Integer.parseInt(br.readLine());
				isFence=new boolean[2005][2005][4]; //0 North, 1 South, 2 East, 3 West
				String path=br.readLine();
				
				int x=1002; int y=1002;
				
				for(int i=0;i<N;i++){
					if(path.charAt(i)=='E'){
						isFence[x][y][2]=true;
						isFence[x+1][y][3]=true;
						x++;
					}
					if(path.charAt(i)=='W'){
						isFence[x][y][3]=true;
						isFence[x-1][y][2]=true;
						x--;
					}
					if(path.charAt(i)=='N'){
						isFence[x][y][0]=true;
						isFence[x][y+1][1]=true;
						y++;
					}
					if(path.charAt(i)=='S'){
						isFence[x][y][1]=true;
						isFence[x][y-1][0]=true;
						y--;
					}
				}
				
				visited=new boolean[2005][2005];
				
				test.floodfill();
				br1.write(Integer.toString(compnum-1));
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
