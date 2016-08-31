/**
 * 
 */
package edu.csupomona.cs.cs420.Project2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author David
 * 
 */
public class Project2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] temp = generator();
		Queens t = hillClimbing(temp);
		System.out.println(t.toString() + "\n\n" + t.getHeuristic());
		t = minConflicts(temp, 1000);
		if (t != null) {
			System.out.println("\n\n" + t.toString() + "\n\n" + t.getHeuristic());
		} else {
			System.out.println("error");
		}

	}

	public static Queens hillClimbing(int[] initial) {
		Queens current = new Queens(initial);
		Queens neighbor;

		while (true) {
			neighbor = current.getNeighbors().poll();
			if (neighbor.getHeuristic() >= current.getHeuristic()) {
				return current;
			}
			current = neighbor;
		}
	}

	public static Queens minConflicts(int[] initial, int max) {
		Queens current = new Queens(initial);
		Queens value;
		Random rand = new Random();
		int r, var;

		for (int i = 0; i < max; i++) {
			if (current.getHeuristic() == 0) {
				return current;
			}
			ArrayList<Integer> list = getConflicts(current);
			r = rand.nextInt(list.size());
			var = list.get(r).intValue();
			value = current.getNeighbors(var).poll();
			current = value;
		}
		return null;
	}

	private static ArrayList<Integer> getConflicts(Queens current) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < current.getSize(); i++) {
			if (current.hasConflict(i)) {
				list.add(i);
			}
		}
		return list;
	}

	public static int[] generator() {
		int[] result = new int[15];
		Random rand = new Random();
		int r;
		for (int i = 0; i < result.length; i++) {
			r = rand.nextInt(15);
			result[i] = r;
		}
		return result;
	}

}
