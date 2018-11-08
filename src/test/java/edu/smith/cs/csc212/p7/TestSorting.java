package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * 
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i = 0; i < items.size() - 1; i++) {
			if (items.get(i) > items.get(i + 1)) {
				System.err.println("items out of order: " + items.get(i) + ", " + items.get(i + 1) + " at index=" + i);
				return false;
			}
		}
		return true;
	}

	/**
	 * Here's some data!
	 */
	private static int[] data = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	private static int[] test1 = { 1,2 };
	private static int[] test2 = { 2,4 };

	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sortMe);
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void testSelectionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sortMe);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void testInsertionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		sortMe = InsertionSort.linearInsertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sortMe);
		sortMe = InsertionSort.linearInsertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void testBinaryInsertionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		sortMe = InsertionSort.binaryInsertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sortMe);
		sortMe = InsertionSort.binaryInsertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testMerge() {
		
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		for (int x: test1) {
			A.add(x);
		}
		
		for (int y: test2) {
			B.add(y);
		}
		
		result = MergeSort.merge(A, B);
		System.out.println(result);
		
	}
	
	@Test
	public void testRecursiveMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		ArrayList<Integer> sorted = MergeSort.recursiveMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sorted));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sorted);
		sorted = MergeSort.recursiveMergeSort(sorted);
		Assert.assertTrue(checkSorted(sorted));
	}
	
	@Test
	public void testIterativeMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		ArrayList<Integer> sorted = MergeSort.iterativeMergeSort(sortMe);
		System.out.println(sorted);
		Assert.assertTrue(checkSorted(sorted));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		Collections.shuffle(sortMe);
		sorted = MergeSort.iterativeMergeSort(sortMe);
		System.out.println(sorted);
		Assert.assertTrue(checkSorted(sorted));
	}
	
	@Test
	public void testDLLMerge() {
		
		DoublyLinkedList<Integer> A = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> B = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> result = new DoublyLinkedList<>();
		
		for (int x: test1) {
			A.addBack(x);
		}
		
		for (int y: test2) {
			B.addBack(y);
		}
		
		result = MergeSort.dllMerge(A, B);
		List<Integer> copy = result.copyToList();
		System.out.println(copy);
		
	}
	
	@Test
	public void testDLLMergeSort() {
		// See if the data can be reversed:
		DoublyLinkedList<Integer> sortMe = new DoublyLinkedList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		sortMe = MergeSort.dllMergeSort(sortMe);
		List<Integer> sorted = sortMe.copyToList();
		Assert.assertTrue(checkSorted(sorted));

		// For good measure, let's shuffle it and sort it again to see if that works,
		// too.
		sortMe = new DoublyLinkedList<>();
		Collections.shuffle(sorted);
		for (int y : sorted) {
			sortMe.addBack(y);
		}
		sortMe = MergeSort.dllMergeSort(sortMe);
		sorted = sortMe.copyToList();
		Assert.assertTrue(checkSorted(sorted));
	}

}
