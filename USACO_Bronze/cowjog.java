package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



	public class cowjog {
		public static void main(String[] args) throws Exception{
			FileInputStream fis=null;
			InputStreamReader isr=null;
			BufferedReader br= null;
			BufferedWriter br1= null;
			FileOutputStream fos = null;

		
			try{
				fis= new FileInputStream("cowjog.in");
				isr= new InputStreamReader(fis);
				br=new BufferedReader(isr);
				fos= new FileOutputStream("cowjog.out");
				br1 = new BufferedWriter(new OutputStreamWriter(fos));
				int numberofcows=Integer.parseInt(br.readLine());
				ArrayList<Integer> array=new ArrayList<Integer>();
				
				for(int i=0;i<numberofcows;i++){
					array.add(Integer.parseInt(br.readLine().split(" ")[1]));
				}
				boolean flag = false;
				ArrayList<Integer> array1 = null;

				while(!flag){
					array1=new ArrayList<Integer>();
					array1 = getMin(array);
					if(array.size() == array1.size()){
						flag = true;
						break;
					}
					else{
						array = null;
						array = array1;
					}
					
				}
		
				
				br1.write(Integer.toString(array1.size()));
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
	
	
		
		public static ArrayList<Integer> getMin(ArrayList<Integer> y){
			ArrayList<Integer> x=new ArrayList<Integer>();
			for(int i=0;i<y.size()-1;i++){
				
				if(y.get(i) <= y.get(i+1)){
					
					x.add(y.get(i));
				
				}
				
				
				
			}
			x.add(y.get(y.size()-1));
			return x;
		}

}