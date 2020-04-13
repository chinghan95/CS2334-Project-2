/**
 * This class represents a one-dimensional cellular automaton with 
 * cells that can be in two states. The rule that governs the evolution
 * of the cells can be any concrete subclass of Rule, and the boundary
 * conditions can be any class that implements BoundaryCondidionts.
 * 
 * @author User
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Automaton {
	
	private Rule rule;
	private List<Generation> generations = new ArrayList<Generation>();
	private BoundaryConditions bc;
	
	public Automaton(Rule rule, Generation init, BoundaryConditions bc) {
		try {
			this.rule = rule;
		}
		catch(NullPointerException e) {
			System.out.println("Automaton constructor, rule");
		}
		try {
			this.generations.add(init);
		}
		catch(NullPointerException e) {
			System.out.println("Automaton constructor, generations");
		}
		try {
			this.bc = bc;
		}
		catch(NullPointerException e) {
			System.out.println("Automaton constructor, bc");
		}
		
		
	}
	
	public Rule getRule() {
		return this.rule;
	}

	/**
	 * Return the Generation produced by the given evolution step. 
	 * If the Automaton has not evolved this far, first call the evolve method 
	 * with the necessary number of steps.
	 * @param stepNum
	 * @return
	 * @throws InvalidStepNumException 
	 */
	public Generation getGeneration(int stepNum) throws InvalidStepNumException {
		if(stepNum < 0) {
			throw new InvalidStepNumException();
		}
		else {
			try {
				if(generations.size() > stepNum)
				return this.generations.get(stepNum);
				else {
					evolve(stepNum - (generations.size() - 1));
					return this.generations.get(stepNum);
				}
			}
			catch(NullPointerException e) {
				System.out.println("Automaton getGeneration");
			}
		}
		return null;
	}
	
	/**
	 * Return boundary conditions
	 * @return
	 */
	public BoundaryConditions getBoundaryConditions() {
		try {
			return this.bc;
		}
		catch(NullPointerException e) {
			System.out.println("Automaton getBoundaryConditions");
		}
		return null;
	}
	
	/**
	 * Evolve the Automaton a given number of steps, appending each successive Generation 
	 * to the generations List. If the number of steps is less than or equal to 0, 
	 * leave the Automaton unchanged.
	 * @param numSteps
	 */
	public void evolve(int numSteps) {
		
		if (numSteps <= 0)
			System.out.println("The number of steps is less than or equal to 0");
		else {
			int length = generations.size();
			for(int i = (length - 1); i < (length - 1 + numSteps); ++i) {
				Generation newGeneration = this.rule.evolve(generations.get(i), this.bc);
				this.generations.add(newGeneration);
			}
		}
	}
	
	/**
	 * Return the total number of steps that the Automaton has evolved.
	 * @return
	 */
	public int getTotalSteps() {
		try {
			return this.generations.size() - 1;
		}
		catch(NullPointerException e) {
			System.out.println("Automaton getTotalSteps");
		}
		return 0;
	}
	
	/**
	 * Return a String representation of the current generation.
	 */
	public String toString() {
		try {
			Generation currGeneration = generations.get(getTotalSteps());
			return currGeneration.toString();
		}
		catch(NullPointerException e) {
			System.out.println("Automaton toString");
		}
		return "";
	}
	
	/**
	 * Return a String that represents the entire evolution of the Automaton. 
	 * The String consists of the representations of all the Generations joined together 
	 * by newline characters. (Note: This was the toString method in Project 1.)
	 * @return
	 */
	public String getHistory() {
		
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.generations.size(); ++i) {
			str.append(this.generations.get(i).toString());
			str.append("\n");
		}
		// Remove the last "\n"
		str.deleteCharAt(str.length() - 1);
		return str.toString();
	}
}








