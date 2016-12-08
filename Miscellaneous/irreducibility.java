import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import java.math.BigDecimal;
import java.util.Scanner;

public class irreducibility {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("What is the order of the matrix?");
		int N=sc.nextInt();
		System.out.println("How many matrices do you want to test");
		int num=sc.nextInt();
		for(int k=0;k<num;k++){
		double[][] entries=new double[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				entries[i][j]=1;
			}
		}
		
		System.out.println("How many zeroes are in the matrix?");
		int zercount=sc.nextInt();
		System.out.println("Enter the (i,j) pair of the entries of your matrix that are equal to 0 as two integers");
		System.out.println("Note: After each coordinate (i, j seperate), please press enter. Also, each coordinate goes from 0 to order of matrix-1");
		for(int i=0;i<zercount;i++){
			int tempi=sc.nextInt();
			int tempj=sc.nextInt();
			entries[tempi][tempj]=0;
		}
	
		Matrix mat=new Matrix(entries);
		mat.plusEquals(mat.identity(N, N));
		Matrix newmat=mat.identity(N, N);
		for(int i=0;i<N-1;i++){
			newmat=newmat.times(mat);
		}
		
		int count=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(newmat.get(i, j)<=0){
					count++;
					break;
				}
			}
			if(count>0){
				break;
			}
		}
		if(count==0){
			System.out.println("This is irreducible");
		}
		else{
			System.out.println("this is redubcible");
		}
		
		}
	}
}
