package Util;

import java.util.Iterator;

class LstNode<E>
{
	public final E head;
	public final LstNode<E> tail;
	
	public LstNode(E head)
	{
		this(head, null);
	}
	
	public LstNode(E head, LstNode<E> tail)
	{
		this.head = head;
		this.tail = tail;
	}
}

public class Lst<E> implements Iterable<E>, Iterator<E>
{
	public LstNode<E> start;
	
	public Lst()
	{
		start = null;
	}
	
	public Lst(E head)
	{
		start = new LstNode<E>(head);
	}
	
	public Lst(E head, Lst<E> tail)
	{
		start = new LstNode<E>(head, tail.start);
	}
	
	private Lst(LstNode<E> start)
	{
		this.start = start;
	}
	
	public static <E> Lst<E> list(E... elements)
	{
		Lst<E> list = new Lst<E>();
		for (int i = elements.length - 1; i >= 0; i--)
			list.start = new LstNode<E>(elements[i], list.start);
		return list;
	}
	
	public Lst<E> reverse()
	{
		Lst<E> list = new Lst<E>();
		for (E x : this)
			list = new Lst<E>(x, list);
		return list;
	}
	
	public E head()
	{
		return start.head;
	}
	
	public Lst<E> tail()
	{
		return new Lst<E>(start.tail);
	}
	
	public boolean isEmpty()
	{
		return start == null;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder("Lst [");
		if (! isEmpty())
		{
			builder.append(head());	
			for (E x : tail())
				builder.append(", ").append(x.toString());
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Lst<E>(start);
	}

	@Override
	public boolean hasNext()
	{
		return start != null;
	}

	@Override
	public E next()
	{
		final E x = start.head;
		start = start.tail;
		return x;
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("LstIterator does not support the remove operation");
	}

	public static void main(String[] args)
	{
		Lst<Integer> list = new Lst<Integer>(10, new Lst<Integer>(20, new Lst<Integer>(30, new Lst<Integer>())));
		System.out.println(list);
		for (Integer x : list)
			System.out.println(x);
	}
}
