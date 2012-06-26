package IterationTwo.syntaxtree;
import IterationTwo.symbol.Symbol;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class IntegerLiteral extends Exp {
  public int i;

  public IntegerLiteral(int ai) {
    i=ai;
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



