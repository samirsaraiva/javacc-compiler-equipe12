package IterationTwo.syntaxtree;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ImperativeSymbolTableVisitor;

public class VarDecl {
  public Type t;
  public Identifier i;
  
  public VarDecl(Type at, Identifier ai) {
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
 

}

