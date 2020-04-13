
public class Generation {
	
	/**
	 * A reference to an array of Cells. If the Generation is the 
	 * initial state of an Automaton, the array will store regular
	 * Cell objects. If the Generation was produced by the evolution 
	 * of another Generation, the array will store EvolvedCell objects.
	 */
	private Cell[] cells;
	
	/**
	 * Construct a Generation of regular Cell objects from an array of
	 * CellStates. The number of Cells is equal to the length of the array,
	 * and the Cells have the same states in the same order.
	 * @param states
	 */
	public Generation(CellState[] states) {
		this.cells = new Cell[states.length];
		for (int i = 0; i < states.length; ++i) {
			this.cells[i] = new Cell(states[i]);
		}
	}
	
	/**
	 * Construct a Generation of regular Cell objects from a String of
	 * characters representing cell states. If any symbol is not a key 
	 * in the Map SYMBOL_TO_STATE (in the CellState enum), throw an 
	 * IllegalArgumentException.
	 * @param states
	 */
	public Generation (String states) {
		this.cells = new Cell[states.length()];
		for(int i = 0; i < states.length(); ++i) {
			char ch = states.charAt(i);	
			
			if(ch != 'O' && ch != '.')
				throw new IllegalArgumentException("Invalid key. ch: " + ch);
			
			this.cells[i] = new Cell(CellState.getState(ch));
		}
	}
	
	/**
	 * Construct a Generation from an array of Cells. Make a copy of 
	 * the array to ensure the Generation is immutable. Note that
	 * this is the only way to create a Generation of EvolvedCell objects.
	 * @param cells
	 */
	public Generation(Cell[] cells) {
		this.cells = new Cell[cells.length];
		this.cells = cells.clone();
	}
	
	/**
	 * Return the size of Cell[].
	 * @return
	 */
	public int size() {
		return this.cells.length;
	}
	
	/**
	 * Return the Cell value in Cell[] array with a given index.
	 * @param idx
	 * @return
	 */
	public Cell getCell(int idx) {
		if(idx < 0)
			idx = 0;
		return this.cells[idx];
	}
	
	/**
	 * Return a string representation of the Generation. 
	 * The String consists of the concatenation of the characters 
	 * that represent the states of the Cells.
	 * (Hint: Use the toString method of the Cell class.)
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < size(); ++i) {
			str.append(cells[i].toString());
		}
		return str.toString();
	}
	
}
