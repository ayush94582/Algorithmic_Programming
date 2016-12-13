/*
Interesting Graph Theory Challenge to detect 4-cycles in O(N^2), a large improvement over the naive O(N^4). Mathematical proof 
can be found on my website to prove that O(N^2) is a lower bound runtime in worst-case.
*/

import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class fourcycle 
{	
   /* Makes use of Map collection to store the adjacency list for each vertex.*/
    private  Map<Integer, List<Integer>> Adjacency_List;	
    int x,y;
    
   /*
    * Initializes the map to with size equal to number of vertices in a graph
    * Maps each vertex to a given List Object 
    */
    public fourcycle(int number_of_vertices)
    {
        Adjacency_List = new HashMap<Integer, List<Integer>>();	
        for (int i = 1 ; i <= number_of_vertices ; i++)
        { 
            Adjacency_List.put(i, new LinkedList<Integer>());
        }
    }
    
    public fourcycle(int a, int b) {
        x = a;
        y = b;
    }

   
   static class Key {

		//constructs a key object that is an ordered pair (x,y)
	    private final int x;
	    private final int y;

	    public Key(int a, int b) {
	        x = a;
	        y = b;
	    }

	    //comparing two keys
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Key)) return false;
	        Key key = (Key) o;
	        return x == key.x && y == key.y;
	    } 

	 	
	    public int hashCode() {
	        int result = x;
	        result = 31 * result + y;
	        return result;
	    }
	}
    
    public void finish(Map<Integer, List<Integer>> adjlist, int N){
    	boolean cycleexists=false;
    	Map<fourcycle, Integer> detect=new HashMap<fourcycle, Integer>();
		for(int i=1;i<=N;i++){
			for(int j=0;j<adjlist.get(i).size();j++){
				for(int k=j+1;k<adjlist.get(i).size();k++){
					fourcycle check= new fourcycle(adjlist.get(i).get(j), adjlist.get(i).get(k)); //check to see if this key is already in keyset of detect
					if(detect.containsKey(check)){
						System.out.println(i+" "+adjlist.get(i).get(j)+" "+adjlist.get(i).get(k)+" "+detect.get(check));
						cycleexists=true;
						break;
					}
					else{
						detect.put(check, i); //store i as being adjacent to the key check
					}
				}
				if(cycleexists){
					break;
				}
			}
			if(cycleexists){
				break;
			}
		}
		if(!cycleexists){
			System.out.println("There is no four-cycle");
		}
    }
    
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("How many nodes and edges are in the graph?");
		int N=sc.nextInt(), E=sc.nextInt();
		
		fourcycle test=new fourcycle(N);

		Map<Integer, List<Integer>> adjlist=new fourcycle(N).Adjacency_List;	
		sc.nextLine();
		
		//reading in edges and updating the adjacency list
		System.out.println("Enter edges in adjacency list form");
		for(int i=0;i<E;i++){
			String[] in=sc.nextLine().split(" ");
			adjlist.get(Integer.parseInt(in[0])).add(Integer.parseInt(in[1]));
			adjlist.get(Integer.parseInt(in[1])).add(Integer.parseInt(in[0]));
		}
			
		test.finish(adjlist, N);
	}
	
	
	
}
	
	
	
	
