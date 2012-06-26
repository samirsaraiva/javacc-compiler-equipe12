package IterationTwo.SymbolTable;

import IterationTwo.syntaxtree.Type;
import frame.Access;
import IterationTwo.symbol.Symbol;

public class VarInfo
{
	public Type type;
	public Symbol name;
	
    public Access access;
    
	public VarInfo(Type t, Symbol s)
	{
		super();
		
		type = t;
		name = s;
		
	}

}
