/*
ID: ayush941
LANG: JAVA
TASK: trapped
*/
/*
Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=554
Algorithm: Sort the haybales in decreasing order of size. Consider having an empty road, and place the haybales in that order. 
When placing a haybale, look immediately to its left and to its right and see if you can break through either one of those 
haybales if you were inside that interval. Mark that interval as "trapped" if so.

This will be O(NlogN) as long as you check to make sure that the interval isn't already marked as trapped.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class trapped {

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
	
	static class MyComparator implements Comparator<pair> {
		@Override
		public int compare(pair a, pair b){
			return a.x-b.x;
		}
	}
	
	public void sequencel(int left){
		if(left<0 || rightmost>=bales.size()){
			return;
		}
		
		while(bales.get(rightmost).x-bales.get(left).x>bales.get(rightmost).y){
			rightmost++;
			if(rightmost==bales.size()){
				break;
			}
		}
		
		if(rightmost<bales.size()){
			pos.add(bales.get(rightmost).x-bales.get(left).x-bales.get(left).y);
			//System.out.println(bales.get(rightmost).x-bales.get(left).x-bales.get(left).y);
		}
		
		if(bales.get(left+1).x-bales.get(left).x>bales.get(left).y){
			sequencel(left-1);
		}
		else{
			return;
		}
	}
	
	public void sequencer(int right){
		if(right>=bales.size() || leftmost<0){
			return;
		}
		
		while(bales.get(right).x-bales.get(leftmost).x>bales.get(leftmost).y){
			leftmost--;
			if(leftmost==-1){
				break;
			}
		}
		
		//System.out.println("right is: "+right+" and leftmost is: "+leftmost);
		if(leftmost>=0){
			pos.add(bales.get(right).x-bales.get(leftmost).x-bales.get(right).y);
		}
		
		if(bales.get(right).x-bales.get(right-1).x>bales.get(right).y){
			sequencer(right+1);
		}
		else{
			return;
		}
	}

	
	static ArrayList<pair> bales=new ArrayList<pair>();
	static int ind; //lower bound index
	static pair first;
	static pair last;
	static ArrayList<Integer> pos=new ArrayList<Integer>();
	static int rightmost;
	static int leftmost;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		trapped test=new trapped();

		try{
			fis= new FileInputStream("trapped.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("trapped.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			int N=Integer.parseInt(in[0]);
			int B=Integer.parseInt(in[1]);
			
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				bales.add(new pair(Integer.parseInt(temp[1]), Integer.parseInt(temp[0])));
			}
			
			Collections.sort(bales, new MyComparator());
			first=bales.get(0); last=bales.get(bales.size()-1);
			
			
			for(int i=0;i<bales.size();i++){
				if(bales.get(i).x<B && B<bales.get(i+1).x){
					ind=i;
					break;
				}
			}
			
			rightmost=ind+1;
			test.sequencel(ind);
			leftmost=ind;
			test.sequencer(ind+1);
			
			int min=Integer.MAX_VALUE;
			for(int i=0;i<pos.size();i++){
				if(min>pos.get(i)){
					min=pos.get(i);
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
