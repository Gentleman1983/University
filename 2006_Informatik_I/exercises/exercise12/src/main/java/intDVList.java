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

public class intDVList {
    private intNode head = null; // Listenkopf, element irrelevant
    public intDVList () { head = new intNode(); }
    public void addFirst (int x) { // neuen Knoten vorn einfuegen
	intNode n = new intNode( x, head.getNext());
	head.setNext(n);
    }
    public void addLast(int x) {
	intNode l = head;
	while ( l.getNext() != null) // letzten Knoten suchen ..
	    l = l.getNext(); // .. und dahinter einfuegen:
	intNode n = new intNode (x, null);
	l.setNext(n);
    }
    public int getFirst() { 
	return head.getNext().getElement();
    }
    public int getLast() { 
	intNode l = head;
	while (l.getNext() != null)
	    l = l.getNext();
	return l.getElement();
    }
    public int removeFirst() {
	int x = head.getNext().getElement();
	head.setNext(head.getNext().getNext());
	return x;
    }
    public int removeLast() {
	intNode l = head;
	while (l.getNext().getNext() != null)
	    l = l.getNext();
	int x = l.getNext().getElement();
	l.setNext(null);
	return x;
    }
    public int size() {
	int s = 0;
	intNode n = head;
	while (n.getNext() != null) {
	    s++;
	    n = n.getNext();
	}
	return s;
    }
    // eine einfache Testumgebung: 
    // gelesene Zahlen vorn einfuegen und von dort alle wieder auslesen
    public static void main(String args[]) {
	intDVList list = new intDVList();
	for (int i = 0; i < args.length; ++i)
	    list.addFirst(Integer.parseInt(args[i]));
	while ( list.size() != 0)
	    System.out.println(list.removeFirst());
    }	
}

