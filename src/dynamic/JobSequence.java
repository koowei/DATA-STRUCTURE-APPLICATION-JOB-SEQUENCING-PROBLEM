package dynamic;
import java.util.*;
import java.io.*;

public class JobSequence {

	public static void main(String[] args) {
		
		//display the heading
		System.out.println("===================================================================");
		System.out.println("Job Sequencing using Weighted Problem with Dynamic Programming");
		System.out.println("===================================================================");
		
		//create an ArrayList object of Job class
		List<Job> job =new ArrayList<>();
		//create a file object
		File jobFile = new File("src/Dynamic.txt");
		
		//file exception handling
		try {
			//read the file
			Scanner readfile = new Scanner(jobFile);	
			
			while(readfile.hasNext()) {
				int startTime = readfile.nextInt();
				int endTime = readfile.nextInt();
				int profit = readfile.nextInt();
				//add the startTime, endTime, profit into ArrayList
				job.add(new Job(startTime,endTime,profit));
			}
			//close the file
			readfile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		
		//get the ArrayList size
		int n = job.size();
		
		//sort job in ascending order as per start time
		Collections.sort(job, new SortByStartTime());
		
		//display the heading
		System.out.println("The ascending order of job sequence: ");
		
		//use iterator to loop through ArrayList
		Iterator<Job> iterator = job.iterator(); 
		while(iterator.hasNext()) {
			System.out.print(iterator.next());
			System.out.println();
		}
		
		int choice;
		Scanner input = new Scanner(System.in);
		
		do {
			choice = jobMenu();
			
			switch(choice) {			
			case 1: //enter new job details and display the updated job sequence
				
				int startTime = 0;
				int endTime = 0;
				
				do {
					do {
						//prompt user to enter start time
						System.out.print("\nEnter start time for the job: ");
						startTime = input.nextInt(); 
						if (startTime < 0) {
							System.out.println("Start Time must be greater than or equal to zero. Try Again.\n");
						}
					} while(startTime < 0);
				
					do {
						//prompt user to enter end time
						System.out.print("Enter end time for the job: ");
						endTime = input.nextInt(); 
						 if (endTime <= 0){
								System.out.println("End Time must be greater than zero. Try Again.\n");
							}
					} while(endTime <= 0);
					
					if (endTime < startTime) {
						System.out.println("End Time must be greater than Start Time. Try Again.\n");
					} 
				} while(endTime < startTime);
				
				//prompt user to enter profit
			    int profit = 0;
			    do {
			    	System.out.print("Enter profit for the job: ");
			    	profit = input.nextInt();  
			    	if (profit <= 0) {
			    		System.out.println("Profit must be greater than zero. Try again.\n");
			    	}
			    } while(profit <= 0);
			    
			    //add the startTime, endTime, profit into ArrayList
			    job.add(new Job(startTime,endTime,profit)); 
			       
			    //sort job in ascending order as per start time
			    Collections.sort(job, new SortByStartTime());
			       
			     //display the heading
			     System.out.println("\n===================================================================");
			     System.out.println("The updated job sequence: ");
			       
			     //use iterator to update the job sequence
			     Iterator<Job> iteratorUpdate = job.iterator();
			     while(iteratorUpdate.hasNext()) {
					System.out.print(iteratorUpdate.next());
					System.out.println();
				}
					
			       break;
			       
			case 2: //perform job sequencing
				//display the job sequence and calculate the total maximum profit
				calculateMaxProfit(job,n); 
				break;
			
			case 3: //exit program
				System.out.println("Exiting program..."); //exit program
				break;
				
			default:
				System.out.println("Please input 1 OR 2 OR 3. Try again.");
			}
		} while(choice != 3);
	}
		
	//display the menu
	public static int jobMenu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		
		//choice input error handling
		try {
			System.out.println("\n===================================================================");
			System.out.println("\nPlease input 1/2/3 to perform the following operation: ");
			System.out.println("1. Enter new job details"); //choice == 1
			System.out.println("2. Proceed to job sequencing"); //choice == 2
			System.out.println("3. Exit program"); //choice == 3
			System.out.print("==>");
			choice = input.nextInt();
			}
		catch(InputMismatchException e) { //choice != 1 || choice != 2 || choice != 3
			System.out.println("Please input 1 OR 2 OR 3. Try again.");
			System.out.println();
		}
		return choice;
	}
	
	//display the job sequence and calculate maximum profit
	public static void calculateMaxProfit(List<Job> job, int n) {
			
			//sort job in ascending order as per start time
		Collections.sort(job, new SortByStartTime());
			
		List<List<Integer>> JobSequence = new ArrayList<>(); //create an ArrayList with a list of integer as generic type
		
		for (int i = 0; i < n; i++) {
			JobSequence.add(new ArrayList<>());
		    }//JobSequence stores index of non-conflicting job that ends with i job
		
		     int[] profitList = new int[n];//store the maximum profit in specific index
		 
		     for (int i = 0; i < n; i++)
		      {//loop through jobs
		    	 for (int j = 0; j < n; j++)
		            {//nested loop through the jobs
		                if (job.get(j).endTime <= job.get(i).startTime && profitList[i] < profitList[j])
		                {//update the i job into jobSequence if no conflicted with j job to get max profit
		                	JobSequence.set(i, new ArrayList<>(JobSequence.get(j)));
		                    profitList[i] = profitList[j];
		                }
		            }
		            //end with i job
		            JobSequence.get(i).add(i);
		            profitList[i] += job.get(i).profit;
		      }
		 
		        int index = 0;
		        for (int i = 1; i < n; i++)
		        {//loop through the profit list except 0
		            if (profitList[i] > profitList[index]) { //Compare and find the index that contains the highest profit
		                index = i;
		            }
		        }
		 
		        System.out.println("\n" + "Maximum profit in the job sequence: ");
		        
		        for (Integer i: JobSequence.get(index)) { //display job sequence that contains highest profit
		            System.out.print(job.get(i));
		            System.out.println();
		        }
		        System.out.println("\nTotal profit: " + profitList[index]); //display maximum profit earned
		        System.out.println();
		}
}


