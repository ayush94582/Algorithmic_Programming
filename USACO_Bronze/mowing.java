/*
ID: ayush941
LANG: JAVA
TASK: mowing
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class mowing {
	
	static String[][] grid;
	static int m;
	static int n;
	static int count=0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		mowing test=new mowing();

		try{
			fis= new FileInputStream("mowing.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("mowing.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			m=Integer.parseInt(in[0]);
			n=Integer.parseInt(in[1]);
			grid=new String[m][n];
			
			for(int i=0;i<m;i++){
				String temp=br.readLine();
				for(int j=0;j<temp.length();j++){
					grid[i][j]=temp.substring(j, j+1);
				}
			}
			
			test.solve(0, 0, grid[0][0]);
			br1.write(Integer.toString(count));
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

		public void solve(int x, int y, String cur){
			if(x==m-1 && y==n-1){
				count++;
				return;
			}
			if(x>=m-1 || y>=n-1){
				return;
			}
			
			for(int i=x+1;i<m;i++){
				for(int j=y+1;j<n;j++){
					if(!grid[i][j].equals(cur)){
						solve(i,j,grid[i][j]);
					}
				}
			}
		}
	}

