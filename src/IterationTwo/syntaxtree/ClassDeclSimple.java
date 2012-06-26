package IterationTwo.syntaxtree;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ClassTable;
import IterationTwo.SymbolTable.SymbolTableVisitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class ClassDeclSimple extends ClassDecl {
  public Identifier i;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml) {
    i=ai; vl=avl; ml=aml;
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
  public ClassTable accept(SymbolTableVisitor v) {
	// TODO Auto-generated method stub
	return (ClassTable) v.visit(this);
  }

  @Override
  public void accept(TypeCheckVisitor v) {
	v.visit(this);	
  }
	@Override
	public void accept(IRTranslateVisitor v) {
		v.visit(this);
	}
}


