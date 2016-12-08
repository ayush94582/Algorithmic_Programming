/*
ID: ayush941
LANG: JAVA
TASK: closing
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class closing {
	
	public boolean initial(){
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(1);
		int count=0;
		while(!q.isEmpty()){
			int cur=q.poll();
			if(visited[cur]){
				continue;
			}
			visited[cur]=true;
			for(int i=0;i<adj.get(cur).size();i++){
				if(!visited[adj.get(cur).get(i)]){
					q.add(adj.get(cur).get(i));
				}
			}
			count++;
		}
		if(count==N){
			return true;
		}
		return false;
	}
	
	public boolean modified(int pos){
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(perm[perm.length-1]);
		int count=0;
		while(!q.isEmpty()){
			int cur=q.poll();
			if(visited[cur]){
				continue;
			}
			visited[cur]=true;
			for(int i=0;i<adj.get(cur).size();i++){
				if(!visited[adj.get(cur).get(i)]){
					q.add(adj.get(cur).get(i));
				}
			}
			count++;
		}
		if(count>=N-pos){
			return true;
		}
		return false;
	}
	
	static Map<Integer, LinkedList<Integer>> adj=new HashMap<Integer, LinkedList<Integer>>();
	static int N;
	static boolean[] visited;
	static int[] perm;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		closing test=new closing();

		try{
			fis= new FileInputStream("closing.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("closing.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));

			String[] in=br.readLine().split(" ");
			N=Integer.parseInt(in[0]); int M=Integer.parseInt(in[1]);
		
			for(int i=1;i<=N;i++){
				adj.put(i, new LinkedList<Integer>());
			}
			
			for(int i=0;i<M;i++){
				String[] temp=br.readLine().split(" ");
				adj.get(Integer.parseInt(temp[0])).add(Integer.parseInt(temp[1]));
				adj.get(Integer.parseInt(temp[1])).add(Integer.parseInt(temp[0]));
			}
			
			visited=new boolean[N+1];
			
			perm=new int[N];
			for(int i=0;i<N;i++){
				perm[i]=Integer.parseInt(br.readLine());
			}
				
			System.out.println(perm[61]);
			visited=new boolean[N+1];
			
			if(test.initial()){
			br1.write("YES");
			br1.newLine();
			}
			else{
				br1.write("NO");
				br1.newLine();
			}
			for(int i=0;i<N-1;i++){
				visited=new boolean[N+1];
				if(i==61){
					for(int j=0;j<adj.size();j++){
						System.out.println(j+" is j ");
						for(int k=0;k<adj.get(j).size();k++){
							System.out.println(k);
						}
					}
				}
				for(int j=1;j<=N;j++){
					if(j==perm[i]){
						adj.get(j).clear();
					}
					else{
						for(int k=0;k<adj.get(j).size();k++){
							if(adj.get(j).get(k)==perm[i]){
								adj.get(j).remove(k);
							}
						}
					}
				}
				if(test.modified(i+1)){
					br1.write("YES");
				}
				else{
					br1.write("NO");
				}
				br1.newLine();
			}
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
