/*
ID: ayush941
LANG: JAVA
TASK: angry
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class angry {
	
	static int N;
	static int K;
	static int[] bales;
	static int count=0;
	
	public void solve(int r, int pos){
		
		count++;
		int ind=-1;
		for(int i=pos+1;i<N;i++){
			if(bales[i]>bales[pos]+2*r){
				ind=i;
				break;
			}
		}
		
		if(ind>=0){
			solve(r, ind);
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=null;	
		InputStreamReader isr=null;
		BufferedReader br= null;
		BufferedWriter br1= null;
		FileOutputStream fos = null;
		angry test=new angry();

		try{
			fis= new FileInputStream("angry.in");
			isr= new InputStreamReader(fis);
			br=new BufferedReader(isr);
			fos= new FileOutputStream("angry.out");
			br1 = new BufferedWriter(new OutputStreamWriter(fos));
			
			String[] in=br.readLine().split(" ");
			N=Integer.parseInt(in[0]);
			K=Integer.parseInt(in[1]);
			bales=new int[N];
			
			for(int i=0;i<N;i++){
				bales[i]=Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(bales);

			
		for(int i=1;i<bales.length;i++){
				test.solve(i, 0);
				if(count<=K){
					br1.write(Integer.toString(i));
					br1.newLine();
					br1.flush();
					break;
				}
				count=0;
			}
			
			
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
