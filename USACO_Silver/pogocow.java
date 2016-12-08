/*
ID: ayush941
LANG: JAVA
TASK: pogocow
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class pogocow {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		pogocow test=new pogocow();

		try{
			fis= new FileInputStream("pogocow.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("pogocow.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));

			int N=Integer.parseInt(br.readLine());
			int[] x=new int[N];
			int[] val=new int[N];
			
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				x[i]=Integer.parseInt(temp[0]);
				val[i]=Integer.parseInt(temp[1]);
			}
			
			int[] xsort=new int[N];
			for(int i=0;i<x.length;i++){
				xsort[i]=x[i];
			}
			
			int[] valsort=new int[N];
			Arrays.sort(xsort);
			int max=xsort[xsort.length-1];
			for(int i=0;i<xsort.length;i++){
				for(int j=0;j<x.length;j++){
					if(xsort[i]==x[j]){
						valsort[i]=val[j];
						break;
					}
				}
			}
		
		int result=0;
		
		for(int ii=0;ii<2;ii++){
			int[][] dp=new int[N][N]; //first parameter: current index of location, second parameter: possible next target
			for(int i=N-1;i>=0;i--){
				int k=N-1;
				for(int j=i+1;j<N;j++){
					int tempmax=0;
					while(Math.abs(xsort[k]-xsort[j])>=Math.abs(xsort[j]-xsort[i]) && k>j){
						if(tempmax<dp[j][k]){
							tempmax=dp[j][k];
						}
						k--;
					}
					if(valsort[i]+tempmax>dp[i][j]){
						dp[i][j]=valsort[i]+tempmax;
					}
					if(dp[i][j]>result){
						result=dp[i][j];
					}
				}
			}
			test.reverse(xsort);
			test.reverse(valsort);
			dp=new int[N][N];
		}
			
		br1.write(Integer.toString(result));
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
	public void reverse(int[] arr){
		int N=arr.length;
		int[] arr2=new int[N];
		for(int i=0;i<N;i++){
			arr2[i]=arr[N-1-i];
		}
		arr=arr2;
	}
	
}
