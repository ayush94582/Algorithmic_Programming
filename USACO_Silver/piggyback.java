/*
ID: ayush941
LANG: JAVA
TASK: piggyback
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class piggyback {
	
	public void buildB(){
		q.add(1);
		boolean[] visited=new boolean[N+1];
		Bdistance[1]=0;
		visited[0]=true;
		while(!q.isEmpty()){
			int cur=q.remove();
			if(visited[cur]){
				continue;
			}
			visited[cur]=true;
			for(int i=0;i<adj.get(cur).size();i++){
				if(!visited[adj.get(cur).get(i)]){
					q.add(adj.get(cur).get(i));
					if(Bdistance[adj.get(cur).get(i)]>Bdistance[cur]+1){
						Bdistance[adj.get(cur).get(i)]=Bdistance[cur]+1;
					}
				}
			}
		}
	}
	
	public void buildE(){
		q.add(2);
		boolean[] visited=new boolean[N+1];
		Edistance[2]=0;
		visited[0]=true;
		while(!q.isEmpty()){
			int cur=q.remove();
			if(visited[cur]){
				continue;
			}
			visited[cur]=true;
			for(int i=0;i<adj.get(cur).size();i++){
				if(!visited[adj.get(cur).get(i)]){
					q.add(adj.get(cur).get(i));
					if(Edistance[adj.get(cur).get(i)]>Edistance[cur]+1){
						Edistance[adj.get(cur).get(i)]=Edistance[cur]+1;
					}
				}
			}
		}
	}
	
	public void buildN(){
		q.add(N);
		boolean[] visited=new boolean[N+1];
		Ndistance[N]=0;
		visited[0]=true;
		while(!q.isEmpty()){
			int cur=q.remove();
			if(visited[cur]){
				continue;
			}
			visited[cur]=true;
			for(int i=0;i<adj.get(cur).size();i++){
				if(!visited[adj.get(cur).get(i)]){
					q.add(adj.get(cur).get(i));
					if(Ndistance[adj.get(cur).get(i)]>Ndistance[cur]+1){
					Ndistance[adj.get(cur).get(i)]=Ndistance[cur]+1;
					}
				}
			}
		}
	}

	static int B, E, N, P, M;
	static Map<Integer, List<Integer>> adj=new HashMap<Integer, List<Integer>>();
	static Queue<Integer> q = new LinkedList<Integer>();
	static int[] Bdistance;
	static int[] Edistance;
	static int[] Ndistance;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			FileInputStream fis=null;	
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;
			piggyback test=new piggyback();
			
			try{
				fis= new FileInputStream("piggyback.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("piggyback.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));

				String[] in=br.readLine().split(" ");
				B=Integer.parseInt(in[0]);
				E=Integer.parseInt(in[1]);
				P=Integer.parseInt(in[2]);
				N=Integer.parseInt(in[3]);
				M=Integer.parseInt(in[4]);
				
				for(int i=1;i<=N;i++){
					adj.put(i, new LinkedList<Integer>());
				}
				
				for(int i=0;i<M;i++){
					String[] edge=br.readLine().split(" ");
					adj.get(Integer.parseInt(edge[0])).add(Integer.parseInt(edge[1]));
					adj.get(Integer.parseInt(edge[1])).add(Integer.parseInt(edge[0]));
				}
				
				Bdistance=new int[N+1];
				Edistance=new int[N+1];
				Ndistance=new int[N+1];
				Arrays.fill(Bdistance, Integer.MAX_VALUE);
				Arrays.fill(Edistance, Integer.MAX_VALUE);
				Arrays.fill(Ndistance, Integer.MAX_VALUE);
				test.buildB();
				test.buildE();
				test.buildN();
				
			int min=Integer.MAX_VALUE;	
			for(int i=3;i<=N;i++){
				if(Bdistance[i]*B+Edistance[i]*E+Ndistance[i]*P<min){
					min=Bdistance[i]*B+Edistance[i]*E+Ndistance[i]*P;
				}
			}
			
			br1.write(Integer.toString(min));
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
