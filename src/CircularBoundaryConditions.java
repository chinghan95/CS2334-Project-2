/**
 * This class treats the cells of an automaton as though 
 * they are arranged in a circle. 
 * @author User
 *
 */
public class CircularBoundaryConditions implements BoundaryConditions{
	
	public CircularBoundaryConditions() {
		
	}
	
	@Override
	/**
	 * It implements getNeighbor so that invalid neighbor 
	 * indices wrap back into the valid range using modular arithmetic.
	 */
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {
		Cell cell;
		int idx;
		if((cellIdx + offset) >= gen.size()) {
			idx = (cellIdx + offset) % gen.size();
			cell = gen.getCell(idx);
		}
		else if((cellIdx + offset) < 0) {
			idx = cellIdx + offset + gen.size();
			while(idx < 0)
				idx = idx + gen.size();
			cell = gen.getCell(idx);
		}
		else {
			cell = gen.getCell(cellIdx + offset);
		}
		return cell;
	}

}
