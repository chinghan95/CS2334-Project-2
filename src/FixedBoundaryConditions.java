/**
 * This class treats an automaton as though it has an infinite number of cells 
 * extending to the left and right. All of the cells with indices less than 0 
 * are fixed in the same state. Call these the "left boundary cells." 
 * All of the cells with indices greater than or equal to the size of the automaton 
 * are fixed in another state. Call these the "right boundary cells."
 * @author User
 *
 */
public class FixedBoundaryConditions implements BoundaryConditions{

	private CellState left;
	private CellState right;
	
	/**
	 * The states of the boundary cells are specified by the constructor 
	 * parameters left and right. Since each parameter has two values, 
	 * there are 4 possible fixed boundary conditions
	 * @param left
	 * @param right
	 */
	public FixedBoundaryConditions(CellState left, CellState right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Return left boundary cell's CellState.
	 * @return
	 */
	public CellState getLeftState() {
		return this.left;
	}
	
	/**
	 * Return right boundary cell's CellState.
	 * @return
	 */
	public CellState getRightState() {
		return this.right;
	}
	
	@Override
	/**
	 * Use left and right boundary cell's states to determine the neighbor of 
	 * the given cellIdx of a given generation.
	 */
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {
		Cell cell;
		if((cellIdx + offset) < 0) {
			cell = new Cell(this.left);
		}
		else if((cellIdx + offset) >= gen.size()) {
			cell = new Cell(this.right);
		}
		else {
			cell = gen.getCell(cellIdx + offset);
		}
		return cell;	
	}

}
