package IterationTwo.SymbolTable;

import java.util.Hashtable;
import java.util.Set;

import IterationTwo.symbol.Symbol;

import error.ErrorMsg;

public class SymbolTable extends Table{
	
	private Hashtable<Symbol, ClassTable> cTable;
	
	public SymbolTable(){
		cTable = new Hashtable<Symbol, ClassTable>();
	}	

	@Override
	public Object get(Symbol key) {
		return cTable.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		return cTable.keySet();
	}

	@Override
	public void put(Symbol key, Object value) {
		if(cTable.get(key)!=null)
			ErrorMsg.complain("Classe "+key.toString()+" ja foi definida no escopo do programa");
		else cTable.put(key,(ClassTable) value);
	}	
	
	public void print(){
		for (Symbol k : keys()) {
	    	   System.out.println(k);
	    	   ClassTable cT = cTable.get(k);
	    	   cT.print();
		}
	}
	
	public String toString(){
		return cTable.toString();
	}
}

