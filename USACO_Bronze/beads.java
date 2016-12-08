package src;

/*
ID: ayush941
LANG: JAVA
TASK: beads
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

				public class beads {
					public static void main(String[] args) throws Exception{
						FileInputStream fis=null;
						InputStreamReader isr=null;
						BufferedReader br= null;
						BufferedWriter br1= null;
						FileOutputStream fos = null;
						
						try{
							fis= new FileInputStream("beads.in");
							isr= new InputStreamReader(fis);
							br=new BufferedReader(isr);
							fos= new FileOutputStream("beads.out");
							br1 = new BufferedWriter(new OutputStreamWriter(fos));
							int number=Integer.parseInt(br.readLine());
							String necklaceBeads=br.readLine();

							int maxBeads = 0;
							 
						       for (int i = 0; i < number; i++) {
						 
						           int count = 0;

						           String straightNecklaceBeads = necklaceBeads.substring(i + 1)
						 
						                   + necklaceBeads.substring(0, i + 1);

						            char colorCheck = ' ';
						            for (int j = 0; j < straightNecklaceBeads.length(); j++) {
						                if (straightNecklaceBeads.charAt(j) == 'w')
						                    count++;
						                else if (colorCheck == ' ') {
						                    colorCheck = straightNecklaceBeads.charAt(j);
						                    count++;
						                } else {
						                     if (straightNecklaceBeads.charAt(j) == colorCheck)
						                        count++;
						                     else 
						                       break;
						               }
						            }

						            colorCheck = ' ';
						            
						             for (int j = (straightNecklaceBeads.length() - 1); j >= 0; j--) {
						                if (straightNecklaceBeads.charAt(j) == 'w')
						                    count++;
						                else if (colorCheck == ' ') {
						                    colorCheck = straightNecklaceBeads.charAt(j);
						                    count++;
						                } else {
						                    if (straightNecklaceBeads.charAt(j) == colorCheck) 
						                        count++;
						                    else        
						                       break;
						               }
						            }
						            maxBeads = Math.max(maxBeads, count);
						        }
						       
						          if (maxBeads > number){
						           maxBeads = number;
						          
						       }
						          br1.write(Integer.toString(maxBeads));	
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