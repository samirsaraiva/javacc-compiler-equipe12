package IterationTwo.syntaxtree;
import IterationTwo.symbol.Symbol;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class BooleanType extends Type {
  @Override
public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  @Override
public String toString(){
	  return "boolean";
  }

  @Override
  public Symbol accept(TypeCheckVisitor v) {
	return v.visit(this);
  }
  
	@Override
	public void accept(IRTranslateVisitor v) {
		v.visit(this);
	}
}

