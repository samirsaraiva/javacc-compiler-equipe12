package IterationThree.IntermediateCodeGeneration.translate;
import java.util.ArrayList;
import java.util.Stack;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationThree.IntermediateCode.tree.exp.*;
import IterationThree.IntermediateCode.tree.stm.*;
import IterationThree.IntermediateCodeGeneration.translate.Exp;
import IterationTwo.syntaxtree.*;
import IterationTwo.SymbolTable.*;
import Temp.Label;

import frame.Frame;
import IterationTwo.symbol.Symbol;

public class IRTranslateVisitor implements IRVisitor {

	Stack<Frame> frames;
	Frame currentFrame;
	Table symbolT;
	MethodBodyTable methodT;
	ClassTable classT;
	
	public ArrayList<FragImpl> frags;
	
	Symbol className;
	Symbol methodName;
	
	
	
	public IRTranslateVisitor(SymbolTable sT, Frame currentFrame) {
		symbolT = sT;
		frags = new ArrayList<FragImpl>();
		frames = new Stack<Frame>();
		frames.push(currentFrame);
		this.currentFrame = currentFrame;
	}
	
	private Exp getVar(Symbol var){
		VarInfo vD;
		ClassTable cT = (ClassTable) symbolT.get(className);
		MethodBodyTable mBT = (MethodBodyTable) cT.mTable.get(methodName);
		
		if((vD=(VarInfo) mBT.lTable.get(var))!=null);
		else if((vD=(VarInfo) mBT.pTable.get(var))!=null);
		else vD=(VarInfo) cT.fTable.get(var);
		IterationThree.IntermediateCode.tree.exp.Exp move = vD.access.exp(new TEMP(currentFrame.FP()));
		
		return new Exp(move);
	}
	
	
	@Override
	public void visit(Program n) {
		n.m.accept(this);
        for(int i=0; i<n.cl.size();i++){
        	if(n.cl.elementAt(i) instanceof ClassDeclSimple){
        		className = Symbol.symbol(((ClassDeclSimple)n.cl.elementAt(i)).i.toString());
        	}else {
        		className = Symbol.symbol(((ClassDeclExtends)n.cl.elementAt(i)).i.toString());
        	}
        	classT = (ClassTable) symbolT.get(className);
        	n.cl.elementAt(i).accept(this);
        }
           
	}

	@Override
	public void visit(MainClass n) {
		className = Symbol.symbol("$main");
		Stm body = new EXP(n.s.accept(this).unEx());
		currentFrame = currentFrame.newFrame(className, new ArrayList<Boolean>());
		frames.push(currentFrame);
		java.util.ArrayList<Stm> l = new java.util.ArrayList<Stm>();
		l.add(body);
		currentFrame.procEntryExit1(l);
		frames.pop();
	}

	@Override
	public void visit(ClassDeclSimple n) {
		
		for(int i=0; i<n.vl.size();i++){
			
		}
		for(int i=0; i<n.ml.size();i++){
			methodName = Symbol.symbol(n.ml.elementAt(i).i.toString());
			methodT = (MethodBodyTable)classT.mTable.get(methodName);
            n.ml.elementAt(i).accept(this);
        }
		methodT = null;
	}

	@Override
	public void visit(ClassDeclExtends n) {
		for(int i=0; i<n.ml.size();i++){
			methodName = Symbol.symbol(n.ml.elementAt(i).i.toString());
			verifierMethodExtend(classT);
            n.ml.elementAt(i).accept(this);
		}
		methodT = null;	
	}
	
	private void verifierMethodExtend(ClassTable ct){
		methodT=(MethodBodyTable) ct.mTable.get(methodName);
		
		if(methodT==null)
			verifierMethodExtend((ClassTable) symbolT.get(ct.extendClass));
	}

	@Override
	public Exp visit(VarDecl n) { 
		return null;
	}

	@Override
	public void visit(MethodDecl n) {
		IterationThree.IntermediateCode.tree.stm.Stm body = new EXP(new CONST(0));
		java.util.List<Boolean> formals= new ArrayList<Boolean>();
		for (int i = 0; i <= n.fl.size(); i++)
			formals.add(true);
		
		String s = className.toString()+"$"+methodName.toString();
		currentFrame = currentFrame.newFrame(Symbol.symbol(s), formals);
		frames.push(currentFrame);
		
		java.util.ArrayList<Stm> l = new java.util.ArrayList<Stm>();
		
		for(int i=0; i<n.sl.size(); i++){
           body = new SEQ(body,new EXP(n.sl.elementAt(i).accept(this).unEx()));
		}
		frags.add(new FragImpl(currentFrame, body));
		l.add(body);	
		currentFrame.procEntryExit1(l);
		frames.pop();
	}

