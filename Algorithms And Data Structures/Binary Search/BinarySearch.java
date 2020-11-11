public class BinarySearch {

	public BinarySearch () {
		System.out.println("hey");
	}

	//Only works on sorted arrays
	public static int bnSearch(int[] array, int target) {
		int low = 0; int high = array.length-1;
		while (low <= high) {
			int middle = low + (high - low)/2;
			if (array[middle] > target)
				high = middle-1;
			else if (array[middle] < target)
				low = middle+1;
			else
				return middle;		//If target is neither greater nor smaller than array[middle], it must mean it's equal
		}
		return -1;		//If low = high and even then, target is not equal to array[middle], the next while will make low > high and the algorithm will return -1
	} 

	public static void main (String[] args) {
		/*
		int[] test = {1,3,4,9,10,11,13,14,14,17,22};
		System.out.println(bnSearch(test, 4));
		*/
	}

	/*
	PERFORMANCE
		bnSearch: lg N     -Time consumed by algorithms that divide N/2 in every iteration usually scales logarithmically
	*/

}