package edu.smith.cs.csc212.p7;

import java.util.List;

public class SelectionSort {
	
	/**
	 * From rubric:
	 * Selection Sort: finding the minimum value in the list and bringing it to the front
	 * @param input
	 */
	public static void selectionSort(List<Integer> input) {
		
		int minIndex;
		int n = input.size();
		
		//The outer loop: add selected value to the front
		//To n-1 because there is no need to compare the last unsorted value
		for (int i = 0; i < n-1; i++ ) {
			minIndex = i;
			
			//The inner loop: find minimum value each time
			//Start from i+1 because the values before that are already sorted
			for (int j = i+1; j< n; j++) {
				if (input.get(j) < input.get(minIndex)) {
					minIndex = j;
				}
			}
			
			//Put the selected value to the front, swap
			int temp = input.get(minIndex);
			input.set(minIndex, input.get(i));
			input.set(i, temp);
			
		}
		
	}
	
}
