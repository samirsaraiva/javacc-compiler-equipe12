package IterationTwo.SymbolTable;

import IterationTwo.syntaxtree.BooleanType;
import IterationTwo.syntaxtree.ClassDeclExtends;
import IterationTwo.syntaxtree.ClassDeclSimple;
import IterationTwo.syntaxtree.Formal;
import IterationTwo.syntaxtree.Identifier;
import IterationTwo.syntaxtree.IdentifierType;
import IterationTwo.syntaxtree.IntArrayType;
import IterationTwo.syntaxtree.IntegerType;
import IterationTwo.syntaxtree.MethodDecl;
import IterationTwo.syntaxtree.Program;
import IterationTwo.syntaxtree.VarDecl;
import IterationTwo.symbol.Symbol;

public class ImperativeSymbolTableVisitor implements SymbolTableVisitor {
	
	
	private SymbolTable sTable;
	
	public ImperativeSymbolTableVisitor() {
		sTable = new SymbolTable();
	}
	
	Table getSymbolTable(){
		return sTable;
	}
	
	@Override
	public Table visit(Program n) {
		
		ClassTable cT;
		cT = n.m.accept(this,Symbol.symbol(n.m.i1.toString()));
		sTable.put(Symbol.symbol(n.m.i1.toString()), cT);			
	    for ( int i = 0; i < n.cl.size(); i++ ) {
	        cT = n.cl.elementAt(i).accept(this);
	        if(n.cl.elementAt(i) instanceof ClassDeclSimple){
	        	sTable.put(Symbol.symbol(((ClassDeclSimple)n.cl.elementAt(i)).i.toString()), cT);
	        }
	        if(n.cl.elementAt(i) instanceof ClassDeclExtends){
	        	sTable.put(Symbol.symbol(((ClassDeclExtends)n.cl.elementAt(i)).i.toString()), cT);
	        }	        
	    }
	    return sTable;
	}

	@Override
	public Table visit(ClassDeclSimple n) {
		ClassTable cT = new ClassTable(Symbol.symbol(n.i.toString()));
		for (int i = 0; i < n.vl.size(); i++) {
			cT.fTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()), n.vl.elementAt(i).accept(this));
		}
		
		for (int i = 0; i < n.ml.size(); i++) {
			cT.methodCurrent=Symbol.symbol(n.ml.elementAt(i).i.s);
			cT.mTable.put(Symbol.symbol(n.ml.elementAt(i).i.toString()), n.ml.elementAt(i).accept(this));
		}
		cT.methodCurrent=null;
		return cT;
	}

	@Override
	public Table visit(ClassDeclExtends n) {
		ClassTable cT = new ClassTable(Symbol.symbol(n.i.toString()));
		cT.setExtendsClass(Symbol.symbol(n.j.toString()));
		for (int i = 0; i < n.vl.size(); i++) {
			cT.fTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()), n.vl.elementAt(i).accept(this));
		}
		
		for (int i = 0; i < n.ml.size(); i++) {
			cT.methodCurrent=Symbol.symbol(n.ml.elementAt(i).i.s);
			cT.mTable.put(Symbol.symbol(n.ml.elementAt(i).i.toString()), n.ml.elementAt(i).accept(this));
		}
		cT.methodCurrent=null;
		return cT;
	}

	@Override
	public VarInfo visit(VarDecl n) {
		return new VarInfo(n.t,Symbol.symbol(n.i.toString()));
	}

	@Override
	public Table visit(MethodDecl n) {
		
		MethodBodyTable mBt = new  MethodBodyTable(Symbol.symbol(n.t.toString()),Symbol.symbol(n.i.toString()));
		for (int i = 0; i < n.fl.size(); i++) {
			mBt.pTable.put(Symbol.symbol(n.fl.elementAt(i).i.toString()),
					n.fl.elementAt(i).accept(this));
			mBt.pList.add(i, Symbol.symbol(n.fl.elementAt(i).t.toString()));
		}
		
		for (int i = 0; i < n.vl.size(); i++) {
			mBt.lTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()),n.vl.elementAt(i).accept(this));
		}
						
		return mBt;
	}

	@Override
	public VarInfo visit(Formal n) {
		return new VarInfo(n.t,Symbol.symbol(n.i.s));
	}

	@Override
	public Symbol visit(IntArrayType n) {
		return Symbol.symbol("int[]");
	}

	@Override
	public Symbol visit(BooleanType n) {
		return Symbol.symbol("boolean");
	}

	@Override
	public Symbol visit(IntegerType n) {
		return Symbol.symbol("int");
	}

	@Override
	public Symbol visit(IdentifierType n) {
		return Symbol.symbol(n.s);
	}
	
	@Override
	public Symbol visit(Identifier n) {
		return Symbol.symbol(n.s);
		
	}
	
}
