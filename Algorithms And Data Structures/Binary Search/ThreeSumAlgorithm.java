public class ThreeSumAlgorithm {

	//Given an array, check how many sums of 3 non-repeating numbers result in 0
	public static int threeSum(int[] array) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = i+1; j < array.length; j++) { // j=i+1 so we don't pair the same number
				int index = BinarySearch.bnSearch(array, -(array[i]+array[j])); //Search for the target that would result in a 0 sum
				if (index > -1 && index > j) {		//index > j will guarantee index != j and also prevent different combinations of the same 3 numbers
					count++;
					//System.out.println("A: "+array[i]+", B: "+array[j]+", C: "+array[index]);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		//Since the goal is not to sort the array (even though any algorithm of performance N²logN or faster would suffice), I'm already initiallizing a sorted one
		/*
		int[] test = {-40, -20, -10, 0, 5, 10, 30, 40};
		System.out.println(threeSum(test));
		*/
	}

	/*
	PERFORMANCE
		threeSum: N²logN  -As stated above, any sorting algorithm better than this would suffice
	*/

}