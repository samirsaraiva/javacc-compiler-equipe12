package IterationTwo.syntaxtree;
import IterationTwo.TypeChecking.TypeCheckVisitor;
import IterationTwo.visitor.TypeVisitor;
import IterationTwo.visitor.Visitor;
import IterationTwo.SymbolTable.ClassTable;
import IterationTwo.SymbolTable.SymbolTableVisitor;
import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;


public abstract class ClassDecl {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract ClassTable accept(SymbolTableVisitor v);
  public abstract void accept(TypeCheckVisitor v);
  public abstract void accept(IRTranslateVisitor v) ;

}

