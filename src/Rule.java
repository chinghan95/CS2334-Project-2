/**
 * This abstract class represents a cellular automaton rule that governs 
 * how cell states change. Any concrete subclass can be used with the 
 * Automaton class.
 * @author User
 *
 */
public abstract class Rule {
	
	private int ruleNum;
	
	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;	
	}
	
	public int getRuleNum() {
		return this.ruleNum;
	}
	
	/**
	 * Apply the rule to a given Generation, subject to the given boundary
	 * conditions, to calculate the next Generation.
	 * (Hint: Use the abstract methods described above.)
	 * @param gen
	 * @param bc
	 * @return
	 */
	public Generation evolve(Generation gen, BoundaryConditions bc) {
		Cell[] nextGeneration = new Cell[gen.size()];
		for(int i = 0; i < gen.size(); ++i) {
			Cell[] neighborhood = getNeighborhood(i, gen, bc);
			EvolvedCell newCell = evolve(neighborhood);
			nextGeneration[i] = newCell;
		}
		Generation nextGen = new Generation(nextGeneration);
		return nextGen;
	}
	
	/**
	 * Return the number of subrules used to determine the next state
	 * of a cell from its neighborhood.(i.e., the length of the rule table).
	 * @return
	 */
	public abstract int getNumSubrules();
	
	/**
	 * Return the neighborhood of the cell with index cellIdx subject
	 * to the given boundary conditions. The neighborhood of a cell
	 * consists of the cells that determine its state in the next generation.
	 * For a one-dimensional automaton, the neighborhood is typically 
	 * the cell itself and the two adjacent cells.
	 * @param cellIdx
	 * @param gen
	 * @param bc
	 * @return
	 */
	public abstract Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc);
	
	/**
	 * Apply the rule to a cell with the given neighborhood. The method 
	 * returns a new EvolvedCell that represents the cell in the next generation.
	 * @param neighborgood
	 * @return
	 */
	public abstract EvolvedCell evolve(Cell[] neighborgood);
	
	public abstract String toString();
}
