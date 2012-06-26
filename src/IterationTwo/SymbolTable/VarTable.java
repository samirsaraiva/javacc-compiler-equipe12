package IterationTwo.SymbolTable;

import java.util.Hashtable;
import java.util.Set;

import IterationTwo.symbol.Symbol;

public class VarTable extends Table {
	
	private Hashtable<Symbol, VarInfo> bindings;
	
	public VarTable() {
		bindings = new Hashtable<Symbol, VarInfo>();
	}

	@Override
	public Object get(Symbol key) {
		return bindings.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		return bindings.keySet();
	}

	@Override
	public void put(Symbol key, Object value) {
		if(bindings.get(key)==null) bindings.put(key, (VarInfo)value);
	}

	public void print() {
		for (Symbol k : keys()) {
			System.out.println(k+" "+get(k));
		}
		
	}

}
