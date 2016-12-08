/*
ID: ayush941
LANG: JAVA
TASK: stampede
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class stampede {

	static class interval{
	    private final int l;
	    private final int u;

	    public interval(int a, int b) {
	        l = a;
	        u = b;
	    }

	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof interval)) return false;
	        interval key = (interval) o;
	        return l == key.l && u == key.u;
	    } 
	    
	    public int hashCode() {
	        int result = l;
	        result = 31 * result + u;
	        return result;
	    }
	}
	
	static class MyComparator implements Comparator<interval> {
		@Override
		public int compare(interval a, interval b){
			return a.l-b.l;
		}
	}
	
	public interval intersection(interval a, interval b){
		if(a.l>=b.l && a.l<=b.u){
			return (new interval(a.l, Math.min(a.u, b.u)));
		}
		if(b.l>=a.l && b.l<=a.u){
			return (new interval(b.l, Math.min(a.u, b.u)));
		}
		return null;
	}
	
	public interval union(interval a, interval b){
		
		if(a==null){
			return b;
		}
		
		else if(intersection(a, b)!=null){
			int lower=Math.min(Math.min(a.l, b.l), Math.min(a.u, b.u));
			int upper=Math.max(Math.max(a.l, b.l), Math.max(a.u, b.u));
			return new interval(lower,upper);
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		stampede test=new stampede();

		try{
			fis= new FileInputStream("stampede.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("stampede.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			int N=Integer.parseInt(br.readLine());
			int[] loc=new int[N];
			int[] rates=new int[N];
			
			for(int i=0;i<N;i++){
				String[] in=br.readLine().split(" ");
				loc[i]=Integer.parseInt(in[0]);
				rates[i]=Integer.parseInt(in[2]);
			}
			
			interval[] arr=new interval[N];
			
			for(int i=0;i<N;i++){
				arr[i]=new interval(loc[i]*rates[i]*-1-rates[i],loc[i]*rates[i]*-1);
			}
			
			int count=0;
			for(int i=1;i<N;i++){
				interval temp=arr[i];
				interval newtemp = null;
				for(int j=0;j<N && j!=i;j++){
					interval check=test.union(newtemp, arr[j]);
					System.out.println(check.l+" "+check.u);
					if(check!=null){
						newtemp=new interval(check.l, check.u);
					}
				}
				if(!test.intersection(newtemp, temp).equals(temp)){
					count++;
				}
			}
		
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
	
}
