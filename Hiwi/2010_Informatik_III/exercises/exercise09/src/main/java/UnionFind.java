import java.util.*;

public class UnionFind {
	int[] Father;
	int NodeCount;
	
	public UnionFind(int NodeCount) {
		this.NodeCount = NodeCount+1;
		Empty();
	}
	
	public void Empty() {
		Father = new int[NodeCount];
		for (int i = 0; i < NodeCount; i++) 
			Father[i] = 0;
	}
	
	public void MakeSet(int x) {
		Father[x] = -1;
	}
	
	public void Union(int x, int y) {
		if (Father[x] < Father[y]) {
			Father[x] += Father[y];
			Father[y] = x;
		} else {
			Father[y] += Father[x];
			Father[x] = y;
		}
	}
	
	public int Find(int x) {
		if (Father[x] < 0)
			return x;
		int root = Find(Father[x]);
		Father[x] = root;
		return root;
	}
	
	@SuppressWarnings("unchecked")
	public List[] GetList() {
		List[] templist = new List[NodeCount];
		for (int i = 0; i < NodeCount; i++)
			templist[i] = new LinkedList();
		for (int i = 1; i < NodeCount; i++) {
			templist[Find(i)].add(i);
		}
		return templist;
	}
}
