package greedy;
import java.util.Comparator;

//sort job in descending order as per profit
public class SortByProfit implements Comparator<Job>{
	public int compare(Job a, Job b) {
		return b.profit - a.profit;
	}
}


