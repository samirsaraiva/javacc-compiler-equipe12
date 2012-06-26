package IterationTwo.syntaxtree;
import IterationTwo.symbol.Symbol;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class IdentifierExp extends Exp {
  public String s;
  public IdentifierExp(String as) { 
    s=as;
  }

  @Override
public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  @Override
  public Symbol accept(TypeCheckVisitor v) {
  	 return v.visit(this);
  }
	@Override
	public IterationThree.IntermediateCodeGeneration.translate.Exp accept(IRTranslateVisitor v) {
		return v.visit(this);
	}
}

