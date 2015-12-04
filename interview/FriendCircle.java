import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FriendCircle {

	/*
	 * Complete the function below.
	 */

	// mark all friends of "frd" as true in "circled"
	static void recursiveTag(String[] friends, boolean[] circled, int frd) {
		circled[frd] = true; // always friend of oneself
		for (int j = frd + 1; j < friends.length; j++) {
			if (friends[frd].charAt(j) == 'Y') {
				// circled[j] = true; // friend of i
				recursiveTag(friends, circled, j);
			}
		}
	}

	static int friendCircles(String[] friends) {
		if (friends.length == 1) {
			return 1;
		}
		int res = 0;
		boolean[] circled = new boolean[friends.length];
		for (int i = 0; i < friends.length; i++) {
			if (circled[i]) { // already in a circle
				continue;
			}
			recursiveTag(friends, circled, i);
			res++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("---" + "a".substring(1) + "---");
		Scanner in = new Scanner(System.in);
		final String fileName = "/tmp/out.txt"; // System.getenv("OUTPUT_PATH");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		int res;

		int _friends_size = Integer.parseInt(in.nextLine());
		String[] _friends = new String[_friends_size];
		String _friends_item;
		for (int _friends_i = 0; _friends_i < _friends_size; _friends_i++) {
			try {
				_friends_item = in.nextLine();
			} catch (Exception e) {
				_friends_item = null;
			}
			_friends[_friends_i] = _friends_item;
		}

		res = friendCircles(_friends);
		System.out.println(res);
		// bw.write(String.valueOf(res));
		// bw.newLine();

		bw.close();
	}
}