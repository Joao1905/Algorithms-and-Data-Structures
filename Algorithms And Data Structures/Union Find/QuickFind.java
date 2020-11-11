class QuickFind {

	private int[] UFarray;

	public QuickFind (int n) {
		this.UFarray = new int[n];
		for (int i = 0; i < n; i++)
			UFarray[i] = i;
	}

	public boolean connected (int a, int b) {
		return UFarray[a] == UFarray[b];
	}

	public void union (int a, int b) {

		//It is important to get the values before because UFarray[a and b] are constantly changing throughout the loop, which would result in a bug
		int valueofa = UFarray[a];
		int valueofb = UFarray[b];

		// Take (5,0) for example
		for (int i = 0; i < UFarray.length; i++) {	//Check every value in the array
			if (UFarray[i] == valueofa) { 			//If the current value being checked is equal to the value inside index 5
				UFarray[i] = valueofb;				//We swap it for the value inside index 0
			}
		}

	}

	/*
	PERFORMANCE
	Array accesses
		Constructor: N
		Union: N
		Connected: 1
	*/

	/*
	public static void main (String[] args) {

		QuickFind myObj = new QuickFind(9);
		System.out.println(myObj.connected(0,5));
		myObj.union(0,5);
		System.out.println(myObj.connected(0,5));

	}
	*/

}