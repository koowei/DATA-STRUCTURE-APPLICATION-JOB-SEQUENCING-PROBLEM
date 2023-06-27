package dynamic;

public class Job {
	
	//declare instance variables
	int startTime, endTime, profit;
	
	//constructor
	public Job(int startTime, int endTime, int profit) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}
	
	//display all the jobs
	public String toString() {
		return "\n" + "Start Time: " + this.startTime + "\n" + "End Time: " + this.endTime + "\n" + "Profit: " + this.profit;
	}
}
