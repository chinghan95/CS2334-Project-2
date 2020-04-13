import java.util.HashMap;
import java.util.Map;

public enum CellState {
	
	OFF('.'),
	ON('O');
	
	private char symbol;
	private static final Map<Character, CellState> SYMBOL_TO_STATE = new HashMap<>();
	
	// Init the map
	static {
		SYMBOL_TO_STATE.put('.', OFF);
		SYMBOL_TO_STATE.put('O', ON);
	}
	
	/**
	 * Constructor method
	 * @param symbol
	 */
	private CellState(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Return the CellState associated with a given symbol.
	 * @param symbol
	 * @return
	 */
	public static CellState getState(char symbol) {
		return SYMBOL_TO_STATE.get(symbol);
	}
	
	/**
	 * Return the symbol that represents the CellState value.
	 */
	public String toString() {
		return Character.toString(this.symbol);
	}
}
