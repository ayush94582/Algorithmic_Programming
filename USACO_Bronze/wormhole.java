/*
ID: ayush941
LANG: JAVA
TASK: wormhole
*/
import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class wormhole 
{ 
	public static void main(String[] args) throws Exception 
	{
	  ArrayList < MyPoint > points = new ArrayList<MyPoint> () ;
	    
	  	String name = "wormhole" + ".";
	  	String inFileName = name + "in";
	  	String outFileName = name + "out" ;
	  	
		Scanner scan = new Scanner( new File (inFileName)  );	
	    PrintWriter out=new PrintWriter( outFileName  );
		int N=scan.nextInt ();
		for (int i=0;i<N;++i ) {
			int a=scan.nextInt ();
			int b=scan.nextInt ();
			MyPoint p = new MyPoint (a,b) ;
			points.add ( p ) ;
		}
		Collections.sort ( points ) ; // sort by Y, break tie by X
		System.out.println ( points  ); 
		MyPoint p0 = null;
		for ( MyPoint p1: points ) {
			if (p0 != null ) {
				if ( p0.getY() == p1.getY() ) {
          p0.rights = p1;
				}
			}
			p0 = p1;
		}
		
    int count [] = {0};
		pairing ( points, count, 0 ) ;
		out.println ( count[0] ) ;
		System.out.println ( count[0] ) ;

		out.close();
	}

	public static Boolean isLooping ( 
			MyPoint p0, 
			Boolean trace) 
	{
		Boolean ans = false ;
		HashSet <MyPoint> hs = new HashSet<MyPoint> ();
        for (;;) {
        		MyPoint p1 = p0.partner ;
        		if ( trace  ) {
        			System.out.printf (" (%.0f,%.0f) --->(%.0f,%.0f) \n",
        					p0.getX(), p0.getY(), p1.getX(), p1.getY() ) ;    					
        		}
        		if ( hs.contains(p0)  )  {
        			return true;
        		}
        		hs.add(p0);

        		if ( p1.rights != null  ) {
        			p0 = p1.rights ;
        		} else {
        			break;
        		}
        }
        return  ans;

	}
	public static Boolean checkLoop ( 
			ArrayList < MyPoint > points,
				int[] count
	) {
		Boolean ans = false ;
		for (MyPoint p0 : points ) {
		    	ans =  isLooping ( p0, false ) ;
		    	if (ans)  {
		    		count[0] ++;
		    		break;
		    	}
		}

        return ans;
		
	}
	public static void pairing ( 		
			ArrayList < MyPoint > points,
			int[] count,
			int i ) 
	{
		int n = points.size ();
	
	    MyPoint pi = points.get(i) ;

	    for ( ;i<n ;++i ) { // look for the next unpaired hole
	    	   pi = points.get(i) ;
	    	   Boolean paired = pi.partner != null ;
	       if (!paired) break;
	    }
      if ( i==n ) {
			   checkLoop (points, count ) ;	    	 
			   return ;
      }
	    for (int j=i+1;j<n;++j ) {  // try all possible partners for hole i
	    		MyPoint pj = points.get(j) ;
	    		if ( pj.partner != null  ) continue;
          pi.partner = pj;
          pj.partner = pi;

	        pairing  ( points, count, i+1 );  	
	        
          pi.partner = null;
          pj.partner = null;
	    }

	}

}

class MyPoint extends Point implements Comparable<MyPoint>  { 
  public MyPoint rights;
  public MyPoint partner ;
  public MyPoint ( int x, int y ) {
    this.x = x;
    this.y = y;
  }
  public int compareTo(MyPoint p2)
  {
		int diff = (int) (getY() - p2.getY()); // compare Y first
		if ( diff ==0 ) {
			diff = (int) (getX() - p2.getX()); // compare X next
		}
	  return diff ;
  }
  @Override
  public  String  toString () 
  {
    return "(" + x + "," + y + ")";
  }
}

