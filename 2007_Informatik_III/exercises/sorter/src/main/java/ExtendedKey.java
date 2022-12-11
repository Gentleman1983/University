public class ExtendedKey extends SimpleKey 
{
    protected Comparable pos;
    
    public ExtendedKey(Comparable key, Comparable pos) 
    {
        super(key);
	this.pos = pos;
    }

    public Comparable getPos() 
    {
        return pos;
    }

    public int compareTo(Object o) 
    {
        int res = super.compareTo(o);
	if((res == 0) && (o instanceof ExtendedKey)) 
        {
            return(res == 0 ? pos.compareTo(((ExtendedKey) o).getPos()): res);
	}
        return(res == 0 ? pos.compareTo(o) : res);
    }
}
