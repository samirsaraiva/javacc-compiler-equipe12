package IterationTwo.SymbolTable;

import java.util.ArrayList;
import java.util.Set;

import IterationTwo.symbol.Symbol;

import error.ErrorMsg;

public class MethodBodyTable extends Table{
	public VarTable pTable;
	public ArrayList<Symbol> pList;
	public VarTable lTable;
	public Symbol returnSymbol;
	private boolean isInParams; 
	private Symbol id;
	
	
	public MethodBodyTable(Symbol returnS,Symbol id) {
		pTable = new VarTable();
		pList = new ArrayList<Symbol>();
		lTable = new VarTable();
		returnSymbol = returnS;
		isInParams = true;
		this.id = id;
	}

	public Object getReturn(){
		return returnSymbol;
	}

	@Override
	public Object get(Symbol key) {
		Object s;
		if((s=pTable.get(key))!=null)return s; 
		return lTable.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		Set< Symbol> s = pTable.keys();
		s.addAll(lTable.keys());
		return s;
	}

	@Override
	public void put(Symbol key, Object value) {
		if(isInParams){
			if(pTable.get(key)!=null)
				ErrorMsg.complain("Parametro de entrada"+key.toString()+" ja foi definido");
			else{
				pList.add((Symbol) value);
				pTable.put(key, value);
			}
		}
		else{
			if(lTable.get(key)!=null||pTable.get(key)!=null)ErrorMsg.complain("Variavel "+key.toString()+" ja foi definida no escopo do metodo");
			else lTable.put(key, value);
		}
	}
	
	public void isInParams(boolean b){
		isInParams=b;
	}

	public void print() {
		System.out.print(returnSymbol+" "+id+" (");
		pTable.print();
		System.out.println(" )");
		
		System.out.println("Variaveis");
		lTable.print();
		
	}
	
}
