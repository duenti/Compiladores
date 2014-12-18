package Parse;

import ErrorMsg.ErrorMsg;
import ErrorMsg.Pos;
import Types.Type;
import Util.ToTree;

public abstract class Absyn implements ToTree<String>
{
	Pos pos;
	
	public Absyn(Pos pos)
	{
		this.pos = pos;
	}

	public void error(String msg)
	{
		error(pos, msg);
	}

	public void error(Pos pos, String msg)
	{
		e.error(pos, msg);
	}
	
	public static final Env env = new Env();
	
	public static final Type INT = new Types.INT();
	public static final Type STRING = new Types.STRING();
	public static final Type NIL = new Types.NIL();
	public static final Type VOID = new Types.VOID();
	
	public static ErrorMsg e = new ErrorMsg("-");
}
