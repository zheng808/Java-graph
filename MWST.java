/* MWST.java
   CSC 226 - Spring 2015
   Assignment 1 - Minimum Weight Spanning Tree Template
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java MWST
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java MWST file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry A[i][j] of the adjacency matrix gives the weight of the edge from 
   vertex i to vertex j (if A[i][j] is 0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that A[i][j]
   is always equal to A[j][i].
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.util.Queue;

//Do not change the name of the MWST class
public class MWST{

	/* mwst(G)
		Given an adjacency matrix for graph G, return the total weight
		of all edges in a minimum weight spanning tree.
		
		If G[i][j] == 0, there is no edge between vertex i and vertex j
		If G[i][j] > 0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
        public static int find(int p,int[]array){
        int length=array.length;
        int []id=new int[length];
        while (p != id[p])
            p = id[p];
        return p;
    }
    public static void union(int tmp1, int tmp2,int[]array) {
        int r1 = find(tmp1, array);
        int r2 = find(tmp2, array);
        int temp = array[r1] + array[r2];
        if (array[r1] > array[r2]) {
            array[r1] = r2;
            array[r2] = temp;
        } else {
            array[r2] = r1;
            array[r1] = temp;
        }
        
    }
    public static int[] storearray(int[][]G){
        int length=G.length;
        int i,j;
        int count=0;
        for(i=0;i<length;i++){
            for(j=0;j<length;j++){
                if(G[i][j]>0){
                    count++;
                }
            }
        }
        int count2=0;
        int[] arrayforweight=new int[count];
        for(i=0;i<length;i++){
            for(j=0;j<length;j++){
                
                if(G[i][j]>0){
                    arrayforweight[count2]=G[i][j];
                    count2++;
                }
        }
            }
        return arrayforweight;
    }
    
	static int MWST(int[][] G){
        int totalWeight = 0;
        int num=G.length;
        int[] temparray=new int[4];
        int[]array=new int[num];
        for(int j=0;j<num;j++)
        {
             array[j]=-1;
        }
        int u,v,m,n=0;
        int tracker=0;
        int []weightarray=new int[num];
        weightarray=storearray(G);
        int length=weightarray.length;
        insertionsort(weightarray); //sort an arry that has weight of edge
        for(int i=0;i<length;i++){
            temparray=findvertices(weightarray[i],G);
            u=temparray[0];
            v=temparray[1];
            if(temparray[2]!=0&&temparray[3]!=0){
                m=temparray[2];
                n=temparray[3];
                if(find(m,array)!=find(n,array)){
                    totalWeight=totalWeight+weightarray[i];
                    union(m,n,array);
                    tracker++;
                }
            }
               if(find(u,array)!=find(v,array)){
                totalWeight=totalWeight+weightarray[i];
                union(u,v,array);
                tracker++;
            }
            if(tracker>=num-1)
                break;
        }
        
		
		return totalWeight;
		/* ... Your code here ... */
		
		
		
		/* Add the weight of each edge in the minimum weight spanning tree
		   to totalWeight, which will store the total weight of the tree.
		*/
		
		/* ... Your code here ... */
		
		
	}
    public static int[] insertionsort(int []array){
       
        int length=array.length;
        for(int i=1;i<length;i++){
            int key=array[i];
            int c=0;
            int k=i-1;
            while(c>=0){
                  if(array[k]<key)
                 {
                     c--;
                 }
                else{
                    array[k+1]=array[k];
                    k--;
                }
                array[k+1]=key;
            }
        }
    }
    public static int[] findvertices(int weight,int[][]graph){
        int count=0;
        int count2=0;
        int num=graph.length;
        int countappear=0;
        int temp[]=new int[4];
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                if(graph[i][j]==weight){
                countappear++;
                }
            }
        }
        for(int i=0;i<num;i++)
      {
          if(count==0)
         {
                for(int j=0;j<num;j++)
                {
                    if(graph[i][j]==weight)
                    {
                        temp[0]=i;
                        temp[1]=j;
                        count=1;
                    }
                }
          }
       }
        if(countappear>1){
           for(int i=0;i<num;i++)
        {
                    if(count2==0){
                    for(int j=0;j<num;j++){
        if(graph[i][j]==weight&&i!=temp[0]&&i!=temp[1]&&i!=temp[1]&&j!=temp[1]){
                            temp[2]=i;
                            temp[3]=j;
                            count2=1;
                        }
                    }
                }
            
        }
        }
        return temp;
        
    }
	/* main()
	   Contains code to test the MWST function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			
			int totalWeight = MWST(G);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			System.out.printf("Graph %d: Total weight is %d\n",graphNum,totalWeight);
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}