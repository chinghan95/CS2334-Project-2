/**
 * This is a utility class used to calculate quantities that characterize 
 * how an automaton evolves. Below are descriptions of the methods.
 * @author User
 *
 */
public class AutomatonMeasurements {
	
	/**
	 * Calculate the Hamming distance between two generations. 
	 * (The Hamming distance is the number of cell states that 
	 * must be flipped to make the generations the same.) 
	 * If the sizes of the generations are different, 
	 * throw an IllegalArgumentException.
	 * @param g1
	 * @param g2
	 * @return
	 */
	public static int hammingDistance(Generation g1, Generation g2) {
		int count = 0;
		if(g1.size() != g2.size())
			throw new IllegalArgumentException("The sizes of the generations are different.");
		else {
			String st1 = g1.toString();
			String st2 = g2.toString();
			for(int i = 0; i < st1.length(); ++i) {
				if(st1.charAt(i) != st2.charAt(i))
					count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculate the Hamming distance between two successive generations 
	 * (i.e., the states of the Automaton immediately before and after 
	 * the given evolution step). The step number must be greater than 0.
	 * @param stepNum
	 * @param a
	 * @return
	 * @throws InvalidStepNumException 
	 */
	public static int hammingDistance(int stepNum, Automaton a) throws InvalidStepNumException {
		int count = 0;
		if(stepNum > 0) {
			Generation g1 = a.getGeneration(stepNum);
			Generation g2 = a.getGeneration(stepNum+1);
			count = hammingDistance(g1, g2);
		}
		return count;
	}
	
	/**
	 * Calculate the Hamming distance for each pair of successive generations. 
	 * The length of the returned array is equal to the total number of steps, 
	 * and the distances are sorted by step number.
	 * @param a
	 * @return
	 * @throws InvalidStepNumException 
	 */
	public static int[] hammingDistances(Automaton a) throws InvalidStepNumException {
		int length = a.getTotalSteps();
		int[] count = new int[length];
		for(int i = 0; i < length; ++i) {
			Generation g1 = a.getGeneration(i);
			Generation g2 = a.getGeneration(i+1);
			count[i] = hammingDistance(g1, g2);
		}
		return count;
	}
	
	/**
	 * Count the number of times each subrule is applied during 
	 * the given evolution step. (The step number must be greater than 0.) 
	 * The length of the returned array is equal to the number of subrules, 
	 * and the counts are indexed by subrule number. 
	 * (Hint: The method getNumSubrules can be called on any Rule 
	 * to get the number of subrules.)
	 * @param stepNum
	 * @param a
	 * @return
	 * @throws InvalidStepNumException 
	 */
	public static int[] subruleCount (int stepNum, Automaton a) throws InvalidStepNumException {
		Rule rule = a.getRule();
		int[] count = new int[rule.getNumSubrules()];
		
		if(stepNum >= 0) {
			Generation g = a.getGeneration(stepNum);
			
			for(int i = 0; i < g.size(); ++i) {
				
				Cell[] neighborhood = rule.getNeighborhood(i, g, a.getBoundaryConditions());
				char[] ch = new char[neighborhood.length];
				
				if(rule.getNumSubrules() == 8) {
					for (int j = 0; j < neighborhood.length; ++j) {
						if(neighborhood[j].getState().equals(CellState.OFF))
							ch[j] = '0';
						else if(neighborhood[j].getState().equals(CellState.ON))
							ch[j] = '1';
					}
					
					String str = new String(ch);
					int subruleNum = Integer.parseInt(str, 2);
					count[subruleNum]++;
				}
				else if(rule.getNumSubrules() == 6){
					int subruleNum = 0;
					for (int k = 0; k < neighborhood.length; ++k) {
						if(neighborhood[k].getState().equals(CellState.ON))
							subruleNum++;
					}
					count[subruleNum]++;
				}	
			}
		}
		return count;
	}
	
	/**
	 * Return the subrule counts for every evolution step. 
	 * The length of the returned array is equal to the total number of steps, 
	 * and each element is an array of subrule counts.
	 * @param a
	 * @return
	 * @throws InvalidStepNumException 
	 */
	public static int[][] subruleCounts (Automaton a) throws InvalidStepNumException{
		int[][] count = new int[a.getTotalSteps()][a.getRule().getNumSubrules()];
		for(int i = 0; i < a.getTotalSteps(); ++i) {
			count[i] = subruleCount(i, a);
		}
		return count;
	}
}
