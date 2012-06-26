package IterationTwo.SymbolTable;
import java.util.Set;

import IterationTwo.symbol.Symbol;

public abstract class Table {
	public abstract void put(Symbol key, Object value);
	public abstract Object get(Symbol key);
	public abstract Set<Symbol> keys();
}
