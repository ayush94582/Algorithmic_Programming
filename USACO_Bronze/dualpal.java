package src;
/*
ID: ayush941
LANG: JAVA
TASK: dualpal
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

	

				public class dualpal {
					public static void main(String[] args) throws Exception{
						FileInputStream fis=null;
						InputStreamReader isr=null;
						BufferedReader br= null;
						BufferedWriter br1= null;
						FileOutputStream fos = null;
						dualpal test= new dualpal();
						
						try{
							fis= new FileInputStream("dualpal.in");
							isr= new InputStreamReader(fis);
							br=new BufferedReader(isr);
							fos= new FileOutputStream("dualpal.out");
							br1 = new BufferedWriter(new OutputStreamWriter(fos));
							String[] arr=br.readLine().split(" ");
							int N=Integer.parseInt(arr[0]);
							int S=Integer.parseInt(arr[1]);
							ArrayList<Integer> list=new ArrayList<Integer>();
							int count=0;
							for(int i=S+1; i<13000;i++){
								for(int j=2; j<11;j++){
									String x=test.changed(i, j);
									if(test.palindrome(x)){
										count++;
									}
									
								}
								if(count>1){
									list.add(i);
								}
								count=0;
								
							}
						for(int i=0;i<N;i++){
							br1.write(Integer.toString(list.get(i)));
							br1.newLine();
							System.out.println(list.get(i));
						}
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

					public String changed(int x, int base){
						ArrayList<Integer> converted=new ArrayList<Integer>();
						while(x/base !=0){
							converted.add(x%base);
							x= x/base;
					}				
						converted.add(x);
					String y=Integer.toString(converted.get(converted.size()-1));
					if(converted.size()>=2){
					for(int i=converted.size()-2;i>=0;i--){
						y=y+Integer.toString(converted.get(i));
					}}
					
					return y;
					}
					
					public boolean palindrome(String x){
						boolean palindrome=false;
						if(x.length()>1){
						for(int i=0; i<(x.length())/2;i++){
							if(x.charAt(i)==x.charAt(x.length()-i-1)){
								palindrome=true;
							}
							else{
								palindrome=false;
								break;
							}
						}
						}
						else if(x.length()==1){
							palindrome=true;
						}
						return palindrome;
					}
				}