/**
 * This class represents a rule that governs the evolution of an elementary 
 * cellular automaton.
 * @author User
 *
 */
public class ElementaryRule extends Rule{
	
	private final int CONFIGUATION_NUM = 8;
	
	protected ElementaryRule(int ruleNum) throws InvalidRuleNumException {
		super(checkRuleNum(ruleNum));
	}
	
	private static int checkRuleNum (int ruleNum) throws InvalidRuleNumException {
		if(ruleNum < 0 || ruleNum > 255) {
			throw new InvalidRuleNumException();
		}
		return ruleNum;
	}
	
	@Override
	/**
	 * Return the number of subrules used to determine the next state of 
	 * a cell from its neighborhood (i.e., the length of the rule table).
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
		Cell[] neighborgood = new Cell[3];
		neighborgood[0] = bc.getNeighbor(cellIdx, -1, gen);
		neighborgood[1] = gen.getCell(cellIdx);
		neighborgood[2] = bc.getNeighbor(cellIdx, 1, gen);
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

		char[] ch = new char[neighborhood.length];
		for (int i = 0; i < neighborhood.length; ++i) {
			if(neighborhood[i].getState().equals(CellState.OFF))
				ch[i] = '0';
			else if(neighborhood[i].getState().equals(CellState.ON))
				ch[i] = '1';
		}
		
		String str = new String(ch);
		int subruleNum = Integer.parseInt(str, 2);
		
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
	/**Return a two-line string representation of the rule table, 
	 * using the characters that represent CellState.OFF and CellState.ON. 
	 * Separate each neighborhood configuration with a single space, 
	 * and align the characters on the second line with the center character 
	 * of each configuration.
	 */
	public String toString() {
		
		String ruleTable = "OOO OO. O.O O.. .OO .O. ..O ...\n";
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
		   result = result.append("   ");
		}
		String str = result.toString().replaceAll("\\s+$", "");
		ruleTable = ruleTable + " " + str + " ";
		return ruleTable;
	}

}
