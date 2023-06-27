package greedy;

public class Job {

		//declare instance variables
        String jobName;
        int deadline;
        int profit;
        
        //constructor
        public Job(String jobName, int deadline, int profit) {
            this.jobName = jobName;
            this.deadline = deadline;
            this.profit = profit;
        }
        
        //display all the jobs
    	public String toString() {
    		return "\n" + "Job Name: " + this.jobName + "\n" + "Deadline: " + this.deadline + "\n" + "Profit: " + this.profit;
    	}
}
        
        