package IterationTwo.TypeChecking;

import java.util.Stack;

import IterationTwo.syntaxtree.*;
import IterationTwo.SymbolTable.ClassTable;
import IterationTwo.SymbolTable.ImperativeSymbolTableVisitor;
import IterationTwo.SymbolTable.MethodBodyTable;
import IterationTwo.SymbolTable.SymbolTable;
import IterationTwo.SymbolTable.Table;
import IterationTwo.SymbolTable.VarInfo;

import error.ErrorMsg;

import IterationTwo.symbol.Symbol;

public class TypeCheckVisitor implements CheckVisitor {

	private SymbolTable table;	
	private ImperativeSymbolTableVisitor visitor;
	private Stack<Table> scope;
	
	public TypeCheckVisitor() {
		visitor = new ImperativeSymbolTableVisitor();
		scope = new Stack<Table>();
	}
	
	@Override
	public SymbolTable visit(Program n) {
		//constroi tabela de simbolos
		table = (SymbolTable) visitor.visit(n);
		//visita no main
		n.m.accept(this);
		//visita nos que representam as classes
		for ( int i = 0; i < n.cl.size(); i++ ) {
			n.cl.elementAt(i).accept(this);
	    }
		return table;
	}
	
	/**
	 * atualiza o escopo do programa para classe representada por symbol
	 * @param symbol
	 */
	private Table beginScope(Symbol symbol) {
		Table t = (Table) table.get(symbol);
		scope.push(t);
		return t;
	}
	
	private void endScope(){
		scope.pop();
	}

	@Override
	public void visit(MainClass n) {
		// visita statements
		n.s.accept(this);
	}

	@Override
	public void visit(ClassDeclSimple n) {
		//atualiza escopo para classe n
		ClassTable ct = (ClassTable) beginScope(Symbol.symbol(n.i.toString()));
		//visita declaracao de metodos
		for ( int i = 0; i < n.ml.size(); i++ ) {
			//seta como metodo corrente o que esta sendo visitado
			ct.mTable.methodCurrent = Symbol.symbol(n.ml.elementAt(i).i.toString());
			n.ml.elementAt(i).accept(this);
	    }
		//sai do escopo da classe
		endScope();
	}

	@Override
	public void visit(ClassDeclExtends n) {
		//atualiza escopo para classe n
		ClassTable ct = (ClassTable) beginScope(Symbol.symbol(n.i.toString()));
		
		//visita declaracao de metodos
		for ( int i = 0; i < n.ml.size(); i++ ) {
			//seta como metodo corrente o que esta sendo visitado
			ct.mTable.methodCurrent = Symbol.symbol(n.ml.elementAt(i).i.toString());
			n.ml.elementAt(i).accept(this);
	    }
		//verifica se a classe mae esta definidida
		if(table.get(ct.extendClass)==null)
			ErrorMsg.complain("Classe mae nao encontrada "+n.i+"\n");
		//sai do escopo da classe
		endScope();
	}

