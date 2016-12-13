/*
ID: ayush941
LANG: JAVA
TASK: fuel
*/
/*
Problem Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=283
Approach: Use LIFO Property of Stack, see mathematical insight on link above
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class fuel {
	
	private class Station {

		//constructs a key object that is an ordered pair (x,y)
	    private final int x;
	    private final int y;

	    public Station(int a, int b) {
	        x = a;
	        y = b;
	    }

	    //comparing two keys
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Station)) return false;
	        Station key = (Station) o;
	        return x == key.x && y == key.y;
	    } 

	 	
	    public int hashCode() {
	        int result = x;
	        result = 31 * result + y;
	        return result;
	    }
	}

	static Station[] arr;
	static int[] cheap;
	
	public int solve(int N, int G, int B, int D, Map<Integer, Integer> map, int[] sort){ //B is starting, D is distance, G is capacity
		Arrays.sort(sort);
		arr=new Station[N];
		for(int i=0;i<sort.length;i++){
			arr[i]=new Station(sort[i], map.get(sort[i]));
		}
	
		cheap=new int[arr.length];
		cheap[arr.length-1]=arr.length-1;
		
		Stack<Integer> lifo=new Stack<Integer>();
		
		for(int i=0;i<arr.length;i++){
			lifo.add(i);
		}
		
		
		for(int i=arr.length-2;i>=0;i--){
			Stack<Integer> dupl=lifo;
			for(int j=i+1;j<lifo.size();j++){
				if(arr[dupl.peek()].y<arr[i].y){
					cheap[i]=dupl.peek();
				}
				dupl.pop();
			}
		}
		
		for(int i=0;i<cheap.length;i++){
			System.out.println(i+" "+cheap[i]);
		}
		
		int gas=B-sort[0];
		int pos=sort[0];
		int cost=0;
		int i=0;
		while(pos!=sort[sort.length-1]){
			int temp=cheap[i];
			int dif=arr[temp].x-pos;
			if(dif==0){
				cost+=(G-gas)*arr[temp].y;
				gas=G-(sort[i+1]-sort[i]);
				pos=sort[i+1];
			}
			else if(gas+G>=dif){
				cost+=Math.max(0, (dif-gas)*arr[i].y);
				gas=Math.max(0, gas-dif);
				pos=sort[temp];
			}
			else{
				cost+=(G-gas)*arr[i].y;
				gas=G-(sort[i+1]-sort[i]);
				pos=sort[i+1];
			}
			i++;
		}
		
		cost+=Math.max((D-pos-gas)*arr[arr.length-1].y, 0);
		return cost;
	}
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		fuel test=new fuel();

		try{
			fis= new FileInputStream("fuel.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("fuel.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			int N=Integer.parseInt(in[0]);
			int G=Integer.parseInt(in[1]);
			int B=Integer.parseInt(in[2]);
			int D=Integer.parseInt(in[3]);
			
			Map<Integer, Integer> map=new HashMap<Integer, Integer>();
			int[] sort=new int[N];
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				map.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				sort[i]=Integer.parseInt(temp[0]);
			}
			
			br1.write(Integer.toString(test.solve(N, G, B, D, map, sort)));
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
