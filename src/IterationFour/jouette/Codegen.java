package IterationFour.jouette;

import java.util.ArrayList;

import util.List;
import IterationThree.IntermediateCode.tree.exp.*;
import IterationThree.IntermediateCode.tree.stm.*;
import Temp.Label;
import Temp.Temp;
import assem.Instr;
import assem.OPER;
import frame.Frame;

public class Codegen {
	private Frame frame;
	public ArrayList<Instr> ilist;
	
	public Codegen(Frame f) {
		frame=f;
	}
	
	private void emit(assem.Instr inst) {
		ilist.add(inst);
			
	}
	
	public void munchStm(Stm s) throws Exception {
		if(s instanceof MOVE) munchMove(((MOVE)s).dst, ((MOVE)s).src);
		else if(s instanceof SEQ) munchSeq((SEQ)s);
		else if(s instanceof CJUMP) munchCJump((CJUMP)s);
		else if(s instanceof JUMP) munchJump((JUMP)s);
		else if(s instanceof EXP) munchExp(((EXP)s).exp);
		
		else munchLabel((LABEL)s);
	}
	

	private void munchJump(JUMP s) {
		emit(new assem.OPER("goto "+((NAME)s.exp).label.toString(), null, null, new List<Label>(((NAME)s.exp).label, null),null));	
	}

	private void munchCJump(CJUMP s) throws Exception {
		Temp r = munchExp(new BINOP(BINOP.MINUS,s.left,s.right));
		if(s.relop==CJUMP.EQ){
			emit(new OPER("BRANCHEQ if "+r+" = 0 goto "+s.iftrue, null,
					new List<Temp>(r,null), new List<Label>(s.iftrue, null),null));
		}
		else if(s.relop==CJUMP.GE){
			emit(new OPER("BRANCHGE if "+r+" >= 0 goto "+s.iftrue, null,
					new List<Temp>(r,null), new List<Label>(s.iftrue, null),null));
		}
		else if(s.relop==CJUMP.LT){
			emit(new OPER("BRANCHLT if "+r+ " < 0 goto "+s.iftrue, null,
					new List<Temp>(r,null), new List<Label>(s.iftrue, null),null));
		}
		else if(s.relop==CJUMP.NE){
			emit(new OPER("BRANCHNE if "+r+" != 0 goto "+s.iftrue, null,
					new List<Temp>(r,null), new List<Label>(s.iftrue, null),null));
		}else
		emit(new assem.OPER("goto "+s.iffalse.toString(), null, null, new List<Label>(s.iffalse, null),null));	
	}

	private void munchLabel(LABEL s) {
		emit(new assem.LABEL(s.label.toString()+":",s.label));
	}

	private void munchSeq(SEQ s) throws Exception {
		munchStm(s.left);
		munchStm(s.right);
	}
	
	void munchMove(Exp dst, Exp src) throws Exception {
		// MOVE(d, e)
		if (dst instanceof MEM) munchMove((MEM)dst,src);
		else if (dst instanceof TEMP && src instanceof CALL) munchMove((TEMP)dst,(CALL)src);
		else if (dst instanceof TEMP) munchMove((TEMP)dst,src);
		else if (src instanceof TEMP) munchMove(dst,(TEMP)src);
	}
	
	void munchMove(TEMP dst, Exp src) throws Exception {
		// MOVE(TEMP(t1), e) 
		Temp t =  munchExp(src); 
		emit(new assem.MOVE("MOVEA "+dst.temp+" <- "+t, dst.temp,t));
	}
	
	void munchMove(Exp dst, TEMP src) throws Exception {
		// MOVE(e, TEMP(t1)) 
		Temp t =  munchExp(dst); 
		emit(new assem.MOVE("MOVED "+t+" <- "+src.temp, t,src.temp));
	}
	
