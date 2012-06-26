package IterationTwo.SymbolTable;

import IterationTwo.syntaxtree.BooleanType;
import IterationTwo.syntaxtree.ClassDeclExtends;
import IterationTwo.syntaxtree.ClassDeclSimple;
import IterationTwo.syntaxtree.Formal;
import IterationTwo.syntaxtree.Identifier;
import IterationTwo.syntaxtree.IdentifierType;
import IterationTwo.syntaxtree.IntArrayType;
import IterationTwo.syntaxtree.IntegerType;
import IterationTwo.syntaxtree.MethodDecl;
import IterationTwo.syntaxtree.Program;
import IterationTwo.syntaxtree.VarDecl;
import IterationTwo.symbol.Symbol;

public interface SymbolTableVisitor {
	  public Table visit(Program n);
	  public Table visit(ClassDeclSimple n);
	  public Table visit(ClassDeclExtends n);
	  public VarInfo visit(VarDecl n);
	  public Table visit(MethodDecl n);
	  public VarInfo visit(Formal n);
	  public Symbol visit(IntArrayType n);
	  public Symbol visit(BooleanType n);
	  public Symbol visit(IntegerType n);
	  public Symbol visit(IdentifierType n);
	  public Symbol visit(Identifier n);
}
