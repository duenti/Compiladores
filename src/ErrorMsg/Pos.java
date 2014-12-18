package ErrorMsg;

public class Pos
{
	public int left;
	public int right;
	
	public Pos(int left, int right)
	{
		this.left = left;
		this.right = right;
	}

	public String toString()
	{
		return String.format("%d:%d", left, right);
	}
}
