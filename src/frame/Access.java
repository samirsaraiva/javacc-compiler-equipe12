package frame;

import IterationThree.IntermediateCode.tree.exp.Exp;

public abstract class Access
{
    public Access()
    {
    }

    public abstract Exp exp(Exp framePtr);
}
