package Util;

import java.util.LinkedList;
import java.util.List;

public class Tree<E>
{
    public E info;
    public List<Tree<E>> children;
    
	public Tree(E info)
	{
		this(info, new LinkedList<Tree<E>>());
	}
    
	public Tree(E info, List<Tree<E>> children)
	{
		this.info = info;
		this.children = children;
	}
	
	public Tree<E> addChild(Tree<E> child)
	{
		children.add(child);
		return this;
	}

	public String prettyPrint()
	{
		return prettyPrint("").toString();
	}
	
	private StringBuilder prettyPrint(String indent)
	{
		StringBuilder builder = new StringBuilder(info.toString());

		if (! children.isEmpty())
		{
			final Tree<E> lastChild = children.get(children.size() - 1);
			for (Tree<E> t : children)
				builder
					.append('\n')
					.append(indent)
					.append("+--")
					.append(t.prettyPrint(indent + (t != lastChild ? "|  " : "   ")));
		}

		return builder;
	}
}