	@Override
	public Exp visit(Formal n) {
		VarInfo var = (VarInfo) methodT.pTable.get(Symbol.symbol(n.i.toString()));
		return new Exp(var.access.exp(new TEMP(currentFrame.FP())));
		
	}

	@Override
	public Exp visit(Block n) {
		IterationThree.IntermediateCode.tree.exp.Exp stm = new CONST(0);
		for(int i=0; i<n.sl.size(); i++)
           stm = new ESEQ(new SEQ(new EXP(stm), new EXP(n.sl.elementAt(i).accept(this).unEx())), new CONST(0));
		return new Exp(stm);	
	}

	@Override
	public Exp visit(If n) {
		Label t = new Label();//then
		Label f = new Label();//else
		       
		Exp cond = n.e.accept(this);
		Exp th = n.s1.accept(this);
		Exp el = n.s2.accept(this);
        
       
		IterationThree.IntermediateCode.tree.exp.Exp r = 
			new ESEQ(
				new SEQ(
					new CJUMP(CJUMP.GT,cond.unEx(),new CONST(0),t,f),
						new SEQ(
							new SEQ(new IterationThree.IntermediateCode.tree.stm.LABEL(t),new EXP(th.unEx())),
							new SEQ(new IterationThree.IntermediateCode.tree.stm.LABEL(f),new EXP(el.unEx()))
						))
				,        			
				new CONST(0));
		
	   return new Exp(r);
	}

	@Override
	public Exp visit(While n) {
		Label test = new Label();
        Label done = new Label();
        Label body = new Label();
        
        Exp cond = n.e.accept(this);
        Exp b = n.s.accept(this);
        
        IterationThree.IntermediateCode.tree.exp.Exp  r = new ESEQ(
        		new SEQ(new LABEL(test),
        				new SEQ(
        					new CJUMP(CJUMP.GT,cond.unEx(),new CONST(0),body,done),
        					new SEQ(new LABEL(body), new SEQ(new EXP(b.unEx()),new JUMP(test)))
        				)
        		), new CONST(0)
        );
        	
		return new Exp(r);
	}

	@Override
	public Exp visit(Print n) {
		Exp arg = n.e.accept(this);
        
        ExpList param = new ExpList(arg.unEx(), null);
        //TODO verificar nome correto pra chamada de sistema
        IterationThree.IntermediateCode.tree.exp.Exp call = currentFrame.externalCall("printInt", param);
        
        return new Exp(call);
	}

	@Override
	public Exp visit(Assign n) {
		Exp value = n.e.accept(this);
		Exp addr = getVar(Symbol.symbol(n.i.s));
			
		IterationThree.IntermediateCode.tree.exp.Exp r = new ESEQ(
							 new MOVE(addr.unEx(),value.unEx()),
							 new CONST(0));
		return new Exp(r);
	}

	@Override
	public Exp visit(ArrayAssign n) {
		Exp addr = n.i.accept(this);
		Exp index = n.e1.accept(this);
		Exp value = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp r = new ESEQ( 
							new MOVE( new MEM(
				 				new BINOP(BINOP.PLUS,addr.unEx(),
								new BINOP(BINOP.MUL,index.unEx(),new CONST(currentFrame.wordSize())))),
								value.unEx()),
							new CONST(0));
		return new Exp(r);
	}

	@Override
	public Exp visit(And n) {
		Exp op1 = n.e1.accept(this);
		Exp op2 = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp and = new BINOP(BINOP.MUL,op1.unEx(),op2.unEx());
		
		return new Exp(and);
	}

	@Override
	public Exp visit(LessThan n) {
		
		Exp op1 = n.e1.accept(this);
		Exp op2 = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp lt = 	new BINOP(BINOP.MINUS, op2.unEx(), op1.unEx());
		return new Exp(lt);
	}

	@Override
	public Exp visit(Plus n) {
		Exp op1 = n.e1.accept(this);
		Exp op2 = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp plus = new BINOP(BINOP.PLUS,op1.unEx(),op2.unEx());
		
		return new Exp(plus);
	}

	@Override
	public Exp visit(Minus n) {
		Exp op1 = n.e1.accept(this);
		Exp op2 = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp min = new BINOP(BINOP.MINUS,op1.unEx(),op2.unEx());
		
		return new Exp(min);
	}

