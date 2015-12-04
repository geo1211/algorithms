import java.io.*;
import java.util.*;

public class StringChain {

	static int longest_chain(String[] w) {
		Map<Integer, Set<String>> wordByLen = new HashMap<Integer, Set<String>>();
		final int MAX_LEN = 50;
		for (String w1 : w) {
			int len = w1.length();
			if (!wordByLen.containsKey(len)) {
				wordByLen.put(len, new HashSet<String>());
			}
			wordByLen.get(len).add(w1);
		}

		Map<String, Integer> chainCounts = new HashMap<String, Integer>();

		for (int l = 1; l <= MAX_LEN; l++) {
			Set<String> words = wordByLen.get(l);
			Set<String> lasts = wordByLen.get(l - 1);
			if (lasts == null || lasts.size() == 0) { // broken
				if (words != null) {
					for (String word : words) {
						chainCounts.put(word, 1); // start again
					}
				}
			} else if (words != null && words.size() > 0) { // test if connected
				for (String word : words) {
					boolean connected = false;
					for (int c = 0; c < word.length(); c++) {
						String subword = getStringWithoutChar(word, c);
						if (chainCounts.containsKey(subword)) {
							connected = true;
							Integer subcount = chainCounts.get(subword);
							Integer count = chainCounts.containsKey(word) ? chainCounts.get(word) : 0;
							// compare to prev results in this loop
							chainCounts.put(word, Math.max(count, subcount + 1));
						}
					}
					if (!connected) {
						chainCounts.put(word, 1);
					}
				}
			}
		}

		int res = 0;
		for (Integer ii : chainCounts.values()) {
			if (ii > res) {
				res = ii;
			}
		}
		return res;
	}

	static String getStringWithoutChar(String str, int charIndex) {
		return str.substring(0, charIndex) + str.substring(charIndex + 1);
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		final String fileName = "/tmp/out.txt"; // System.getenv("OUTPUT_PATH");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		int res;

		int _w_size = 0;
		_w_size = Integer.parseInt(in.nextLine());
		String[] _w = new String[_w_size];
		String _w_item;
		for (int _w_i = 0; _w_i < _w_size; _w_i++) {
			try {
				_w_item = in.nextLine();
			} catch (Exception e) {
				_w_item = null;
			}
			_w[_w_i] = _w_item;
		}

		res = longest_chain(_w);
		System.out.println(res);
		// bw.write(String.valueOf(res));
		// bw.newLine();

		bw.close();
	}
}
