package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	/**
	 * Merge two sorted lists into one sorted list
	 * 
	 * Reference: "Top-down implementation using lists":
	 * https://en.wikipedia.org/wiki/Merge_sort
	 * 
	 * @param sortedLeft - a sorted list
	 * @param sortedRight- a sorted list
	 * @return a merged sorted list consisting of two sorted lists
	 */
	public static ArrayList<Integer> merge(List<Integer> sortedLeft, List<Integer> sortedRight) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		// Now I assume that the lists have been sorted
		// Keep track of the index in each sorted list
		int left = 0;
		int right = 0;

		// End if one of the list is empty (the index goes beyond the size of the list)
		while ((left < sortedLeft.size()) && (right < sortedRight.size())) {
			// Compare the "first element" in two sorted lists, and add the smaller one to the result list
			if (sortedLeft.get(left) < sortedRight.get(right)) {
				result.add(sortedLeft.get(left));
				left++;
			} else {
				result.add(sortedRight.get(right));
				right++;
			}
		}
		// If one sorted list is empty, add the remaining elements in the other list to the result
		if (left >= sortedLeft.size()) {
			result.addAll(sortedRight.subList(right, sortedRight.size()));
		} else if (right >= sortedRight.size()) {
			result.addAll(sortedLeft.subList(left, sortedLeft.size()));
		}

		return result;
	}

	/**
	 * Recursive Merge Sort
	 * 
	 * @param input - the list that we want to sort
	 * @return a sorted list
	 */
	public static ArrayList<Integer> recursiveMergeSort(List<Integer> input) {

		ArrayList<Integer> unsorted = new ArrayList<>(input);

		if (unsorted.size() < 2) {
			return unsorted;
		}

		int mid = unsorted.size() / 2;

		List<Integer> leftSorted = recursiveMergeSort(input.subList(0, mid));
		List<Integer> rightSorted = recursiveMergeSort(input.subList(mid, unsorted.size()));
		return merge(leftSorted, rightSorted);

	}

	/**
	 * Iterative Merge Sort 
	 * Thank you JJ for your help in the lab :)
	 * 
	 * @param input - the list that you want to be sorted
	 * @return the sorted list
	 */
	public static ArrayList<Integer> iterativeMergeSort(List<Integer> input) {
		//First, split the unsorted list into the smallest units
		List<ArrayList<Integer>> unsorted = new ArrayList<>();
		for (int i: input) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(i);
			unsorted.add(temp);
		}
		//Then, merge the first two elements in the new unsorted list
		//Until there is only one element left
		while (unsorted.size() >= 2) {
			ArrayList<Integer> sorted = merge(unsorted.get(0),unsorted.get(1));
			unsorted.remove(0);
			unsorted.remove(0);
			unsorted.add(sorted);
		}
		return unsorted.get(0);
	}
	
	/**
	 * Merge method for doubly linked list merge sort
	 * Exactly the same idea as what I've implemented for ArrayList
	 * @param sortedLeft - a sorted list
	 * @param sortedRight - a sorted list
	 * @return a merged sorted list
	 */
	public static DoublyLinkedList<Integer> dllMerge(DoublyLinkedList<Integer> sortedLeft, DoublyLinkedList<Integer> sortedRight) {
		
		DoublyLinkedList<Integer> result = new DoublyLinkedList<Integer>();
		
		while (!(sortedLeft.isEmpty()) && !(sortedRight.isEmpty())) {
			if (sortedLeft.getFront()<sortedRight.getFront()) {
				result.addBack(sortedLeft.getFront());
				sortedLeft.removeFront();
			}else {
				result.addBack(sortedRight.getFront());
				sortedRight.removeFront();
			}
		}
		
		if (sortedLeft.isEmpty()) {
			for (int i: sortedRight) {
				result.addBack(i);
			}
		}else if (sortedRight.isEmpty()) {
			for (int i: sortedLeft) {
				result.addBack(i);
			}
		}
		
		return result;
	}
	
	/**
	 * Recursive Merge Sort for DoublyLinkedList
	 * @param input
	 * @return
	 */
	public static DoublyLinkedList<Integer> dllMergeSort(DoublyLinkedList<Integer> input) {
		
		//Start merging, when we have the smallest units (of 1 element)
		if (input.size() < 2) {
			return input;
		}
		
		//Split the input into two halves
		int mid = input.size() / 2;
		DoublyLinkedList<Integer> left = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> right = new DoublyLinkedList<>();
		int count = 0;
		for (int i: input) {
			if (count < mid) {
				left.addBack(i);
			}else {
				right.addBack(i);
			}
			count++;
		}
		
		//Sort each half
		DoublyLinkedList<Integer> leftSorted = dllMergeSort(left);
		DoublyLinkedList<Integer> rightSorted = dllMergeSort(right);
		
		//Merge the two halves
		return dllMerge(leftSorted, rightSorted);
	}

}
