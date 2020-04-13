/**
 * This class represents a single cell in an automaton produced by 
 * an earlier generation of cells. In addition to the cell state,
 * the class stores the number of the sub-rule used to produce the cell.
 * @author User
 *
 */
public class EvolvedCell extends Cell{
	
	private int subruleNum;
	
	public EvolvedCell(CellState state, int subruleNum) {
		super(state);
		this.subruleNum = subruleNum;
	}
	
	public int getSubruleNum() {
		return this.subruleNum;
	}
}
