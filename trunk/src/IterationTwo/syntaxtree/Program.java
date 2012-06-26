package IterationTwo.syntaxtree;
import IterationTwo.TypeChecking.CheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ImperativeSymbolTableVisitor;
import IterationTwo.SymbolTable.Table;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;

public class Program {
  public MainClass m;
  public ClassDeclList cl;

  public Program(MainClass am, ClassDeclList acl) {
    m=am; cl=acl; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public Table accept(ImperativeSymbolTableVisitor v) {
	return v.visit(this);
  }

  public void accept(CheckVisitor v) {
	v.visit(this);	
  }
  
  public void accept(IRTranslateVisitor v) {
	v.visit(this);
  }
}

