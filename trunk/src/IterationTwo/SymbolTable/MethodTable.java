package IterationTwo.SymbolTable;

import java.util.Hashtable;
import java.util.Set;

import IterationTwo.symbol.Symbol;

import error.ErrorMsg;

public class MethodTable extends Table{
	
	public Hashtable<Symbol, MethodBodyTable> body;
	public Symbol methodCurrent;
	
	public MethodTable() {
		body = new Hashtable<Symbol, MethodBodyTable>();
	}
	
	@Override
	public Object get(Symbol key) {
		return body.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		return body.keySet();
	}

	@Override
	public void put(Symbol key, Object value) {
		if(body.get(key)!=null)
			ErrorMsg.complain("Metodo "+key.toString()+" ja foi definido no escopo da classe");
		else body.put(key, (MethodBodyTable) value);
	}

	

}
