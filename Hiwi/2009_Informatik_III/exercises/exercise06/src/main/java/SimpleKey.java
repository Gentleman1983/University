public class SimpleKey implements Comparable
{
    private Comparable key;

    public SimpleKey(Comparable key)
    {
        super();
	this.key = key;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(Object o)
    {
	if(o instanceof SimpleKey)
        {
            return key.compareTo(((SimpleKey)o).getKey());
        }
	return key.compareTo(o);
    }

    public Comparable getKey()
    {
        return key;
    }
}
