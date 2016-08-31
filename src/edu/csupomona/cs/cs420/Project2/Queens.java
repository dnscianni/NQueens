/**
 * 
 */
package edu.csupomona.cs.cs420.Project2;

import java.util.PriorityQueue;

/**
 * @author David
 * 
 */
public class Queens implements Comparable<Queens>{

	private int[] queenLocations;
	private int heuristic;

	public Queens(int[] q) {
		queenLocations = q;
		heuristic = calcHeuristic();
	}

	private int calcHeuristic() {
		int result = 0;
		for (int i = 0; i < queenLocations.length; i++) {
			for (int j = 1; j + i < queenLocations.length; j++) {
				if (queenLocations[i] == queenLocations[i + j]) {
					result++;
				}
				if (queenLocations[i] + j == queenLocations[i + j]) {
					result++;
				}
				if (queenLocations[i] - j == queenLocations[i + j]) {
					result++;
				}
			}
		}
		return result;
	}

	public PriorityQueue<Queens> getNeighbors() {
		PriorityQueue<Queens> neighbors = new PriorityQueue<Queens>();
		for (int i = 0; i < queenLocations.length; i++) {
			for (int j = 0; j < queenLocations.length; j++) {
				if (queenLocations[i] != j) {
					int[] temp = queenLocations.clone();
					temp[i] = j;
					Queens n = new Queens(temp);
					neighbors.add(n);
				}
			}
		}
		return neighbors;
	}
	
	public PriorityQueue<Queens> getNeighbors(int n) {
		PriorityQueue<Queens> neighbors = new PriorityQueue<Queens>();
		for (int i = 0; i < queenLocations.length; i++) {
			
				if (queenLocations[n] != i) {
					int[] temp = queenLocations.clone();
					temp[n] = i;
					Queens neighbor = new Queens(temp);
					neighbors.add(neighbor);
				}
			
		}
		return neighbors;
	}
	
	public boolean hasConflict(int i) {
		for(int j = i; j < i+1; j++) {
			for(int k =1; k+j < queenLocations.length;k++) {
				if ((queenLocations[j] == queenLocations[j + k]) || (queenLocations[j] + k == queenLocations[j + k]) || (queenLocations[j] - k == queenLocations[j + k])) {
					return true;
				}
			}
		}
		
		for(int j = i; j > i-1; j--) {
			for(int k =1; j-k >= 0;k++) {
				if ((queenLocations[j] == queenLocations[j - k]) || (queenLocations[j] + k == queenLocations[j - k]) || (queenLocations[j] - k == queenLocations[j - k])) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String toString() {
		String result ="";
		for(int i =0; i < queenLocations.length; i++) {
			for(int j =0; j < queenLocations.length; j++) {
				if(i == queenLocations[j]) {
					result += "Q ";
				} else {
					result += "- ";
				}
			}
			result += "\n";
		}
		return result;
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	
	public int getSize() {
		return queenLocations.length;
	}

	@Override
	public int compareTo(Queens arg0) {
		
		if (heuristic < arg0.getHeuristic())
			return -1;
		else if (heuristic > arg0.getHeuristic())
			return 1;
		return 0;
	}
}
