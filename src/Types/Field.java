package Types;

import Symbol.Symbol;

public class Field
{
	public Symbol name;
	public Type type;
	
	public Field(Symbol name, Type type)
	{
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Field [name=" + name + ", type=" + type + "]";
	}
}
