import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import java.math.BigDecimal;
import java.util.Scanner;

public class matrixfunction {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("What is the order of the matrix?");
		int N=sc.nextInt();
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
		EigenvalueDecomposition eig=new EigenvalueDecomposition(mat); //creation of an EigenvalueDecomposition object in the EigenvalueDecomposition class
		double[] eigenvalues=eig.getRealEigenvalues();
		double[] imeig=eig.getImagEigenvalues();
		System.out.println("For what range of m do you want to find the maximum value of c");
		int lower=sc.nextInt(), upper=sc.nextInt();
		
	
		int count=0;
		BigDecimal val=new BigDecimal(1);
		BigDecimal lastc=new BigDecimal(0);
		for(int i=lower;i<=upper;i++){
			double sum=0;
			for(int j=0;j<eigenvalues.length;){
				if(imeig[j]==0){
				sum+=Math.pow(eigenvalues[j], i);
				j++;
				}
				else{
					sum+=eigenvalues[j]*eigenvalues[j]+imeig[j]*imeig[j];
					j+=2;
				}
			}
			BigDecimal maxc=new BigDecimal(Math.pow(sum, 1.0/i)/N);
			System.out.println(maxc+" at "+i);
			if(val.compareTo(maxc)==1){
				count++;
				lastc=maxc;
			}
			else{
				System.out.println("Starts increasing at: "+i+" with value: "+val);
				
			}
			val=maxc;
		}
		
		if(count==upper-lower+1){
			System.out.println("Decreasing on Interval and last value is: "+lastc);
		}
		
		else{
			System.out.println("Increasing on interval");
		}
	}
}
