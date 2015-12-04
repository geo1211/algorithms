import java.util.*;

public class FlipCoinChain {
	static int solution(int[] A) {
		int n = A.length;
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			if (A[i] == A[i + 1]) {
				result = result + 1;
			}
		}
		int r = -2;
		for (int i = 0; i < n; i++) {
			int count = 0;
			if (i > 0) {
				if (A[i - 1] != A[i]) {
					count = count + 1;
				} else {
					count = count - 1;
				}
			}
			if (i < n - 1) {
				if (A[i + 1] != A[i]) {
					count = count + 1;
				} else {
					count = count - 1;
				}
			}
			r = Math.max(r, count);
		}
		return Math.max(0, result + r);
	}

	// java8 required
	public int postfix(String S) {
		// write your code in Java SE 8
		Stack<Object> stack = new Stack<Object>();
		try {
			for (int i = 0; i < S.length(); i++) {
				char c = S.charAt(i);
				if (c >= '0' && c <= '9') {
					stack.push(new Integer(Character.getNumericValue(c)));
				} else {
					// pop 2 operands
					int o1 = (Integer) stack.pop();
					int o2 = (Integer) stack.pop();
					int res = 0;
					if (c == '+') {
						// res = Math.addExact(o1, o2); // java8 required
					} else if (c == '*') {
						// res = Math.multiplyExact(o1, o2); // java8 required
					}
					stack.push(res);
				}
			}
			return stack.empty() ? -1 : (Integer) stack.pop();
		} catch (EmptyStackException ese) {
			return -1;
		} catch (ArithmeticException ae) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		int[] input = null;
		input = new int[] { 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0 };
		input = new int[] { 0, 0, 0, 0, 0, 0 };
		input = new int[] { 1, 1, 1 };
		System.out.println(FlipCoinChain.solution(input));
		System.out.println(Character.getNumericValue('6') * 6);
	}
}
