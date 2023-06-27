package greedy;
import java.util.*;
import java.io.*;

public class JobSequence {
	
	public static void main(String[] args) {
		
		//display the heading
		System.out.println("===================================================================");
		System.out.println("		Job Sequencing using Greedy Algorithm");
		System.out.println("===================================================================");
		
		//create an ArrayList object of Job class
		List<Job> job =new ArrayList<>();
		//create a file object
		File jobFile = new File("src/Greedy.txt");

		//file exception handling
		try {
			//read the file
			Scanner readfile = new Scanner(jobFile);	
			
			while(readfile.hasNext()) {
				String jobName = readfile.next();
				int deadline = readfile.nextInt();
				int profit = readfile.nextInt();
				//add the jobName, deadline, profit into ArrayList
				job.add(new Job(jobName,deadline,profit));
			}
			//close the file
			readfile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}

		//get the ArrayList size
		int n = job.size();
		
		//sort job in descending order as per profit
		Collections.sort(job, new SortByProfit());
		
		//display the heading
		System.out.println("The descending order of job sequence: ");
		
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

				//prompt user to enter job name
				System.out.print("\nEnter name for the job: ");
			    String jobName = input.next(); 
			    input.nextLine();
			    
			    //prompt user to enter deadline
			    int deadline = 0;
			    do {
			    	System.out.print("Enter deadline for the job: ");
			    	deadline = input.nextInt();  
			    	if (deadline <= 0) {
			    		System.out.println("Deadline must be greater than zero. Try again.\n");
			    	}
			    } while(deadline <= 0);
			    
			    //prompt user to enter profit
			    //
			    int profit = 0;
			    do {
			    	System.out.print("Enter profit for the job: ");
			    	profit = input.nextInt();  
			    	if (profit <= 0) {
			    		System.out.println("Profit must be greater than zero. Try again.\n");
			    	}
			    } while(profit <= 0);
			    
			    //add the jobName, deadline, profit into ArrayList
			    job.add(new Job(jobName,deadline,profit)); 
			       
			    //sort job in descending order as per profit
			    Collections.sort(job, new SortByProfit());
			       
			     //display the heading
			     System.out.println("\n===================================================================");
			     System.out.println("The Updated Job Sequence: ");
			       
			     //use iterator to update the job sequence
			     Iterator<Job> iteratorUpdate = job.iterator();
			     while(iteratorUpdate.hasNext()) {
			    	 System.out.print(iteratorUpdate.next());
			    	 System.out.println();
				}
			    break;
			    
			case 2:  //perform job sequencing
				//display the job sequence and calculate the total maximum profit
				calculateMaxProfit(job,n);
				break;
			
			case 3: //exit program
				System.out.println("Exiting program...");
				break;
				
			default:
				System.out.println("Please input 1 OR 2 OR 3. Try again.");
			}
		} while(choice != 3);
	}

	//display the menu
	public static int jobMenu() {
		Scanner input = new Scanner(System.in);
		
		//initialize choice with 0
		int choice = 0;
		
		//choice input error handling
		try {
			System.out.println("\n===================================================================");
			System.out.println("Please input 1/2/3 to perform the following operation: ");
			System.out.println("1. Enter new job details"); //choice == 1
			System.out.println("2. Proceed to job sequencing");  //choice == 2
			System.out.println("3. Exit program");  //choice == 3
			System.out.print("==>");
			choice = input.nextInt();
			}
		catch(InputMismatchException e) { //choice != 1 || choice != 2 || choice != 3
			System.out.println("Please input 1 OR 2 OR 3. Try again.");
			System.out.println();
		}
		return choice;
	}
	
	//display the job sequence and maximum profit
	//calculate maximum profit
	public static void calculateMaxProfit(List<Job> job, int n) {
		
		//sort job in descending order as per profit
		Collections.sort(job, new SortByProfit());
        
		//get the ArrayList size
		n = job.size();
		
		// to oversee the free time slots
        boolean[] slots = new boolean[n];
        
        // to store the result of job sequence
        int sequence[] = new int[n];

        //loop through the jobs
        for (int i = 0; i < n; i++) {
        	// find a FREE slot for this job (starting with the last possible slot)
            for (int j = job.get(i).deadline - 1; j >= 0; j--) {
            	//FREE slot found, return to the outer loop
                if (!slots[j]) {
                    sequence[j] = i;
                    slots[j] = true;
                    break;
                }
            }
        }
        
        System.out.print("\nJobs: ");
        
        //initialize total profit with 0
        int totalProfit=0;
        
        //display the job sequence and calculate the total maximum profit
        for (int i = 0; i < job.size(); i++) {
            if (slots[i]){
                System.out.print(job.get(sequence[i]).jobName + " ");
                totalProfit += job.get(sequence[i]).profit;
            }
        }
        
        //display the total maximum profit
        System.out.println("\nTotal Profit: " + totalProfit);
    }
		
}
	
