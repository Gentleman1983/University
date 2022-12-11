public class intStack {
    class intNode{
	int element;
	intNode next; 
	
	public intNode () { 
	    next = null;
	}
	public intNode (int x, intNode n) { 
	    element = x; 
	    next = n;
	}
	public void setElement (int x) { 
	    element = x;
	}
	public int getElement () { 
	    return element;
	}
	public void setNext (intNode n) { 
	    next = n;
	}
	public intNode getNext () { 
	    return next;
	}
    } 

    private intNode head = null;

    public intStack() {
	head = new intNode();
    }
    public void push(int x) {
	intNode n = new intNode( x, head.getNext());
	head.setNext(n);
    }
    public void pop() {
	head.setNext(head.getNext().getNext());
    }
    public int top() {
	return head.getNext().getElement();
    }
    public boolean is_empty() {
	return (head.getNext() == null);
    }
    // eine einfache Testumgebung: 
    // gelesene Zahlen einfuegen und wieder alle entnehmen
    public static void main(String args[]) {
	intStack s = new intStack();
	for (int i = 0; i < args.length; ++i)
	    s.push(Integer.parseInt(args[i]));
	while ( !s.is_empty()) {
	    System.out.println(s.top());
	    s.pop();
	}
    }	
}
