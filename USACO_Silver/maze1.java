/*
 ID: ayush941
PROG: maze1
LANG: JAVA
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

public class maze1 {
	static int w;
	static int h;
	static boolean[][] connected; //things on the left
	static boolean[][] connected2; //things on the up
	static int gate1x=-1;
	static int gate2x=-1;
	static int gate1y=-1;
	static int gate2y=-1;
	static int[][] distance;
	static boolean[][] visited;
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		maze1 test=new maze1();

		try{
			fis= new FileInputStream("maze1.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("maze1.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			br.mark(0);
			h=Integer.parseInt(in[0]); //5 
			w=Integer.parseInt(in[1]); //3
			connected=new boolean[h][w];
			connected2=new boolean[h][w];

			for(int i=0;i<2*w+1;i++){
				//System.out.println(i);
				String temp=br.readLine();
				if(i%2==1){
					for(int j=0;j<temp.length();j+=2){
						if((j==0 || j==temp.length()-1) && temp.substring(j, j+1).equals(" ")){
							//System.out.println(i+" "+j);
							if(j==0){
								if(gate1x==-1){
									gate1x=0;
									gate1y=w-(i+1)/2;
								}
								else if(gate2x==-1){
									gate2x=0;
									gate2y=w-(i+1)/2;
								}
							}
							else if(j==temp.length()-1){
								if(gate1x==-1){
									gate1x=h-1;
									gate1y=w-(i+1)/2;
								}
								else if(gate2y==-1){
									gate2x=h-1;
									gate2y=w-(i+1)/2;
								}
							}
						}
						else if(temp.substring(j,j+1).equals(" ") && j!=temp.length()-1){
							connected[j/2][w-(i+1)/2]=true;
						}
					}
				}
				if(i%2==0){
					for(int j=1;j<temp.length();j+=2){
						if((i==0 || i==2*w) && temp.substring(j, j+1).equals(" ")){
							if(i==0){
								if(gate1y==-1){
									gate1y=w-1;
									gate1x=(j+1)/2-1;
								}
								else if(gate2y==-1){
									gate2y=w-1;
									gate2x=(j+1)/2-1;
								}
							}
							else if(i==2*w){
								if(gate1y==-1){
									gate1y=0;
									gate1x=(j+1)/2-1;
								}
								else if(gate2y==-1){
									gate2y=0;
									gate2x=(j+1)/2-1;
								}
							}
						}
						else if(temp.substring(j,j+1).equals(" ") && i!=2*w){
							connected2[(j+1)/2-1][w-(i)/2-1]=true;
						}
					}
				}
				
			}
			distance=new int[h][w];
			visited=new boolean[h][w];
			
			for(int i=0;i<distance.length;i++){
				for(int j=0;j<distance[i].length;j++){
					distance[i][j]=Integer.MAX_VALUE;
				}
			}
			
			distance[gate1x][gate1y]=0;
			distance[gate2x][gate2y]=0;
			visited[gate1x][gate1y]=true;
			//h=5 and w=3
			//first BFS on gate1x, gate1y
			q.add(gate1x);
			q.add(gate1y);
			test.bfs(0);
			visited=new boolean[h][w];
			visited[gate2x][gate2y]=true;
			q.add(gate2x);
			q.add(gate2y);
			test.bfs(0);
			
			int max=0;
			for(int i=0;i<distance.length;i++){
				for(int j=0;j<distance[i].length;j++){
					if(distance[i][j]+1>max){
						max=distance[i][j];
					}
				}
			}
			br1.write(Integer.toString(max+1));
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
	public void bfs(int mark){
	if(q.isEmpty()){
		return;
	}
	
	int constant=q.size();
	for(int i=0;i<constant;i+=2){
		int x=q.peek();
		q.remove();
		int y=q.peek();
		q.remove();
		if(x+1<h && y<w){
		if(connected[x+1][y] && !visited[x+1][y]){
			q.add(x+1);
			q.add(y);
			visited[x+1][y]=true;
			if(distance[x+1][y]>mark+1){
			distance[x+1][y]=mark+1;
			}
		}
		}
		
		if(0<x && x<h && y<w){
		if(connected[x][y] && !visited[x-1][y]){
			q.add(x-1);
			q.add(y);
			visited[x-1][y]=true;
			if(distance[x-1][y]>mark+1){
				distance[x-1][y]=mark+1;
			}
		}
		}
		
		if(x<h && y+1<w){
		if(connected2[x][y] && !visited[x][y+1]){
			q.add(x);
			q.add(y+1);
			visited[x][y+1]=true;
			if(distance[x][y+1]>mark+1){
				distance[x][y+1]=mark+1;
			}
		}
		}
		
		if(x<h && y-1<w && y>0){
		if(connected2[x][y-1] && !visited[x][y-1]){
			q.add(x);
			q.add(y-1);
			visited[x][y-1]=true;
			if(distance[x][y-1]>mark+1){
				distance[x][y-1]=mark+1;
			}
		}
		}
	}	
	bfs(mark+1);
}
}
