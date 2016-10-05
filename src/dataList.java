import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author olwin
 *
 */
public class dataList {
	
	//Two linkedlist to keep track of data and centriods
	static LinkedList<dataPoint> DList = new LinkedList<dataPoint>();//Data
	static LinkedList<dataPoint> CList=new LinkedList<dataPoint>();//Centriods
	static int numOfCentriods=0,iteration=0;
	
	//To keep track of convergance of each iteration
	static boolean final_convergance=true;
	static boolean convergance=true;
	static boolean initial_convergance=true;
	
	public static void main(String[] args) throws InterruptedException{
		setnumOfCentriods(args[0]);
		initialized_Centriods();
		readData("seeds");
		clustering();
		report();
	}
	
	
	/*Purpose: reading in the data set
	 * Code: Using StringTokenizer split by tokens, store data in linkedlist
	 * Note: points are label 1 to n, however linkedlist it will be iterating from 0
	 */
	
	public static void readData(String file){
		try{
			System.out.println("Reading the data file\n");
			FileInputStream fstream = new FileInputStream(file);
	          DataInputStream in = new DataInputStream(fstream);
	          BufferedReader br = new BufferedReader(new InputStreamReader(in));
	          String line;
	          int lineCounter=0;
	          while ((line =br.readLine()) != null)   {
	        	  
	        	  StringTokenizer tokens=new StringTokenizer(line);
	        	  dataPoint point=new dataPoint(tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),lineCounter,numOfCentriods); 
	        	  lineCounter++;
	        	  point.display();
	        	  DList.add(point);
	        	  System.out.println("Linkedlist'size is:"+DList.size()+"\n");
		}
	          System.out.println("Finished reading the data file\n");
	          in.close();
		}catch (Exception e){
			System.err.println("Error:"+e.getMessage());
		}
		
	}
	
	/*Purpose: read in a user input for number of Centriods
	 * Code: use scanner
	 */
	public static void setnumOfCentriods(String value){
		int value_int = 0;
		numOfCentriods=Integer.parseInt(value);
		/*
		Scanner reader=new Scanner(System.in);
		System.out.print("Number of Centriods: ");
		numOfCentriods=reader.nextInt();
		reader.close();
		*/
	}
	
	
	/*Purpose: choosing random data centriods
	 *
	 */
	/*public static void	randDataPoint(){
		System.out.println("Selecting random data points\n");
		
		//helper values
		int centriod_label,Iter_counter=0;

		dataPoint centriod_Point;
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		//generating unique random numbers
		for(int count=1; count<DList.size();count++ ){
			list.add(new Integer(count));//if there is a change
		}
		
		Collections.shuffle(list);
		
		for(int count=0;count<numOfCentriods;count++){
			centriod_label=list.get(count);
			
			System.out.println(centriod_label);
		
		}
		
		//finished generating unique random number
		System.out.println("finished selecting random data points\n");
		
	}
	*/
	
	/* Purpose: To iterate through the specified linkedlist for the given index dataPoint
	 * 
	 */
	public static dataPoint ListIter(LinkedList list,int index){
		dataPoint return_dataPoint = null;
		int count=0;
		ListIterator<dataPoint> Iter = list.listIterator( );
		while(count!=index && count<list.size()){
				  Iter.next();
				  count++;
		}
		return_dataPoint=Iter.next();
		return return_dataPoint;
	}
	
	/* Purpose: Summing the same-label dataPoints and dividing it by its count to get the average centriod
	 * Code: Two parts: looking at the datalist and summing up the same labels and dividing the total sum of each dimension by its total label count to get the average
	 * This will be our new centriod
	 * Note: need to run 1 time first to get the first random centriods
	 */
	public static void updateCentriods(){
		System.out.println("Updating centriods begin\n");
		labelCount_reset();
		for(	
				  ListIterator<dataPoint> Iter = DList.listIterator( );
				  Iter.hasNext();
				)
				{
				 dataPoint item=Iter.next();
			     int group_id=item.group;
			     dataPoint centriod=ListIter(CList,group_id);
			     addToCentriod(centriod,item);

			     }
		//first part
		
		for(int count=0;count<CList.size();count++){
		DivdebySum(ListIter(CList,count));
		}

		List(CList,true);
		//second part
		System.out.println("Updating centriods end\n");
		
		//now we have new centriods we have updated
		}
	
	/*
	 * Purpose: to help reset the labelcount of each centriods for next iterations of assignments
	 */
	public static void labelCount_reset(){
	
		for(	
			  ListIterator<dataPoint> Iter = CList.listIterator( );
			  Iter.hasNext();
			)
			{
			 dataPoint item=Iter.next();
			 item.label_count=0;
		     }	
	}
	
	/*Purpose : in order to sum up same group labels to centriods
	 * 
	 */
	public static void addToCentriod(dataPoint centriod,dataPoint point){
		
		
		centriod.area+=point.area;
		centriod.perimeter+=point.perimeter;
		centriod.compactness+=point.compactness;
		centriod.length_kernal+=point.length_kernal;
		centriod.width+=point.width;
		centriod.asym+=point.asym;
		centriod.length_groove+=point.length_groove;
		centriod.label_count++;

		
		centriod.round();
	}
	
	
	
	/*
	 * Purpose: Divding by the label count to get the average of the centriod
	 */
	public static void DivdebySum(dataPoint centriod){
		

		if(centriod.label_count!=0){
		centriod.area=(centriod.area/centriod.label_count);
		centriod.perimeter=(centriod.perimeter/centriod.label_count);
		centriod.compactness=(centriod.compactness/centriod.label_count);
		centriod.length_kernal=(centriod.length_kernal/centriod.label_count);
		centriod.width=(centriod.width/centriod.label_count);
		centriod.asym=(centriod.asym/centriod.label_count);
		centriod.length_groove=(centriod.length_groove/centriod.label_count);

		centriod.round();
		}
		
		centriod.display();
	}
	
	/*Purpose: initilazing centriods dataPoints in the Centriods Linkedlist (CList)
	 * 
	 */
	public static void initialized_Centriods(){
		System.out.println("initalizing "+numOfCentriods+" centriods\n");
		for(int count=0;count<numOfCentriods;count++){
			dataPoint new_centriod=new dataPoint(count,count);
			CList.add(new_centriod);
		}
	}
	
	/*Purpose: List out the elements in the list to analyze
	 * 
	 */
	public static void List(LinkedList list,boolean isCentriod){
		if(isCentriod) System.out.println("The following are centriods\n");
		for(int count=0;count<list.size();count++){
			dataPoint point=ListIter(list,count);
			
			point.display();
		}
		System.out.println("Finished iterating through the list\n");
	}
	
	/*To calculate the distance between two data Points
	 * 
	 */
	public static double euclidDistanceCalculator(dataPoint a,dataPoint b){
		

		
		//helper values
		double before_squared,after_squared,answer=0;
		
		before_squared=a.area-b.area;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.perimeter-b.perimeter;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.compactness-b.compactness;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.length_kernal-b.length_kernal;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.width-b.width;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.asym-b.asym;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		before_squared=a.length_groove-b.length_groove;
		after_squared=Math.pow(before_squared, 2);
		answer+=after_squared;
		
		answer=Math.sqrt(answer);
		return answer;
	}

	/*Purpose: reassigning the group label of a single dataPoint
	 * 
	 */
	public static void nearestCentriod(dataPoint a){
		double[] distances=new double[numOfCentriods];
		for(int count=0;count<distances.length;count++){
			distances[count]=euclidDistanceCalculator(ListIter(CList,count), a);
		}
		
		int closest_centriod_index=min_value(distances);
		if(a.group==closest_centriod_index) {
			
			System.out.println("Assigned to group id:"+a.group);
			System.out.println("No reassignment");
		}
		else {
			final_convergance=true;
			System.out.println("Assigned to group id:"+a.group);
			a.group=closest_centriod_index;
			System.out.println("Reassigned to group id:"+a.group);
			}
	}
		
	/*Purpose: getting the minmum distance among centriods
	 *
	 */
	public static int min_value(double[] array){
		double smallest_value=array[0];
		int smallest_index=0;
		for(int count=1;count<array.length;count++){
			if(array[count]<smallest_value) {
				smallest_value=array[count];
				smallest_index=count;
			}
		}
		return smallest_index;
	}
	
	/*
	 * Purpose: to reassign group label to every data Point
	 */
	public static void reAssignLabels() throws InterruptedException{
		initial_convergance=convergance;
		final_convergance=false;
		
		System.out.println("initial convergance is:"+convergance+"\n");
		
		
		System.out.println("Iteration "+iteration+" Begin\n");
		for(int count=0;count<DList.size();count++){
			System.out.println("Point "+count+" Reassiging in process");
			dataPoint point=ListIter(DList,count);
			nearestCentriod(point);
			System.out.println("Point "+count+" Reassigning complete\n");
			
			System.out.println("initial convergance is:"+convergance);
			System.out.println("convergance after running through the points is:"+final_convergance+"\n");
		}
		
		if(initial_convergance==true && final_convergance==false) {
			convergance=false;
			System.out.println("initial convergance is true and final is false and thus convergance:"+convergance+"\n");
			}
		System.out.println("Convergance at the end: "+convergance+"\n");
		System.out.println("Iteration "+iteration+" End \n");
		System.out.println("Printing results begin:\n");
		List(CList,true);
		System.out.println("Printing results end:\n");

		iteration++;
	}
	
	/*
	 * Purpose: general method to run kmean clustering
	 */
	public static void clustering() throws InterruptedException{
		
		System.out.println("Clustering Begin\n");
		while(convergance==true){ 
			updateCentriods();
			reAssignLabels();
		}
		System.out.println("Clustering End\n");
	}
	

	
	/*
	 * method to report the stats of the clustering
	 */
	public static void report(){
		System.out.println("Reporting Begin\n");
		System.out.println("Counting from 1, Iteration count:"+iteration+"\n");
		List(CList,true);
		System.out.println("Reporting End\n");
	}
	
	/*
	 * Purpose: to calculate EV
	 */
	public static double EV(){
		for each centriods C
			for each C.dataPoint 
				calculate the euclidean distance
				answer+=distance
		return distance
	}
	
	/*
	 * Purpose: to calculate IV
	 */
	public static double IV(dataPoint centriod_A,dataPoint centriod_B){
		indexA=getCentriodALabel
		indexB=getCentriodBLabel
		
		make array A(indexA)
		make array B(indexB)
		
		for each element in the data list{
			if dataPoint.label==indexA
				put datapoint in arrayA
			else if dataPoint.label=indexB
				put datapoint in array B
		}		
		
		double value;
		try{
		for(dataPoint a: arrayA){
			for(dataPoint b: array B ){
				if(b.index!=a.index){
				value+=distance(a,b) 
				}
				else throw exception e
			}
		}
		value=(value/(indexA.label_count+indexB.label_count));
		}
		catch (exception e){ 
			do something with exception
		}
		
	}
}






