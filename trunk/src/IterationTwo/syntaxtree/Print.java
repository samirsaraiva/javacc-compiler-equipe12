package IterationTwo.syntaxtree;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class Print extends Statement {
  public Exp e;

  public Print(Exp ae) {
    e=ae; 
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
  public void accept(TypeCheckVisitor v) {
	  v.visit(this);
  }

	@Override
	public IterationThree.IntermediateCodeGeneration.translate.Exp accept(IRTranslateVisitor v) {
		return v.visit(this);
	}
}

