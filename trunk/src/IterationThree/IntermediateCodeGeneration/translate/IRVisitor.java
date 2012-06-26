package IterationThree.IntermediateCodeGeneration.translate;

import IterationTwo.syntaxtree.*;

public interface IRVisitor {
	  public void visit(Program n);
	  public void visit(MainClass n);
	  public void visit(ClassDeclSimple n);
	  public void visit(ClassDeclExtends n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(VarDecl n);
	  public void visit(MethodDecl n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Formal n);
	  public void visit(IntArrayType n);
	  public void visit(BooleanType n);
	  public void visit(IntegerType n);
	  public void visit(IdentifierType n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Block n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(If n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(While n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Print n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Assign n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(ArrayAssign n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(And n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(LessThan n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Plus n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Minus n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Times n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(ArrayLookup n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(ArrayLength n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Call n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(IntegerLiteral n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(True n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(False n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(IdentifierExp n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(This n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(NewArray n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(NewObject n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Not n);
	  public IterationThree.IntermediateCodeGeneration.translate.Exp visit(Identifier n);
	
}
