package Symbol;

import java.util.Hashtable;
import java.util.Map;

import Util.ToTree;
import Util.Tree;

public class Symbol implements ToTree<String>
{
	private String name;

	private Symbol(String n)
	{
		name = n;
	}

	@Override
	public String toString()
	{
		return name;
	}

	private static Map<String,Symbol> dict = new Hashtable<String,Symbol>();

	/** 
	 * Make return the unique symbol associated with a string.
	 * Repeated calls to <tt>symbol("abc")</tt> will return the same Symbol.
	 */

	public static Symbol symbol(String n)
	{
		String u = n.intern(); // a canonical representation for the string
		Symbol s = dict.get(u);
		if (s == null)
		{
			s = new Symbol(u);
			dict.put(u,s);
		}
		return s;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>(name);
	}
}
