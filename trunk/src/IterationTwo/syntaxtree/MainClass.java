package IterationTwo.syntaxtree;

import IterationTwo.symbol.Symbol;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ClassTable;
import IterationTwo.SymbolTable.ImperativeSymbolTableVisitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class MainClass {
  public Identifier i1,i2;
  public Statement s;

  public MainClass(Identifier ai1, Identifier ai2, Statement as) {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  	public ClassTable accept(ImperativeSymbolTableVisitor v, Symbol id) {
  		return new ClassTable(id);
  	}

	public void accept(TypeCheckVisitor v) {
		 v.visit(this);
	}

	public void accept(IRTranslateVisitor v) {
		v.visit(this);
	}
	  
}