	private void munchMove(MEM dst, Exp src) throws Exception {
		// MOVE(MEM(BINOP(PLUS, e1, CONST(i))), e2)
		if (dst.exp instanceof BINOP && ((BINOP)dst.exp).binop==BINOP.PLUS
		&& ((BINOP)dst.exp).right instanceof CONST)
		{
			List<Temp> d = new List<Temp>(munchExp(((BINOP)dst.exp).left),null);
			
			List<Temp> s = new List<Temp>(munchExp(src),null);
			
			emit(new assem.OPER("STORE M["+d.get(0)+" + "+((CONST)((BINOP)dst.exp).right).value + " ] <- "+s.get(0), d, s,null, null));
		}
		
		// MOVE(MEM(BINOP(PLUS, CONST(i), e1)), e2)
		else if (dst.exp instanceof BINOP && ((BINOP)dst.exp).binop==BINOP.PLUS
		&& ((BINOP)dst.exp).left instanceof CONST)
		{
			List<Temp> d = new List<Temp>(munchExp(((BINOP)dst.exp).right),null);
			
			List<Temp> s = new List<Temp>(munchExp(src),null);
						
			emit(new assem.OPER("STORE M["+d.get(0)+" + " + ((CONST)((BINOP)dst.exp).left).value + "] <- "+s.get(0),d, s,null, new Integer(((CONST)((BINOP)dst.exp).left).value)));
			
		}
		
		// MOVE(MEM(e1), MEM(e2))
		else if (src instanceof MEM)
		{
			List<Temp> d = new List<Temp>(munchExp(dst.exp),null);
			
			List<Temp> s = new List<Temp>( munchExp(((MEM) src).exp),null);
			
			emit(new assem.OPER("MOVEM M["+d.get(0)+"] <- M["+s.get(0)+"]", d, s, null,null));
		}
		
		//MOVE(MEM(CONST(i)),e2)
		else if(dst.exp instanceof MEM && ((MEM)dst.exp).exp instanceof CONST){

			List<Temp> s = new List<Temp>(munchExp(src), null);
			List<Temp> d = new List<Temp>(frame.R0(), null);
			
			emit(new assem.OPER("STORE M["+frame.R0()+((CONST)((MEM)dst.exp).exp).value + "] <- "+s.get(0),d,s,null,new Integer(((CONST)((MEM)dst.exp).exp).value)));
		}
		
		// MOVE(MEM(e1), e2)
		else
		{
			List<Temp> d = new List<Temp>(munchExp(dst.exp),null);
			
			List<Temp> s = new List<Temp>(munchExp(src),null);
			
			emit(new assem.OPER("STORE M["+d.get(0)+" + 0] <- "+s.get(0), d, s, null,new Integer(0)));
		}
		
	}

	//TODO 
	//EXP(CALL(e,args))
	private Temp munchCall(CALL c)
	{
		return null;
		/*Temp r = munchExp(((CALL)exp.exp).func); 
		TempList l = munchArgs(0,exp.args);
		
		emit(new OPER("CALL 's0\n",calldefs,L(r,l)));*/
	}
	
	public Temp munchExp(IterationThree.IntermediateCode.tree.exp.Exp e) throws Exception {
		if(e instanceof MEM) return munchMem((MEM)e);
		if(e instanceof BINOP) return munchBinop((BINOP)e);
		if(e instanceof CONST) return munchConst((CONST)e);
		if(e instanceof CALL) return munchCall((CALL)e);
		else return munchTemp((TEMP) e);
	}
	
	private Temp munchTemp(TEMP e) {
		return e.temp;
	}

	//TODO NO LIVRO EM OPER EH PASSADA UMA EXPRESSAO E1, MAS QUEM SERIA ESTA? pag 168
	private Temp munchConst(CONST e) {
		// CONST(i)
		Temp r = new Temp();
		List<Temp> d = new List<Temp>(r,null);
		List<Temp> s = new List<Temp>(frame.R0(),null);
		
		emit(new OPER("ADDI "+r+" <- "+frame.R0()+" + "+e.value, d, s, null,null));
		return r;
	}

