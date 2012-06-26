package IterationTwo.syntaxtree;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class ArrayAssign extends Statement {
  public Identifier i;
  public Exp e1,e2;

  public ArrayAssign(Identifier ai, Exp ae1, Exp ae2) {
    i=ai; e1=ae1; e2=ae2;
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


