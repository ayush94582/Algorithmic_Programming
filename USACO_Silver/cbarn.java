/*
ID: ayush941
LANG: JAVA
TASK: cbarn
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


public class cbarn {

	public void cycle(int start){
		if(count==N){
			return;
		}
		
		if(rooms[start]==0 && distance.isEmpty()){
			return;
		}
		
		count++;
		
		boolean check=false;
		if(!distance.isEmpty()){
			for(int i=0;i<distance.size();i++){
				distance.set(i, distance.get(i)+1);
			}
			sum+=distance.get(0)*distance.get(0);
			distance.remove(0);
			check=true;
		}
				
		if(check && distance.size()+rooms[start]>0){
			for(int i=0;i<rooms[start];i++){
				distance.add(0);
			}
		}
		
		if(!check && rooms[start]>1){
			for(int i=0;i<rooms[start]-1;i++){
				distance.add(0);
			}
		}
		
		if(start==N-1){
			cycle(0);
		}
		else{
			cycle(start+1);
		}
		
	
	}
	
	static int[] rooms;
	static ArrayList<Integer> distance=new ArrayList<Integer>();
	static int count=0;
	static int N;
	static int sum=0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		cbarn test=new cbarn();

		try{
			fis= new FileInputStream("cbarn.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("cbarn.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			N=Integer.parseInt(br.readLine());
			rooms=new int[N];
			
			int ind=-1;
			for(int i=0;i<N;i++){
				rooms[i]=Integer.parseInt(br.readLine());

			}
			
			int max=-1;
			for(int i=0;i<N;i++){
				test.cycle(i);
				if(sum>max){
					max=sum;
				}
				sum=0;
				count=0;
				distance.clear();
			}
			
			br1.write(Integer.toString(max));
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
