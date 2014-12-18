package Parse;

import java.util.LinkedList;
import java.util.List;

import ErrorMsg.Pos;
import Util.Tree;


enum Operator { PLUS, MINUS, TIMES, DIV, POW, EQ, NE, LT, LE, GT, GE }

public class OpExp extends Exp
{
	public Exp left;
	public Exp right;
	public Operator op;
	
	public OpExp(Pos pos, Exp left, Exp right, Operator op)
	{
		super(pos);
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	public Tree<String> toTree()
	{
		return new Tree<String>("OpExp " + op).addChild(left.toTree()).addChild(right.toTree());
	}
	
	@Override
	public ExpTy semant()
	{
		ExpTy l = left.semant();
		ExpTy r = right.semant();
		
		switch (op)
		{
		case PLUS:
		case MINUS:
		case TIMES:
		case DIV:
		case POW:
			if (! (l.ty.coerceTo(INT) && r.ty.coerceTo(INT)))
				error("type mimatch");
			return new ExpTy(null, INT);
		
		case EQ:
		case NE:
			if (! (l.ty.coerceTo(r.ty) || r.ty.coerceTo(l.ty)))
				error("type mimatch");
			return new ExpTy(null, INT);
			
		case LT:
		case LE:
		case GT:
		case GE:
			if (!(l.ty.coerceTo(INT) && r.ty.coerceTo(INT) ||
				  l.ty.coerceTo(STRING) && r.ty.coerceTo(STRING)))
				error("type mimatch");
			return new ExpTy(null, INT);
			
		default:
			error("unexpected operator: " + op);
			return new ExpTy(null, INT);
		}
	}	
}
