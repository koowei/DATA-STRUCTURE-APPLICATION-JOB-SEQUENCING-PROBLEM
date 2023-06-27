package dynamic;
import java.util.Comparator;

//sort job in ascending order as per start time
public class SortByStartTime implements Comparator<Job>{
	public int compare(Job a, Job b) {
		return a.startTime - b.startTime;
	}
}
