package IterationTwo.syntaxtree;
import IterationTwo.symbol.Symbol;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ImperativeSymbolTableVisitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;


public class Formal {
  public Type t;
  public Identifier i;
 
  public Formal(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

	public Object accept(ImperativeSymbolTableVisitor v) {
		return v.visit(this);
	}

	public Symbol accept(TypeCheckVisitor v) {
		return v.visit(this);
	}
	
	public IterationThree.IntermediateCodeGeneration.translate.Exp accept(IRTranslateVisitor v) {
		return v.visit(this);
	}
	
}

