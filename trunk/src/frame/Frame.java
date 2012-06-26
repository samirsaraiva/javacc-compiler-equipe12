package frame;

import java.util.ArrayList;
import java.util.LinkedList;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationThree.IntermediateCode.tree.stm.Stm;
import Temp.Label;
import Temp.Temp;
import Temp.TempMap;
import assem.Instr;
import IterationTwo.symbol.Symbol;
import util.List;

public abstract class Frame implements TempMap
{
   
	public abstract Frame newFrame(Symbol name, java.util.List<Boolean> formals);
    
    public Label name;
    
    public LinkedList<Access> formals;
    
    public abstract Access allocLocal(boolean escapes);
    
    public abstract int wordSize();
    
    public abstract Temp FP();
    
    public abstract Temp R0();
    
    public abstract IterationThree.IntermediateCode.tree.exp.Exp externalCall(String s, ExpList args);
    
    public abstract Temp RV();
    
    public abstract void procEntryExit1(java.util.List<Stm> body);
    
    public abstract void procEntryExit2(java.util.List<Instr> body);
    
    public abstract void procEntryExit3(java.util.List<Instr> body);
    
    public abstract ArrayList<Instr> codegen(List<IterationThree.IntermediateCode.tree.stm.Stm> body) throws Exception;
    
    public abstract List<Temp> registers();

}
