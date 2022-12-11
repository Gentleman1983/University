//package de.luhu.infoII.b5;

/**
 * SimpleKey implementation as specified in "Aufgabe 4"
 */
public class SimpleKey implements Comparable {

	/**
	 * Key implementation
	 */
	private Comparable key;

	/**
	 * Constructor initializing key
	 * 
	 * @param key Key object
	 */
	public SimpleKey(Comparable key) {
		super();
		this.key = key;
	}

	/**
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object c) {
		if(c instanceof SimpleKey)
			return key.compareTo(((SimpleKey)c).getKey());
		return key.compareTo(c);
	}

	/**
	 * @return Returns the key
	 */
	public Comparable getKey() {
		return key;
	}
}
