import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stevefoo
 */
public class sub_seq_matching {

    public sub_seq_matching() {

    }

    public void matching(String one, String two) {
        sub_seq[][] matching = new sub_seq[one.length()][two.length()];
        LinkedList<sub_seq> sequence = new LinkedList<>();
        for (int r = 0; r < one.length(); r++) {
            for (int c = 0; c < two.length(); c++) {
                matching[r][c] = new sub_seq(r, c, one.charAt(r), two.charAt(c));
                if (r == 0) {
                    if (c == 0 && one.charAt(r) == two.charAt(c)) {//deals with match m[0][0]
                        matching[r][c].value = 1;
                        matching[r][c].changed = true;
                        sequence.add(matching[r][c]);
                    } else if (c == 0) {//if no match at m[0][0] then it's 0;
                        matching[r][c].value = 0;
                    } else if (one.charAt(r) == two.charAt(c)) {//if there is a match on the first row
                        if (matching[r][c].value == 0) {
                            matching[r][c].value = 1;
                            matching[r][c].changed = true;
                            sequence.add(matching[r][c]);
                        } else //it's already been matched
                        {
                            matching[r][c].value = matching[r][c - 1].value;
                        }
                    } else {
                        matching[r][c].value = matching[r][c - 1].value;
                    }
                } else if (c == 0) {
                    if (one.charAt(r) == two.charAt(c)) {//if there is a match in the first column
                        matching[r][c].value = 1;
                        matching[r][c].changed = true;
                        sequence.add(matching[r][c]);
                    } else//if there is no match in the first column
                    {
                        matching[r][c].value = matching[r - 1][c].value;
                    }
                } else {
                    if (one.charAt(r) == two.charAt(c)) {//if there is match anywhere. increment diagnol square.
                        matching[r][c].value = matching[r - 1][c - 1].value + 1;
                        matching[r][c].changed = true;
                        sequence.add(matching[r][c]);
                    } else {
                        matching[r][c].value = Math.max(matching[r - 1][c].value, matching[r][c - 1].value);
                    }
                }

            }
        }
        System.out.println("The largest subsequence is " + matching[one.length() - 1][two.length() - 1].value + ": ");
        find_sequence(sequence, matching[one.length() - 1][two.length() - 1].value);
        System.out.println("\n");

    }

    private static void find_sequence(LinkedList<sub_seq> seq, int large_val) {
        String word = "";
        sub_seq temp = seq.pollLast();
        while (temp.value != large_val) {
            temp = seq.pollLast();
        }
        word = temp.r_letter + word;
        while (!seq.isEmpty()) {
            if (temp.value == (seq.peekLast().value + 1) && temp.row >= seq.peekLast().row && temp.column >= seq.peekLast().column) {
                temp = seq.pollLast();
                word = temp.r_letter + word;
            } else {
                seq.pollLast();
            }
        }
        System.out.print(word);

    }
}
