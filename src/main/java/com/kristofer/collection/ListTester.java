package com.kristofer.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic test harness to measure iteration speed of Java List implementations.
 * Tests three iteration methods: for-loop, while-loop, and for-each loop.
 *
 * @param <T> A concrete List implementation (e.g., ArrayList, LinkedList)
 */
public class ListTester<T extends List<Integer>> {
    private T list;
    private static final int LIST_SIZE = 1_000_000;
    
    /**
     * Constructor that accepts any concrete List implementation.
     *
     * @param list An instance of a List implementation to test
     */
    public ListTester(T list) {
        this.list = list;
    }
    
    /**
     * Populates the list with 1 million Integer items.
     */
    public void populateList() {
        list.clear();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(i);
        }
    }
    
    /**
     * Times iteration using a traditional for-loop with index.
     *
     * @return Time taken in milliseconds
     */
    public long timeForLoop() {
        long startTime = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
    
    /**
     * Times iteration using a while-loop.
     *
     * @return Time taken in milliseconds
     */
    public long timeWhileLoop() {
        long startTime = System.nanoTime();
        int sum = 0;
        int i = 0;
        while (i < list.size()) {
            sum += list.get(i);
            i++;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
    
    /**
     * Times iteration using a for-each loop.
     *
     * @return Time taken in milliseconds
     */
    public long timeForEachLoop() {
        long startTime = System.nanoTime();
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
    
    /**
     * Runs all tests and reports the results.
     */
    public void runAllTests() {
        System.out.println("Testing " + list.getClass().getSimpleName() + " with " + LIST_SIZE + " items:");
        System.out.println("--------------------------------------------------------");
        
        long forLoopTime = timeForLoop();
        System.out.println("For-loop iteration time:      " + forLoopTime + " ms");
        
        long whileLoopTime = timeWhileLoop();
        System.out.println("While-loop iteration time:    " + whileLoopTime + " ms");
        
        long forEachLoopTime = timeForEachLoop();
        System.out.println("For-each loop iteration time: " + forEachLoopTime + " ms");
        
        System.out.println("--------------------------------------------------------\n");
    }
    
    /**
     * Main method demonstrating usage with ArrayList and LinkedList.
     */
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("List Iteration Performance Test");
        System.out.println("=======================================================\n");
        
        // Test ArrayList
        ListTester<ArrayList<Integer>> arrayListTester = new ListTester<>(new ArrayList<>());
        arrayListTester.populateList();
        arrayListTester.runAllTests();
        
        // Test LinkedList
        ListTester<LinkedList<Integer>> linkedListTester = new ListTester<>(new LinkedList<>());
        linkedListTester.populateList();
        linkedListTester.runAllTests();
        
        System.out.println("=======================================================");
        System.out.println("Test completed!");
        System.out.println("=======================================================");
    }
}
