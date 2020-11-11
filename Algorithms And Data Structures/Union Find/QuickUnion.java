//package packages; //Enable to import in assignments

public class QuickUnion {

	private int[] QUarray;
	private int[] Size;				//This is a feature of Weighted quick union (performance improvement)

	public QuickUnion (int n) {
		this.QUarray = new int[n];
		this.Size = new int[n];
		for (int i = 0; i < n; i++) {
			QUarray[i] = i;
			Size[i] = 1;
		}
	}

	public int getRoot (int a) {
		while (a != QUarray[a]) {
			QUarray[a] = QUarray[QUarray[a]]; //Path compression (performance improvement). While we are searching the root, make every node point to its grandparent to flatten the tree even more
			a = QUarray[a];
		}
		return a;
	}

	public boolean connected (int a, int b) {
		return getRoot(a) == getRoot(b);
	}

	public void union (int a, int b) {
		int rootA = getRoot(a);
		int rootB = getRoot(b);
		QUarray[rootA] = rootB;
	}

	public void weightedUnion (int a, int b) {
		int rootA = getRoot(a);
		int rootB = getRoot(b);
		if (rootA == rootB)
			return;
		//Always put the smaller tree below the larger one to flatten the trees and speed up getRoot() method 
		if (Size[rootA] < Size[rootB]) {
			QUarray[rootA] = rootB;			
			Size[rootB] += Size[rootA];
		} else {
			QUarray[rootB] = rootA;
			Size[rootA] += Size[rootB];
		}
		
	}

	public int getNodeValue (int n) {
		return QUarray[n];
	}

	/*
	PERFORMANCE
	Union Array accesses (worst case)
		Constructor: N
		Union: N
		Connected: N
	Weighted Union Array accesses
		Constructor: N
		Union: lg N
		Connected: lg N
	Weighted Union with path compression Array accesses
		Constructor: N
		Union: lg* N   -Think of lg* N as a number less than 5 (practically it represents linear time)
		Connected: lg* N
	*/
	
	public static void main (String[] args) {

		/*
		QuickUnion myObj = new QuickUnion(10);
		System.out.println(myObj.connected(1, 2));
		myObj.union(4, 3);
		myObj.union(3, 8);
		myObj.union(6, 5);
		myObj.union(9, 4);
		myObj.union(2, 1);

		System.out.println(myObj.connected(1, 2));
		myObj.union(5, 0);
		myObj.union(7, 2);
		myObj.union(6, 1);
		myObj.union(7, 3);

		for (int i : myObj.QUarray)
			System.out.print(i+" ");
		System.out.print("\n");

		QuickUnion myObj2 = new QuickUnion(10);
		System.out.println(myObj2.connected(1, 2));
		myObj2.weightedUnion(4, 3);
		myObj2.weightedUnion(3, 8);
		myObj2.weightedUnion(6, 5);
		myObj2.weightedUnion(9, 4);
		myObj2.weightedUnion(2, 1);

		System.out.println(myObj2.connected(1, 2));
		myObj2.weightedUnion(5, 0);
		myObj2.weightedUnion(7, 2);
		myObj2.weightedUnion(6, 1);
		myObj2.weightedUnion(7, 3);

		for (int i : myObj2.QUarray)
			System.out.print(i+" ");

		*/
	}

}