	@Override
	public void visit(MethodDecl n) {
		ClassTable ct = (ClassTable)scope.peek();
		//atribui a mBT a tabela com dados do metodo n
		MethodBodyTable mBT = (MethodBodyTable)ct.mTable.get(Symbol.symbol(n.i.toString()));
		
		//visita statements 
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
	    }		
		//verifica se parametro de retorno tem o mesmo tipo da expressao retornada
		if(!mBT.returnSymbol.equals(n.e.accept(this)))
			ErrorMsg.complain("Tipo de retorno incompativel "+n.i+"\n");
	}

	
	@Override
	public Symbol visit(Formal n) {
		return n.t.accept(this);
	}

	@Override
	public Symbol visit(IntArrayType n) {
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(BooleanType n) {
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(IntegerType n) {
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(IdentifierType n) {
		//verifica se o identificador corresponde a uma classe da tabela de simbolos
		ClassTable ct = (ClassTable) table.get(Symbol.symbol(n.toString()));
		if(ct==null){
			ErrorMsg.complain("Classe nao encontrada "+n.s+"\n");
			return Symbol.symbol("");
		}
		return ct.id;
	}

	@Override
	public void visit(Block n) {
		//visita statements
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
	    }
	}

	@Override
	public void visit(If n) {
		//verifica se expressao da clausula if e do tipo boolean
		if(!n.e.accept(this).equals(new BooleanType())){
			 ErrorMsg.complain("Tipo incompativel \n");
		}
		//visita statements 
		//then
		n.s1.accept(this);
		//else
		n.s2.accept(this);
	}

	@Override
	public void visit(While n) {
		//verifica se expressao da clausula while e do tipo boolean
		if(!n.e.accept(this).equals(new BooleanType())){
			 ErrorMsg.complain("Tipo incompativel while \n");
		}
		//visita statement
		n.s.accept(this);
	}

	@Override
	public void visit(Print n) {
		//verifica se expressao parametro da system.ou.print e do tipo 
		//int ou boolean
		if(!n.e.accept(this).equals(new IntegerType())&&
			!n.e.accept(this).equals(new BooleanType())){
			ErrorMsg.complain("Tipo incompativel print \n");
		}
	}

	private boolean isMain(){
		return scope.isEmpty();
	}
	
	@Override
	public void visit(Assign n) {
		if(isMain()) ErrorMsg.complain("Variavel nao declarada "+n.i+" \n");
		else{
			Symbol type = n.i.accept(this);
			//verifica se a variavel eh do tipo da expressao da atribuicao
			if(!type.equals(n.e.accept(this)))
				ErrorMsg.complain("Tipo incompativel "+n.i+"\n");
		}
	}

	@Override
	public void visit(ArrayAssign n){
		if(isMain()) ErrorMsg.complain("Variavel nao declarada "+n.i+"\n");
		else{
			Symbol type = n.i.accept(this);
			//verifica se a variavel eh do tipo int[]
			if(!type.equals(new IntArrayType()))
				ErrorMsg.complain("Tipo incompativel "+n.i+"\n");
			//verifica se expressao do indice e atribuicao sao do tipo int
			if(!n.e1.accept(this).equals(new IntegerType())){
				ErrorMsg.complain("Tipo incompativel "+n.i+"\n");
			}
			if(!n.e2.accept(this).equals(new IntegerType())){
				ErrorMsg.complain("Tipo incompativel "+n.i+"\n");
			}
		}
	}

	
	@Override
	public Symbol visit(Identifier n) {
		if(isMain()){
			ErrorMsg.complain("Variavel nao declarada "+n.s+"\n");
			return Symbol.symbol("");
		}
		else{
			ClassTable cT = (ClassTable) scope.peek();
			MethodBodyTable mBT = (MethodBodyTable) cT.mTable.get(cT.mTable.methodCurrent);
			Symbol var = Symbol.symbol(n.s);
			//verifica se eh variavel local do metodo corrente, caso onde type!=null
			VarInfo varDc = (VarInfo) mBT.lTable.get(var);
			if(varDc==null ){
				//caso contrario,
				//verifica se eh parametro do metodo, caso onde type!=null
				varDc = (VarInfo) mBT.pTable.get(var);
				if(varDc==null){
					//caso contrario,
					//verifica se eh atributo da classe, caso onde type!=null
					varDc = (VarInfo) cT.fTable.get(var);
					if(varDc==null){
						//retorna tipo declarado na superclasse,
						//ou um tipo vazio se nao estah declarado 
						return verifierExtendsClass(Symbol.symbol(n.s));
					}
				}
			}
			return Symbol.symbol(varDc.type.toString());
		}
	}
	
	private Symbol verifierExtendsClass(Symbol var){
		//Symbol var = Symbol.symbol(n.s);
		ClassTable cT = (ClassTable) scope.peek();
		//enquanto ha superclasse na hierarquia de classes
		while(cT.extendClass!=null){
			//novo escopo eh o da superclasse
			cT = (ClassTable) beginScope(cT.extendClass);
			VarInfo varDc = (VarInfo) cT.fTable.get(var);
			endScope();
			//verifica se a variavel eh um atributo da classe
			if(varDc!=null)return Symbol.symbol(varDc.type.toString());
		}
		ErrorMsg.complain("Variavel nao declarada "+var+"\n");
		return Symbol.symbol("");
	}

	@Override
	public Symbol visit(And n) {
		//verifica se operandos sao do tipo boolean
		if(!n.e1.accept(this).equals(new BooleanType())&&
			!n.e2.accept(this).equals(new BooleanType())){
			ErrorMsg.complain("Tipo incompativel and \n");
		}
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(LessThan n) {
		//verifica se operandos sao do tipo int
		if(!n.e1.accept(this).equals(new IntegerType())&&
			!n.e2.accept(this).equals(new IntegerType())){
			ErrorMsg.complain("Tipo incompativel lessthan \n");
		}
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(Plus n) {
		//verifica se operandos sao do tipo int
		if(!n.e1.accept(this).equals(new IntegerType())&&
			!n.e2.accept(this).equals(new IntegerType())){
			ErrorMsg.complain("Tipo incompativel plus \n");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(Minus n) {
		//verifica se operandos sao do tipo int
		if(!n.e1.accept(this).equals(new IntegerType())&&
			!n.e2.accept(this).equals(new IntegerType())){
			ErrorMsg.complain("Tipo incompativel minus \n");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(Times n) {
		//verifica se operandos sao do tipo int
		if(!n.e1.accept(this).equals(new IntegerType())&&
			!n.e2.accept(this).equals(new IntegerType())){
			ErrorMsg.complain("Tipo incompativel times \n");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(ArrayLookup n) {
		//verifica se variavel indexada eh do tipo int[]
		//e se indice eh do tipo int
		if(!n.e1.accept(this).equals(new IntArrayType())&&
			!n.e2.accept(this).equals(new IntegerType())){
			ErrorMsg.complain("Tipo incompativel arraylookup \n");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(ArrayLength n) {
		//verifica se variavel indexada eh do tipo int[]
		if(!n.e.accept(this).equals(new IntArrayType())){
			ErrorMsg.complain("Tipo incompativel arraylength \n");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(Call n) {
		Symbol cl = n.e.accept(this);
		MethodBodyTable mBT;
		Symbol m;
		//verifica se a expressao eh uma classe
		if(table.get(cl)!=null){
			m = Symbol.symbol(n.i.s);
			//retorna tabela do metodo chamado
			mBT=verifierMethodClass(m,cl);
			if(mBT!=null){
				//verifica se os parametros passados sao tem tipos corretos
				for (int i = 0; i < mBT.pList.size(); i++) 
					if(!n.el.elementAt(i).accept(this).equals(mBT.pList.get(i)))
						ErrorMsg.complain("Parametro tem tipo incopativel "+n.i+"\n");
				//retorna o tipo de retorno do metodo	
				return mBT.returnSymbol; 
			}
		}		
		//retorna um tipo invalido
		return Symbol.symbol("");
		
	}
	
	private MethodBodyTable verifierMethodClass(Symbol m, Symbol cl){
		MethodBodyTable mBT;
		//enquanto ha superclasse na hierarquia de classes
		ClassTable cT = (ClassTable) beginScope(cl);
		//encontra metodo na classe 
		mBT=(MethodBodyTable) cT.mTable.get(m);
		
		if(mBT==null && cT.extendClass!=null)
			mBT = verifierMethodClass(m, cT.extendClass);
		endScope();
		return mBT;
	}

	@Override
	public Symbol visit(IntegerLiteral n) {
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(True n) {
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(False n) {
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(IdentifierExp n) {
		if(isMain()){
			ErrorMsg.complain("Variavel nao declarada "+n.s+"\n");
			return Symbol.symbol("");
		}
		else{
			ClassTable cT = (ClassTable) scope.peek();
			MethodBodyTable mBT = (MethodBodyTable) cT.mTable.get(cT.mTable.methodCurrent);
			Symbol var = Symbol.symbol(n.s);
			//verifica se eh variavel local do metodo corrente, caso onde type!=null
			VarInfo varDc = (VarInfo) mBT.lTable.get(var);
			if(varDc==null ){
				varDc = (VarInfo) mBT.pTable.get(var);
				if(varDc==null){
					//caso contrario,
					//verifica se eh atributo da classe, caso onde type!=null
					varDc = (VarInfo) cT.fTable.get(var);
					if(varDc==null){
						//retorna tipo declarado na superclasse,
						//ou um tipo vazio se nao estah declarado 
						return verifierExtendsClass(Symbol.symbol(n.s));
					}
				}
				
			}
			return Symbol.symbol(varDc.type.toString());
		}	
	}
	
	@Override
	public Symbol visit(This n) {
		if(isMain()){
			ErrorMsg.complain("Tipo incompativel this \n");
			return Symbol.symbol("");
		}
		ClassTable cT = (ClassTable) scope.peek();
		return cT.id;
	}

	@Override
	public Symbol visit(NewArray n) {
		//verifica se valor no campo do tamanho eh int
		if(!n.e.accept(this).equals(new IntegerType()))
			ErrorMsg.complain("Tipo incompativel newarray \n");
		return Symbol.symbol(new IntArrayType().toString());
	}

	@Override
	public Symbol visit(NewObject n) {
		ClassTable cT = (ClassTable) table.get(Symbol.symbol(n.i.toString()));
		if(cT!=null)return cT.id;
		ErrorMsg.complain("Tipo da classe nao existe "+n.i+"\n");
		return Symbol.symbol("");
	}

	@Override
	public Symbol visit(Not n) {
		//verifica se o operando eh do tipo boolean
		if(!n.e.accept(this).equals(new BooleanType())){
			ErrorMsg.complain("Tipo incompativel not \n");
		}
		return Symbol.symbol(new BooleanType().toString());
	}

}
