/*
ID: ayush941
LANG: JAVA
TASK: diamond
*/
/*
Problem Link: http://www.usaco.org/index.php?page=viewproblem2&cpid=643
Algorithm: Interesting use of sorting/binary search after iterating through total number of diamonds
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


public class diamond {
	 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;

		try{
			fis= new FileInputStream("diamond.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("diamond.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			int N=Integer.parseInt(in[0]); int K=Integer.parseInt(in[1]);
			
			int[] arr=new int[N];
			for(int i=0;i<N;i++){
				arr[i]=Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(arr);
			
			int[] left=new int[N];
			left[N-1]=1;
			
			for(int i=N-2;i>=0;i--){
				int j=i+1; int count=1;
				while(arr[j]-arr[i]<=K){
					count++;
					j++;
					if(j==N){
						break;
					}
				}
				left[i]=Math.max(left[i+1], count);
				//System.out.println(i+" left "+left[i]);
			}
			
			int[] right=new int[N];
			right[0]=1;
			
			for(int i=1;i<N;i++){
				int j=i-1; int count=1;
				while(arr[i]-arr[j]<=K){
					count++;
					j--;
					if(j==-1){
						break;
					}
				}
				right[i]=Math.max(right[i-1], count);
				//System.out.println(i+" right "+right[i]);
			}
			
			int max=-1;
			for(int i=0;i<N;i++){
				if(max<left[i]+right[i]){
					max=left[i]+right[i];
				}
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
