package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

	
	/**
	 * From rubric:
	 * Insertion Sort works by building up a list one at a time, and inserting it into the right point.
	 * Implemented using binary search.
	 * Reference:
	 * https://www.programering.com/a/MjNyEDMwATg.html
	 * @param input - the list to be sorted
	 * @return 
	 */
	public static ArrayList<Integer> binaryInsertionSort(List<Integer> input) {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sorted.add(input.get(0));
		
		for (int i = 1; i < input.size(); i++) {
			//To find the correct position to insert in the sorted list
			int low = 0;
			int high = i-1;
			int value = input.get(i);
			//The appropriate position is the index of the first element larger than (or equal to) the value we have
			//When the while loop ends, the low value will be the correct index
			while (low <= high) {
				//Find the middle value
				int mid = (low+high)/2;
				//If the value is larger than the middle value
				if (value > sorted.get(mid)) {
					//Search in the larger half
					low = mid+1;
				}else {
					//Search in the smaller half
					high = mid-1;
				}
			}
			//Insert the value at appropriate position in the sorted list
			sorted.add(low, value);
		}
		
		return sorted;
		
	}
	/**
	 * Insertion Sort using linear search
	 * @param input - the list to be sorted
	 * @return
	 */
	public static ArrayList<Integer> linearInsertionSort(List<Integer> input) {
		
		int n = input.size();
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sorted.add(input.get(0));
		
		//Outer loop: Each time, take first element of the unsorted part
		for (int i =1;i < n; i++) {
			int value = input.get(i);
			int index = sorted.size();
			//Inner loop: Find where to insert in the sorted list
			for (int j=0; j<sorted.size(); j++) {
				if (sorted.get(j) >= value) {
					index = j;
					break;
				}
			}
			sorted.add(index, value);
		}
		
		return sorted;
	}

}
