package IterationTwo.TypeChecking;

import IterationTwo.syntaxtree.*;
import IterationTwo.SymbolTable.SymbolTable;
import IterationTwo.symbol.Symbol;

public interface CheckVisitor {
	
	  public SymbolTable visit(Program n);
	  public void visit(MainClass n);
	  public void visit(ClassDeclSimple n);
	  public void visit(ClassDeclExtends n);
	  public void visit(MethodDecl n);
	  public Symbol visit(Formal n);
	  public Symbol visit(IntArrayType n);
	  public Symbol visit(BooleanType n);
	  public Symbol visit(IntegerType n);
	  public Symbol visit(IdentifierType n);
	  public void visit(Block n);
	  public void visit(If n);
	  public void visit(While n);
	  public void visit(Print n);
	  public void visit(Assign n);
	  public void visit(ArrayAssign n);
	  public Symbol visit(And n);
	  public Symbol visit(LessThan n);
	  public Symbol visit(Plus n);
	  public Symbol visit(Minus n);
	  public Symbol visit(Times n);
	  public Symbol visit(ArrayLookup n);
	  public Symbol visit(ArrayLength n);
	  public Symbol visit(Call n);
	  public Symbol visit(IntegerLiteral n);
	  public Symbol visit(True n);
	  public Symbol visit(False n);
	  public Symbol visit(IdentifierExp n);
	  public Symbol visit(This n);
	  public Symbol visit(NewArray n);
	  public Symbol visit(NewObject n);
	  public Symbol visit(Not n);
	  public Symbol visit(Identifier n);

}