	private Temp munchBinop(BINOP e) throws Exception {
		// BINOP(PLUS, e1, CONST(i))
		if(e.binop == BINOP.PLUS && e.right instanceof CONST){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(e.left),null);
			
			emit(new OPER("ADDI "+r+" <- "+s.get(0)+" + " + ((CONST)e.right).value, d, s, null, new Integer(((CONST)e.right).value)));
			return r;
		}
		// BINOP(PLUS, CONST(i), e1)
		else if(e.binop == BINOP.PLUS && e.left instanceof CONST){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(e.right),null);
			
			emit(new OPER("ADDI "+r+" <- "+s.get(0)+" + " + ((CONST)e.left).value, d, s, null, new Integer(((CONST)e.left).value)));
			return r;
		}
		// BINOP(ADD, e1, e2)
		else if(e.binop == BINOP.PLUS){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(e.left),new List<Temp>(munchExp(e.right),null));
			
			emit(new OPER("ADD "+r+" <- "+s.get(0)+" + "+s.get(1), d, s, null,null));
			return r;
		}		
		// BINOP(MINUS, e1, CONST(i))
		else if(e.binop == BINOP.MINUS && e.right instanceof CONST){
				Temp r = new Temp();
				List<Temp> d = new List<Temp>(r,null);
				List<Temp> s = new List<Temp>( munchExp(e.left),null);
				
				emit(new OPER("SUBI "+r+" <- "+s.get(0)+"-" + ((CONST)e.right).value, d, s, null,new Integer(((CONST)e.right).value)));
				return r;
			}
		// BINOP(MINUS, e1, e2)
		else if(e.binop == BINOP.MINUS){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(e.left),new List<Temp>(munchExp(e.right),null));
			
			emit(new OPER("SUB "+r+" <- "+s.get(0)+" - "+s.get(1), d, s, null, null));
			return r;
		}
		
		// BINOP(MUL, e1, e2)
		else{
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(e.left),new List<Temp>(munchExp(e.right),null));
			
			emit(new OPER("MUL "+r+" <- "+s.get(0)+" * "+s.get(1), d, s, null, null));
			return r;
		}
	}

	private Temp munchMem(MEM e) throws Exception {
		// MEM(BINOP(PLUS, e1, CONST(i)))
		if(e.exp instanceof BINOP && ((BINOP)e.exp).binop == BINOP.PLUS && 
				((BINOP)e.exp).right instanceof CONST ){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(munchExp(((BINOP)e.exp).left),null);
			
			emit(new OPER("LOAD "+r+" <- M["+s.get(0)+" + "+((CONST)((BINOP)e.exp).right).value+ "]", d, s, null, new Integer(((CONST)((BINOP)e.exp).right).value)));
			return r;
		}
		// MEM(BINOP(PLUS, CONST(i), e1))
		else if(e.exp instanceof BINOP && ((BINOP)e.exp).binop == BINOP.PLUS && 
				((BINOP)e.exp).left instanceof CONST){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>( munchExp(((BINOP)e.exp).right),null);
			
			emit(new OPER("LOAD "+r+" <- M["+s.get(0)+" + "+((CONST)((BINOP)e.exp).left).value+ "]", d, s, null, new Integer(((CONST)((BINOP)e.exp).left).value)));
			return r;
		} 
		// MEM(CONST(i))
		else if(e.exp instanceof CONST){
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			List<Temp> s = new List<Temp>(frame.R0(),null);
			
			emit(new OPER("LOAD "+r+" <- M["+frame.R0()+" + "+ ((CONST)e.exp).value +"]", d, s, null, null));
			return r;
		}
		// MEM(e1)
		else{
			Temp r = new Temp();
			
			List<Temp> d = new List<Temp>(r,null);
			
			List<Temp> s = new List<Temp>(frame.R0(),new List<Temp>(munchExp(e.exp),null));
			
			emit(new OPER("LOAD "+r+" <- M["+frame.R0()+" + "+s.get(0)+"]", d, s, null,null));
			return r;
		}
		
	}

	public ArrayList<Instr> codegen(List<Stm> s) throws Exception {
		ArrayList<Instr> l;
		ilist= new ArrayList<Instr>();
		for(List<Stm> st = s; st != null; st = st.tail) munchStm(st.head);
		l=ilist;
		ilist=null;
		return l;
	}
	
}