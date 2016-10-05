/**
 * 
 */

/**
 * @author olwin
 *
 */


public class dataPoint {
	
	double area,perimeter,compactness,length_kernal,width,asym,length_groove;
	int group,lineNum,label_count;
	
	
	//for dataPoints
	public dataPoint(String area,String perimeter,String compactness,String length_kernal,String width,String asym,String length_groove,int counter,int centriods){
		
		//Data
		this.area=Double.parseDouble(area);
		this.perimeter=Double.parseDouble(perimeter);
		this.compactness=Double.parseDouble(compactness);
		this.length_kernal=Double.parseDouble(length_kernal);
		this.width=Double.parseDouble(width);
		this.asym=Double.parseDouble(asym);
		this.length_groove=Double.parseDouble(length_groove);
		
		//Cluster group id
		//this.group=rand.nextInt((centriods - 0)+1)+centriods;
		this.group=(int )(Math.random()*centriods+0);
		
		//for tracking purposes
		this.lineNum=counter;
		
		//for centriods points
		this.label_count=0;
	}
	
	//for centriods
	public dataPoint(int group, int lineNum) {
		this.area=0;
		this.perimeter=0;
		this.compactness=0;
		this.length_kernal=0;
		this.width=0;
		this.asym=0;
		this.length_groove=0;
		this.group=group;
		this.lineNum=lineNum;
		this.label_count=0;
	}

	/*
	 * Purpose: to display the data
	 */
	public void display(){
		System.out.print("Point "+lineNum+" contains data:");
		System.out.print(area+" ");
		System.out.print(perimeter+" ");
		System.out.print(compactness+" ");
		System.out.print(length_kernal+" ");
		System.out.print(width+" ");
		System.out.print(asym+" ");
		System.out.print(length_groove+" ");

		System.out.println(" ");
		System.out.print("group_id: "+group+",");
		System.out.println("Label_count: "+label_count+" ");
		System.out.println();
		
	}
	
	/*
	 * Purpose: to round up all the data inside a data point
	 */
	public void round(){
		this.area=roundDecimal(this.area);
		this.perimeter=roundDecimal(this.perimeter);
		this.compactness=roundDecimal(this.compactness);
		this.length_kernal=roundDecimal(this.length_kernal);
		this.width=roundDecimal(this.width);
		this.asym=roundDecimal(this.asym);
		this.length_groove=roundDecimal(this.length_groove);
	}
	
	/*
	 * Purpose:To round up a value decimal up to 4th location
	 */
	public static double roundDecimal(double value){
		double finalValue = Math.round( value * 1000.0 ) / 1000.0;
		return finalValue;
	}
	}

