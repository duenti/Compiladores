package Parse;

import Types.Type;

public class ExpTy
{
	Translate.Exp exp;
	Type ty;
	
	public ExpTy(Translate.Exp exp, Type ty)
	{
		this.exp = exp;
		this.ty = ty;
	}

	@Override
	public String toString()
	{
		return "ExpTy [exp=" + exp + ", ty=" + ty + "]";
	}
}
