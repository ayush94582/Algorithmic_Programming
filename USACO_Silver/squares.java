/*
ID: ayush941
LANG: JAVA
TASK: squares
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import week1011.lightson.pair;

public class squares {
	
	static class pair {

		//constructs a key object that is an ordered pair (x,y)
	    private final int x;
	    private final int y;

	    public pair(int a, int b) {
	        x = a;
	        y = b;
	    }

	    //comparing two keys
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
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;

		try{
			fis= new FileInputStream("squares.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("squares.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));

			String[] a=br.readLine().split(" ");
			int N=Integer.parseInt(a[0]); int K=Integer.parseInt(a[1]);
			pair[] in=new pair[N];
			
			for(int i=0;i<N;i++){
				String[] temp=br.readLine().split(" ");
				in[i]=new pair(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			}
			
			pair c1=null, c2=null;
			for(int i=0;i<N;i++){
				boolean check=false;
				for(int j=i+1;j<N;j++){
					if(Math.abs(in[i].x-in[j].x)<=K/2 && Math.abs(in[i].y-in[j].y)<=K/2){
						c1=in[i];
						c2=in[j];
						check=true;
						break;
					}
				}
				if(check){
					break;
				}
			}
			
			int[] arrx={c1.x-K/2, c1.x+K/2, c2.x-K/2, c2.x+K/2};
			Arrays.sort(arrx);
			
			int w=arrx[2]-arrx[1];
			
			int[] arry={c1.y-K/2, c1.y+K/2, c2.y-K/2, c2.y+K/2};
			Arrays.sort(arry);
			
			int h=arry[2]-arry[1];
			
			br1.write(Integer.toString(w*h));
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
