import java.util.*;

/*
 safe with 4 dials on it
 each dial is numbered 0->9, continuous, 0->9 and 9->0

 at any point, the safe will have a 4 digit number on it

 safe can only be opened in the *fewest* number of turns
 "turn" => picking *any* dial, and rotating 1 unit counter/clockwise
 0->4 => 4 turns

 starting combo -> 6429
 (blacklist) -> 8643, 7348, 8244, 8624, 2386, 6428, 8232, 6420
 opening combo -> 7624

 def open_safe(starting_combo, opening_combo, blacklist):
 return something that enables you to open the safe
 */

// TODO:  just look at the beautiful solution here:
// https://gist.github.com/leonmax/b765e7d9640cb0b6888c
// kinda like breadth first search

//def open_safe(starting, opening, blacklist):
//    # validate input
//    validate(starting)
//    validate(opening)
//    map(validate, blacklist)
//    # if opening is in blacklist, return None immediately
//    if opening in blacklist:
//        return None
//
//    # CORE ALGORITHM
//    visited = set(blacklist + [starting])
//    queue = [(starting, [starting])]
//    while queue:
//        (combo, path) = queue.pop(0)
//        if combo == opening:
//            return path
//        # if combo == 939: print([i for i in neighbors(combo)])
//        for neighbor in set(neighbors(combo)) - visited:
//            visited.add(neighbor)
//            queue.append((neighbor, path + [neighbor]))
//
//    # just for readability, if no path found, return None
//    return None

class OpenSafe {
	public void openSafe(int[] sCombo, int[] oCombo, Set<Integer> blacklist) throws Exception {
		if (blacklist.contains(Integer.parseInt(intArrToStr(oCombo)))) {
			throw new Exception("blacklist contains open combo!");
		}

		int ind = 0;
		int[] curr = new int[sCombo.length];
		System.arraycopy(sCombo, 0, curr, 0, sCombo.length);

		while (!intArrToStr(curr).equals(intArrToStr(oCombo))) {
			if (curr[ind] == oCombo[ind]) { // digit is done already
				ind = incInd(ind, curr.length);
				continue;
			}

			boolean fail = false;
			int[] next = curr;
			while (next[ind] != oCombo[ind]) {
				next = clockwise(next, ind);
				if (isBlacklisted(next, blacklist)) {
					fail = true;
					break;
				}
			}
			if (fail) { // try counterClockwise
				fail = false;
				next = curr;
				while (next[ind] != oCombo[ind]) {
					next = counterClockwise(next, ind);
					if (isBlacklisted(next, blacklist)) {
						fail = true;
						break;
					}
				}
			}

			if (!fail) {
				println("_ " + intArrToStr(curr) + " -> " + intArrToStr(next));
				curr = next;
				ind = incInd(ind, curr.length);
			} else { // can't get to target digit for "ind"
				// move the prev digit (or prev prev if can't)
				// if prev digit is already done, undone it
				boolean notUndone = true;
				int triedDigit = 0;
				while (notUndone && triedDigit < curr.length - 1) {
					notUndone = false;
					int undoInd = decInd(ind, curr.length);
					int[] tmp = clockwise(curr, undoInd);
					if (!isBlacklisted(tmp, blacklist)) {
						println("- " + intArrToStr(curr) + " -> " + intArrToStr(tmp));
						curr = tmp;
					} else {
						tmp = counterClockwise(curr, undoInd);
						if (!isBlacklisted(tmp, blacklist)) {
							println("^ " + intArrToStr(curr) + " -> " + intArrToStr(tmp));
							curr = tmp;
						} else {
							triedDigit++;
							notUndone = true; // keep going
						}
					}
				}
				if (notUndone) {
					throw new Exception("no solution"); // unlikely!
				}
			}
			// end of while
		}
	}

	static int decInd(int ind, int len) {
		ind--;
		if (ind < 0) {
			ind = len - 1;
		}
		return ind;
	}

	static int incInd(int ind, int len) {
		ind++;
		if (ind >= len) {
			ind = 0;
		}
		return ind;
	}

	static String intArrToStr(int[] arr) {
		String tmp = "";
		for (int c : arr) {
			tmp += String.valueOf(c);
		}
		return tmp;
	}

	static boolean isBlacklisted(int[] combo, Set<Integer> blacklist) throws Exception {
		Integer comboInt = Integer.parseInt(intArrToStr(combo));
		return blacklist.contains(comboInt);
	}

	static int[] clockwise(int[] combo, int i) {
		int[] res = new int[combo.length];
		System.arraycopy(combo, 0, res, 0, combo.length);
		int digit = clockwise(res[i]);
		res[i] = digit;
		return res;
	}

	static int[] counterClockwise(int[] combo, int i) {
		int[] res = new int[combo.length];
		System.arraycopy(combo, 0, res, 0, combo.length);
		int digit = counterClockwise(res[i]);
		res[i] = digit;
		return res;
	}

	static int clockwise(int digit) {
		return digit + 1 > 9 ? 0 : digit + 1;
	}

	static int counterClockwise(int digit) {
		return digit - 1 < 0 ? 9 : digit - 1;
	}

	public static void main(String[] args) {
		try {
			OpenSafe s = new OpenSafe();
			Set<Integer> blacklist = new HashSet<Integer>(Arrays.asList(8643, 7348, 8244, 8624, 2386, 6428, 8232, 6420));
			s.openSafe(new int[] { 6, 4, 2, 9 }, new int[] { 7, 6, 2, 4 }, blacklist);
			// Set<Integer> blacklist = new HashSet<Integer>(
			// Arrays.asList(9236, 8323, 9632, 2369, 2353, 3927));
			// s.openSafe(new int[] {3,9,2,6}, new int[] {9,6,3,9}, blacklist);
			println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void println(String s) {
		System.out.println(s);
	}
}
