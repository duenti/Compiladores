package Types;

import Parse.Field;
import Parse.NameTy;
import Util.Lst;
import Util.Tree;

public class RECORD extends Type
{
	Lst<Field<NameTy>> fields;

	public RECORD(Lst<Field<NameTy>> fields)
	{
		this.fields = fields;
	}

	@Override
	public boolean coerceTo(Type t)
	{
		return this == t.actual();
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> tree = new Tree<String>("RECORD");
		for (Field f : fields)
		{
			Tree<String> tf = new Tree<String>("Field");
			tf.addChild(new Tree<String>(f.name.toString()));
			tf.addChild(f.toTree());
			tree.addChild(tf);
		}
		return tree;
	}
}
