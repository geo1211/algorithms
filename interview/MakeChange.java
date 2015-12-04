public class MakeChange {

	static int[] dupArray(int[] arr) {
		int[] res = new int[arr.length];
		System.arraycopy(arr, 0, res, 0, arr.length);
		return res;
	}

	static void printArray(int[] res) {
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
			if (i < res.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	public void makeChange1(int amount, int[] coins, int[] res, int offset) {
		if (amount == 0) {
			printArray(res);
			return;
		} else if (offset == coins.length) {
			return; // no solution
		}
		// find biggest < amount to use
		int c = offset;
		for (; c < coins.length; c++) {
			if (amount < coins[c]) {
				continue;
			} else {
				break;
			}
		}
		// two cases: use biggest coin, or not
		// not use biggest (only if not 1 coin left)
		makeChange1(amount, coins, dupArray(res), c + 1);
		// use biggest
		res[c]++;
		makeChange1(amount - coins[c], coins, res, c);
	}
}