	@Override
	public Exp visit(Times n) {
		Exp op1 = n.e1.accept(this);
		Exp op2 = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp tim = new BINOP(BINOP.MUL,op1.unEx(),op2.unEx());
		
		return new Exp(tim);
	}

	@Override
	public Exp visit(ArrayLookup n) {
		
		Exp addr = n.e1.accept(this);
		Exp index = n.e2.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp arraL= new BINOP(BINOP.PLUS,addr.unEx(),
				new BINOP(BINOP.MUL,index.unEx(),new CONST(currentFrame.wordSize())));
		return new Exp(arraL);
	}

	@Override
	public Exp visit(ArrayLength n) {
		Exp array = getVar(Symbol.symbol(((IdentifierExp)n.e).s));
		return array;
	}

	@Override
	public Exp visit(Call n) {
		IterationThree.IntermediateCode.tree.ExpList params = null;
		Exp var;
		for (int i = n.el.size()-1; i >= 0; i--) {
			
			var = n.el.elementAt(i).accept(this);
			params = new ExpList(var.unEx(),params);
		}
		var = n.e.accept(this);
		params = new ExpList(var.unEx(),params);
		
		VarInfo vD;
		Symbol s;
		ClassTable cT ;
		if(!className.equals( Symbol.symbol("$main"))){
			 cT = (ClassTable) symbolT.get(className);
			 s = Symbol.symbol(((IdentifierExp)n.e).s);
			 MethodBodyTable mBT = (MethodBodyTable) cT.get(Symbol.symbol(n.i.s));
			 if((vD=(VarInfo) mBT.lTable.get(s))!=null);
			 else if((vD=(VarInfo) mBT.pTable.get(s))!=null);
			 else vD=(VarInfo) cT.fTable.get(s);
			 s = Symbol.symbol(vD.type.toString());
		}else {
			s = Symbol.symbol(((NewObject)n.e).i.s);
		}
		
		return new Exp(new CALL(new NAME(new Label(s.toString()+"$"+n.i.toString())),params));
	}

	@Override
	public Exp visit(IntegerLiteral n) {
		IterationThree.IntermediateCode.tree.exp.Exp value = new CONST(n.i);
		Exp e = new Exp(value);
		return e;
	}

	@Override
	public Exp visit(True n) {
		return new Exp(new CONST(1));
	}

	@Override
	public Exp visit(False n) {
		return new Exp(new CONST(0));
	}

	@Override
	public Exp visit(IdentifierExp n) {
		return getVar(Symbol.symbol(n.s));
	}

	@Override
	public Exp visit(This n) {
		return new Exp(new MEM(new TEMP(currentFrame.FP())));
	}

	@Override
	public Exp visit(NewArray n) {
		
	       Exp index = n.e.accept(this);
	       
	       IterationThree.IntermediateCode.tree.exp.Exp size = new BINOP(BINOP.MUL,
	    		   						new BINOP(BINOP.PLUS,index.unEx(),new CONST(1)),
	    		   						new CONST(currentFrame.wordSize()));
	       ExpList args1 = null;
	       args1 = new ExpList(size,args1);
	       
	       IterationThree.IntermediateCode.tree.exp.Exp newArray = currentFrame.externalCall("initArray",args1);
	       
	      return new Exp(newArray);
	     
	}

	@Override
	public Exp visit(NewObject n) {
		VarInfo vD;
		Symbol s = Symbol.symbol((n.i).s);
		ClassTable cT = (ClassTable) symbolT.get(s);
		int count = cT.fTable.keys().size();
		while(cT.extendClass!=null){
			cT = (ClassTable)symbolT.get(cT.extendClass);
			count = count + cT.fTable.keys().size();
		}
		
		ExpList args1 = null;
		args1 = new ExpList(new CONST((count+1)*currentFrame.wordSize()),args1);
		
		IterationThree.IntermediateCode.tree.exp.Exp newArray = currentFrame.externalCall("malloc",args1);
	    
		return new Exp(newArray);
	}

	@Override
	public Exp visit(Not n) {
		Exp op = n.e.accept(this);
		
		IterationThree.IntermediateCode.tree.exp.Exp not = new BINOP(BINOP.MINUS,new CONST(1),op.unEx());
		
		return new Exp(not);
	}

	@Override
	public Exp visit(Identifier n) {
		return getVar(Symbol.symbol(n.s));
	}
	@Override
	public void visit(IntArrayType n){}

	@Override
	public void visit(BooleanType n) {}

	@Override
	public void visit(IntegerType n) {}

	@Override
	public void visit(IdentifierType n){}

}
