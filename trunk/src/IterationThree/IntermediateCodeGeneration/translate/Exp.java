package IterationThree.IntermediateCodeGeneration.translate;

public class Exp
{
	private  IterationThree.IntermediateCode.tree.exp.Exp exp;
    
    public Exp(IterationThree.IntermediateCode.tree.exp.Exp e) {
    	exp=e;
	}
    
    public IterationThree.IntermediateCode.tree.exp.Exp unEx() {
		return exp;
	}
}
