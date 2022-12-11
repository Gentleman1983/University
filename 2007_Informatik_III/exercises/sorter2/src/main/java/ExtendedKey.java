//package de.luhu.infoII.b5;

/**
 * ExtendedKey implementation as specified in "Aufgabe 4"
 */
public class ExtendedKey extends SimpleKey {

	/**
	 * Position object, secondary key
	 */
	protected Comparable pos;

	/**
	 * Constructor initializing key and pos
	 * 
	 * @param key The key instance
	 * @param pos The position instance
	 */
	public ExtendedKey(Comparable key, Comparable pos) {
		super(key);
		this.pos = pos;
	}

	/**
	 * @return Return the position, secondary key
	 */
	public Comparable getPos() {
		return pos;
	}

	/**
	 * First lets the super class compare to c. If c equals
	 * the SimpleKey's key than the position will be compared.
	 * 
	 * @see SimpleKey#compareTo(Object)
	 */
	public int compareTo(Object c) {
		int res = super.compareTo(c);
		if ((res == 0) && (c instanceof ExtendedKey)) {
			return (res == 0 ? pos.compareTo(((ExtendedKey) c).getPos())
					: res);
		}

		return (res == 0 ? pos.compareTo(c) : res);
	}
}
