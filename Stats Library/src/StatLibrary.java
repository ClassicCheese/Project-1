
//Let's off with a warmup :: Let's write a program that can find mean median and mode.
import java.util.Arrays;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StatLibrary {
	// Something that's common in all stats classes that you take, is learning about
	// Central Tendancies

	public StatLibrary() {

	}

	private int value;
	private int count;

	public StatLibrary(int num, int count) {
		this.value = num;
		this.count = count;
	}
	// Mean, Median, and Mode
	// The list must be in order, so order the luist before finding median. Should
	// beable to call collections sort or arraylist sort of some kind
	// Mean? ~> It's sum of elements divided by count. Another word for it could be
	// expected.

	// This method find the mean.
	public double findMean(int[] input) { // 2 options, using an array or an array list.
		// Find the sum
		double sum = 0;
		for (int c : input) {
			sum += c;
		}

		double result = sum / (double) input.length;

		return result;
	}

	// Mdedian? ~> Either the middle number if ood, or "average" of the two middlest
	// numbers
	// This method searches through an array and find the middle value of a sorted
	// array.
	public double findMedian(int[] arr) {

		Arrays.sort(arr);
		double mid;
		if (arr.length % 2 == 0) {
			int mid1 = arr.length / 2;
			int mid2 = (arr.length / 2) + 1;
			mid = ((arr[mid1] + arr[mid2]) / 2.0);
			return mid;
		}
		return arr[arr.length / 2];
	}

	// Mode? ~> Is the number that occurs the most, but it is unique, meaning there
	// can only be one number be mode
	// This method searches through an array and find the mode if it exists.
	public int findMode(int[] arr) {
		ArrayList<StatLibrary> counter = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int tempHolder = arr[i];
			boolean found = false;
			for (StatLibrary ele : counter) {
				if (ele.getValue() == tempHolder) {
					ele.increaseCount();
					found = true;
					break;
				}
			}

			if (!found) {
				counter.add(new StatLibrary(tempHolder, 1));
			}
		}

		// This little code determine whether there are multilple "mode"
		int maxCount = 0;
		int mode = 0;
		boolean multipleMode = false;
		for (StatLibrary ele : counter) {
			if (ele.getCount() > maxCount) {
				maxCount = ele.getCount();
				mode = ele.getValue();
				multipleMode = false;
			} else if (ele.getCount() == maxCount) {
				multipleMode = true;
			}
		}

		if (multipleMode) {
			return -1;
		} else
			return mode;
	}

	public int getCount() {
		return count;
	}

	public int getValue() {
		return value;
	}

	public void increaseCount() {
		count++;
	}

	// Factorial method for BigInteger
	public BigInteger factorial(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative value");
		}
		BigInteger sum = BigInteger.ONE;
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i.add(BigInteger.ONE)) {
			sum = sum.multiply(i);
		}
		return sum;
	}

	// This one is for long
	public long factorial(long n) {
		if (n == 0) {
			throw new IllegalArgumentException("Factorial is ont defined for negative value");
		}
		long sum = 1;
		for (int i = 2; i <= n; i++) {
			sum *= i;
		}
		return sum;
	}

	// This method combine the two arrays with values from both arrays.
	public ArrayList unionOf(int[] S, int[] A) {
		Arrays.sort(S);
		Arrays.sort(A);
		ArrayList<Integer> result = new ArrayList<>();

		for (int ele : S) {
			if (!result.contains(ele)) {
				result.add(ele);
			}
		}

		for (int ele : A) {
			if (!result.contains(ele)) {
				result.add(ele);
			}
		}
		// System.out.println(result);
		return result;
	}

	// This method find the common values in S and A arrays
	public ArrayList intersectionOf(int[] S, int[] A) {
		ArrayList<Integer> result = new ArrayList<>();
		Arrays.sort(S);
		Arrays.sort(A);
		for (int ele : S) {
			for (int val : A) {
				if (ele == val) {
					result.add(ele);
				}
			}
		}
		// System.out.println(result);
		return result;
	}

	// This method take the complement of the array S which is a subset of the array
	// A
	public ArrayList complement(int[] S, int[] A) {
		Arrays.sort(S);
		Arrays.sort(A);
		ArrayList<Integer> result = new ArrayList<>();
		List<Integer> set4 = Arrays.asList(Arrays.stream(A).boxed().toArray(Integer[]::new));

		for (int ele : S) {
			if (!set4.contains(ele)) {
				if (!result.contains(ele)) {
					result.add(ele);
				}
			}

		}
		return result;
	}

	// This would find the sample variance from chapter 1
	public double findSampleVariance(int[] data) {
		double mean = findMean(data);
		double sum = 0;
		for (int c : data) {
			double difference = c - mean;
			sum += Math.pow(difference, 2);
		}
		int n = data.length;
		return (sum / (n - 1));
	}

	// This would find the Standard deviation.
	public double FindStandardDeviation(int[] data) {
		double s = findSampleVariance(data);
		return Math.sqrt(s);
	}

	// It's just multiplication so IDK.
	public double findMNRule(double m, double n) {
		return m * n;
	}

	// Permutation method
	public double findPermutation(int n, int r) {
		return factorial(n) / (double) factorial(n - r);
	}

	// This method uses the previously created permutation method to solve the
	// combination formula
	public double findCombination(int n, int r) {
		double temp = findPermutation(n, r);
		return temp / factorial(r);
	}

	// If given P(A), P(B), and either P(A|B), P(B|A), or P(A and B)
	// Else IDK how I can figure this out, unless I do conditional probability
	// and as for P(A and B), I would need to know if A and B are
	// dependent/independent
	// to calculate it
	public boolean isDependent(double A, double B, double condition) {
		if (A == condition || B == condition || (A * B) == condition) {
			return false;
		}
		return true;
	}

	// How many input do I need?
	// I think I need at least 4, P(A), P(B), P(A|B) OR P(B|A), and the dependency
	// Else I can't really know how to calculate the numerator and denominator
	// Formula would be P(A and B) / P(B)
	public double findConditional(double A, double B, double pGiven) {
		if (isDependent(A, B, pGiven)) {
			return (pGiven * A) / B;
		} else {
			return A;
		}
	}

	// Multiplicative law of probability that calculate P(A and B)
	// Given that the parameters are either (P(A) and (PB))
	// or (P(A) and P(B|A)) or vise versa,
	// it doesn't matter if they're dependent or not
	// They both parameters are (one probabilty * another probability)
	public double findMultiLaw(double p1, double p2) {
		return p1 * p2;
	}

	// Additive law of probability method calculate P(A U B)
	// Given parameters P(A), P(B), P(A and B), and dependency status
	public double findAddLaw(double A, double B, double pAnd) {
		return A + B - pAnd;
	}

	// In case that A and B are mutually exclusive, P(A and B) = 0
	public double findAddLaw(double A, double B) {
		return A + B;
	}

	// Complement method
	// This method would find P(A) by subtracting P(A') from 1
	// Given just P(A') and assuming number of runs/trials is n = 1
	public double findComplement(double B) {
		return 1 - B;
	}

	// Given P(A) and n = the number of runs/trials

	public double findComplement(double B, int n) {
		return (1 - Math.pow(B, n));
	}
	// End of testing
	// End of testing

	// Bayes theorem method
	// Method is designed if and only if given these specs
	// The first array consist of P(A|B[i]) for all i
	// Second array is P(B[i]) values for all B
	// n is the vale/index that you're looking for
	public double findBayes(double[] pGiven, double[] B, int n) {
		double sum = 0;
		for (int i = 0; i < pGiven.length; i++) {
			sum += pGiven[i] * B[i];
		}
		double num = pGiven[n - 1] * B[n - 1];
		return num / sum;
	}

	// This method find the expected and variance value of a random variable
	// Im not sure about this one, it have a sigma symbol
	// But only the bottom variable is included
	public double findExpectedRandom(double[] y, double pY) {
		double sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += y[i] * pY;
		}
		return sum;
	}

	// This method calculate the variance of a random variable
	public double findVarianceRandom(double Expected, double[] y, double pY) {
		double sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += Math.pow((y[i] - Expected), 2) * pY;
		}
		return sum;
	}

	// This method find the binomial distribution of the given values
	public double findBinomialDistr(int n, int y, double p) {
		double combination = findCombination(n, y);
		return combination * Math.pow(p, y) * Math.pow(1 - p, n - y);
	}

	// Find the expected value of the binomial distribution
	public double findExpectedBinomial(int n, double p) {
		return n * p;
	}

	// Find the variance of the binomial distribution
	public double findVarianceBinomial(int n, double p) {
		return n * p * (1 - p);
	}

	// This method find the geometric distribution, given q, p, and y
	public double findGeometricDistr(double p, int n) {
		return Math.pow(1 - p, n - 1) * p;
	}

	// This find the expected value of geometric distribution
	public double findExpectedGeoDistri(double p) {
		return 1 / p;
	}

	// This method find the variance of the geometric distribution
	public double findVarianceGeoDistr(double p) {
		return (1 - p) / Math.pow(p, 2);
	}

	// This methods are the shortcuts that you mentioned
	public double findGeoONorBefore(double p, int n) {
		return 1 - Math.pow(1 - p, n);
	}

	public double findGeoBefore(double p, int n) {
		return 1 - Math.pow(1 - p, n - 1);
	}

	public double findGeoONorAfter(double p, int n) {
		return Math.pow(1 - p, n - 1);
	}

	public double findGeoAfter(double p, int n) {
		return Math.pow(1 - p, n);
	}
	// End of shortcut methods

	// This method calculate the hypergeometric probability distribution
	public double findHGD(int r, int y, int N, int n) {
		double comb1 = findCombination(r, y);
		double comb2 = findCombination(N - r, n - y);
		double denomi = findCombination(N, n);
		return (comb1 * comb2) / denomi;
	}

	// This find the exptected value for hypergeometric distribution
	public double findExpectedHGD(int n, int r, int N) {
		return (n * r) / (double) N;
	}

	// This method find the variance of HGD
	public double findVarianceHGD(int n, int r, double N) {
		return n * (r / N) * ((N - r) / N) * ((N - n) / (N - 1));
	}

	// Need testing
	// This method is negative binomial distribution
	// Given those parameters
	public double findNBD(int y, int r, double p) {
		double comb = findCombination(y - 1, r - 1);
		return comb * Math.pow(p, r) * Math.pow(1 - p, y - r);
	}

	// Expected and variance methods for NBD
	public double findExpectedNBD(int r, double p) {
		return r / p;
	}

	public double findVarianceNBD(int r, double p) {
		return (r * (1 - p)) / Math.pow(p, 2);
	}

	// This would just for the mean median and mode, just to display the array
	public static void printArray(double[] pB) {
		for (double c : pB) {
			System.out.print(c + ", ");
		}
		System.out.println();
	}

}
