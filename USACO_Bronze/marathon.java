/*
 ID: ayush941
 LANG: JAVA
 TASK: diamond
 */

package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;




			public class marathon {
				public static void main(String[] args) throws Exception{
					FileInputStream fis=null;
					InputStreamReader isr=null;
					BufferedReader br= null;
					BufferedWriter br1= null;
					FileOutputStream fos = null;

				
					try{
						fis= new FileInputStream("marathon.in");
						isr= new InputStreamReader(fis);
						br=new BufferedReader(isr);
						fos= new FileOutputStream("marathon.out");
						br1 = new BufferedWriter(new OutputStreamWriter(fos));
						int N=Integer.parseInt(br.readLine());
						int[][] arr= new int[N][2];
						int[] difference= new int[N-1];
						ArrayList<Integer> changed= new ArrayList<Integer>();
						for(int i=0; i<N; i++){
							String[] array=new String[2];
							array=br.readLine().split(" ");
							arr[i][0]=Integer.parseInt(array[0]);
							arr[i][1]=Integer.parseInt(array[1]);						
					}
						
						
						
						for(int i=0; i<difference.length;i++){
							difference[i]=Math.abs(arr[i][0]-arr[i+1][0])+Math.abs(arr[i][1]-arr[i+1][1]);
						}
						
						int totaldistance=0;
						for(int i=0; i<difference.length;i++){
							totaldistance=totaldistance+difference[i];
						}
						
						
						for(int i=0; i<difference.length-1;i++){
						int z=Math.abs(arr[i+2][0]-arr[i][0])+Math.abs(arr[i+2][1]-arr[i][1]);
						int x=totaldistance-difference[i]-difference[i+1]+z;
						changed.add(x);
						}
						
						int min=changed.get(0);
						for(int i=1; i<changed.size();i++){
							if(changed.get(i)<min){
								min=changed.get(i);
							}
						}
						
						
						br1.write(Integer.toString(min));
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
			