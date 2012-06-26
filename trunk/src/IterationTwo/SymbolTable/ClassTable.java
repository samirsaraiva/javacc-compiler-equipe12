package IterationTwo.SymbolTable;
import java.util.Set;

import IterationTwo.symbol.Symbol;

import error.ErrorMsg;

public class ClassTable extends Table{
	
	public VarTable fTable;
	public MethodTable mTable;
	public Symbol extendClass;
	public Symbol methodCurrent;
	public Symbol id;

	public ClassTable(Symbol id){
		fTable = new VarTable();
		mTable = new MethodTable();
		this.id = id;
	}
	
	@Override
	public Object get(Symbol key) {
		return fTable.get(key);
	}
	
	public Object getMB(Symbol key) {
		return mTable.get(key);
	}

	@Override
	public Set<Symbol> keys(){
		return fTable.keys();
	}
	
	public Set<Symbol> keysMT(){
		return mTable.keys();
	}

	@Override
	public void put(Symbol key, Object value) {
		if(methodCurrent==null){
			if(fTable.get(key)!=null)
				ErrorMsg.complain("Atributo "+key.toString()+" ja foi definido no escopo da classe");
			else fTable.put(key, value);
		}
		else{
			if(mTable.get(key)!=null)
				ErrorMsg.complain("Metodo "+key.toString()+" ja foi definido no escopo da classe");
			else mTable.put(key, value);
		}
	}
	
	public void setExtendsClass(Symbol cTable){
		extendClass = cTable;
	}

	public void print() {
		
		System.out.println("Atributos");
		for (Symbol k : keys()) {
			Object o = get(k);
			System.out.println(k+"="+o.toString());
		}
		System.out.println("Metodos");
		for (Symbol k : keysMT()) {
			MethodBodyTable m = (MethodBodyTable) get(k);
			m.print();
		}
	}
	
	public String toString(){
		return fTable.toString()+"\n"+mTable.toString();
	}

}
