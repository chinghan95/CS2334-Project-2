
public class Cell {
	
	private CellState state;
	
	public Cell() {
		this.state = CellState.OFF;
	}
	
	/**
	 * Constructor method with a given CellState value.
	 * @param state
	 */
	public Cell(CellState state) {
		this.state = state;
	}
	
	/**
	 * Return the CellState.
	 * @return
	 */
	public CellState getState() {
		return this.state;
	}
	
	public String toString() {
		return this.state.toString();
	}
}
