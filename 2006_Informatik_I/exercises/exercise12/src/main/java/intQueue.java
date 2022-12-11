public class intQueue {
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

    public intQueue() {
	head = new intNode();
    }
    public void enter(int x) {
	intNode n = new intNode( x, head.getNext());
	head.setNext(n);
    }
    public void leave() {
	intNode l = head;
	while (l.getNext().getNext() != null)
	    l = l.getNext();
	l.setNext(null);
    }
    public int front() { 
	intNode l = head;
	while (l.getNext() != null)
	    l = l.getNext();
	return l.getElement();
    }
    public boolean is_empty() {
	return (head.getNext() == null);
    }

    // eine einfache Testumgebung: 
    // gelesene Zahlen in Warteschlange stellen und wieder entnehmen
    public static void main(String args[]) {
	intQueue q = new intQueue();
	for (int i = 0; i < args.length; ++i)
	    q.enter(Integer.parseInt(args[i]));
	while ( ! q.is_empty()) {
	    System.out.println(q.front());
	    q.leave();
	}
    }	
}
