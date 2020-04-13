/**
 * This class represents a rule that governs the evolution of 
 * a two-state totalistic cellular automaton with a neighborhood radius of 2.
 * @author User
 *
 */
public class TotalisticRule extends Rule{
	
	private final int CONFIGUATION_NUM = 6;
	
	public TotalisticRule(int ruleNum) throws InvalidRuleNumException {
		super(checkRuleNum(ruleNum));
	}
	
	private static int checkRuleNum (int ruleNum) throws InvalidRuleNumException {
		if(ruleNum < 0 || ruleNum > 63) {
			throw new InvalidRuleNumException();
		}
		return ruleNum;
	}
	
	@Override
	/**
	 * Return the number of subrules used to determine the next state
	 * of a cell from its neighborhood.(i.e., the length of the rule table).
	 * @return
	 */
	public int getNumSubrules() {
		return this.CONFIGUATION_NUM;
	}

	@Override
	/**
	 * Return the neighborhood of the cell with index cellIdx subject to 
	 * the given boundary conditions. The neighborhood of a cell consists of 
	 * the cells that determine its state in the next generation. For a one-dimensional 
	 * automaton, the neighborhood is typically the cell itself and the two adjacent cells.
	 */
	public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
		Cell[] neighborgood = new Cell[5];
		neighborgood[0] = bc.getNeighbor(cellIdx, -2, gen);
		neighborgood[1] = bc.getNeighbor(cellIdx, -1, gen);
		neighborgood[2] = gen.getCell(cellIdx);
		neighborgood[3] = bc.getNeighbor(cellIdx, 1, gen);
		neighborgood[4] = bc.getNeighbor(cellIdx, 2, gen);
		return neighborgood;
	}

	@Override
	/**
	 * Apply the rule to a cell with the given neighborhood. The method 
	 * returns a new EvolvedCell that represents the cell in the next generation.
	 * @param neighborgood
	 * @return
	 */
	public EvolvedCell evolve(Cell[] neighborhood) {
		
		int subruleNum = 0;
		for (int i = 0; i < neighborhood.length; ++i) {
			if(neighborhood[i].getState().equals(CellState.ON)) {
				subruleNum++;
			}
		}
		
		String base2RuleNum = Integer.toString(getRuleNum(), 2);
		for(int i = base2RuleNum.length(); i < CONFIGUATION_NUM; ++i) {
			base2RuleNum =  "0" + base2RuleNum;
		}
		
		char nextStateSymbol = base2RuleNum.charAt(CONFIGUATION_NUM - subruleNum - 1);
		if(nextStateSymbol == '0')
			nextStateSymbol = '.';
		else if(nextStateSymbol == '1')
			nextStateSymbol = 'O';
		EvolvedCell evolvedCell = new EvolvedCell(CellState.getState(nextStateSymbol), subruleNum);
		
		return evolvedCell;
	}

	@Override
	/**
	 *  Return a string representation of the rule table. 
	 *  For example, the String returned for rule 22 is "5 4 3 2 1 0\n. O . O O ."
	 */
	public String toString() {
		String ruleTable = "5 4 3 2 1 0\n";
		String base2RuleNum = Integer.toString(getRuleNum(), 2);
		for(int i = base2RuleNum.length(); i < CONFIGUATION_NUM; ++i) {
			base2RuleNum =  "0" + base2RuleNum;
		}
		
		char[] ch = new char[base2RuleNum.length()];
		for (int i = 0; i < base2RuleNum.length(); ++i) {
			ch[i] = base2RuleNum.charAt(i);
			if(ch[i] == '0')
				ch[i] = '.';
			else if(ch[i] == '1')
				ch[i] = 'O';	
		}
		
		base2RuleNum = String.valueOf(ch); 
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < base2RuleNum.length(); i++)
		{
		   result = result.append(base2RuleNum.charAt(i));
		   result = result.append(" ");
		}
		String str = result.toString().replaceAll("\\s+$", "");
		ruleTable = ruleTable + str;
		return ruleTable;
	}
	
}
