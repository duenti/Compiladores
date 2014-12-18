package ErrorMsg;


public class ErrorMsg
{
	private LineList linePos = new LineList(-1, null);
	private int lineNum = 1;
	private String filename;
	public boolean anyErrors;

	public ErrorMsg(String f)
	{
		filename = f;
	}

	public void newline(int pos)
	{
		lineNum++;
		linePos = new LineList(pos, linePos);
	}

	public void error(int left, int right, String msg)
	{
		error(new Pos(left, right), msg);
	}
	
	public void error(Pos pos, String msg)
	{
		anyErrors = true;
		System.out.println(filename + ":" + sayPosition(pos.left) + "-" + sayPosition(pos.right) + ": " + msg);
	}
	
	private String sayPosition(int pos)
	{
		int n = lineNum;
		LineList p = linePos;
		String sayPos = "0.0";

		while (p != null)
		{
			if (p.head < pos)
			{
				sayPos = String.valueOf(n) + "." + String.valueOf(pos - p.head);
				break;
			}
			p = p.tail;
			n--;
		}
		return sayPos;
	}
}

class LineList
{
	int head;
	LineList tail;
	
	LineList(int h, LineList t)
	{
		head=h;
		tail=t;
	}
}