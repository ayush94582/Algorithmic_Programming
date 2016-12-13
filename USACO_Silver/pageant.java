/*
ID: ayush941
LANG: JAVA
TASK: pageant
*/
/*
Link: http://usaco.org/index.php?page=viewproblem2&cpid=88
Algorithm: BFS noting the 3 paramters that define the cow's state (see code below)
Will run in O(NMlogM)
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

public class pageant {
	static boolean[][] ex;
	static int[][][] spots=new int[3][2500][2];
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean[][] visited1;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		pageant test=new pageant();

		try{
			fis= new FileInputStream("pageant.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("pageant.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));

			String[] a=br.readLine().split(" ");
			int N=Integer.parseInt(a[0]);
			int M=Integer.parseInt(a[1]);
			ex=new boolean[N+2][M+2];
			
			int x=0, y=0;
			for(int i=1;i<=N;i++){
				String temp=br.readLine();
				for(int j=1;j<=temp.length();j++){
					if(temp.substring(j-1, j).equals("X")){
						if(x==0 && y==0){
							x=i;
							y=j;
						}
						ex[i][j]=true;
					}
				}
			}
			
			visited1=new boolean[N+2][M+2];
			q.add(x);
			q.add(y);
			test.group();
			int count=0;
			for(int i=1;i<N+1;i++){
				for(int j=1;j<M+1;j++){
					if(visited1[i][j]){
						spots[0][count][0]=i;
						spots[0][count][1]=j;
						count++;
					}
				}
			}
			
			for(int i=1;i<ex.length;i++){	
				int c=0;
				for(int j=1;j<ex[0].length;j++){
					if(!visited1[i][j] && ex[i][j]){
						x=i;
						y=j;
						c++;
						break;
					}
				}
				if(c>0){
					break;
				}
			}
			
			boolean[][] visited2=visited1;
			visited1=new boolean[N+2][M+2];
			q.clear();
			q.add(x);
			q.add(y);
			test.group();
			int count1=0;
			for(int i=1;i<N+1;i++){
				for(int j=1;j<M+1;j++){
					if(visited1[i][j]){
						spots[1][count1][0]=i;
						spots[1][count1][1]=j;
						count1++;
					}
				}
			}
			
			for(int i=1;i<visited2.length;i++){
				for(int j=1;j<visited2[0].length;j++){
					if(visited2[i][j]=true){
						visited1[i][j]=true;
					}
				}
			}
			
			for(int i=1;i<ex.length;i++){	
				int c=0;
				for(int j=1;j<ex[0].length;j++){
					if(!visited1[i][j] && ex[i][j]){
						x=i;
						y=j;
						c++;
						break;
					}
				}
				if(c>0){
					break;
				}
			}
			
			System.out.println(x+" "+y);
			visited1=new boolean[N+2][M+2];
			q.clear();
			q.add(x);
			q.add(y);
			test.group();
			int count2=0;
			for(int i=1;i<N+1;i++){
				for(int j=1;j<M+1;j++){
					if(visited1[i][j]){
						spots[2][count2][0]=i;
						spots[2][count2][1]=j;
						count2++;
					}
				}
			}
			
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
		public void group(){
			if(q.isEmpty()){
				return;
			}
			int x=q.poll();
			int y=q.poll();
			visited1[x][y]=true;
			if(ex[x+1][y] && !visited1[x+1][y]){
				q.add(x+1);
				q.add(y);
			}
			if(ex[x-1][y] && !visited1[x-1][y]){
				q.add(x-1);
				q.add(y);
			}
			if(ex[x][y+1] && !visited1[x][y+1]){
				q.add(x);
				q.add(y+1);
			}
			if(ex[x][y-1] && !visited1[x][y-1]){
				q.add(x);
				q.add(y-1);
			}
			group();
		}
	
	}

