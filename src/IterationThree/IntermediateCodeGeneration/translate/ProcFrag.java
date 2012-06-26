package IterationThree.IntermediateCodeGeneration.translate;

import frame.Frame;

public class ProcFrag extends Frag
{
    public IterationThree.IntermediateCode.tree.stm.Stm body;
    public Frame frame;
    
    public ProcFrag(IterationThree.IntermediateCode.tree.stm.Stm b, Frame f)
    {
        super();
        
        body = b;
        frame = f;
    }
}
