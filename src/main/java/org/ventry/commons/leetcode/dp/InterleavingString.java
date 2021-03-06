package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.InterleavingString
 * author: ventry
 * create: 2018/11/2 18:14
 * description:
 */

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        return judge(s1, 0, s2, 0, s3, 0);
    }

    private boolean judge(String s1, int i1,
                          String s2, int i2,
                          String s3, int i3) {
        if (i3 == s3.length()) {
            return true;
        }

        if (i2 == s2.length()) {
            return s1.charAt(i1) == s3.charAt(i3) && judge(s1, i1 + 1, s2, i2, s3, i3 + 1);
        }

        if (i1 == s1.length()) {
            return s2.charAt(i2) == s3.charAt(i3) && judge(s1, i1, s2, i2 + 1, s3, i3 + 1);
        }

        return (s1.charAt(i1) == s3.charAt(i3) && judge(s1, i1 + 1, s2, i2, s3, i3 + 1))
                || (s2.charAt(i2) == s3.charAt(i3) && judge(s1, i1, s2, i2 + 1, s3, i3 + 1));
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if ((s1.length() + s2.length()) != s3.length())
            return false;

        boolean[][] matrix = new boolean[s2.length() + 1][s1.length() + 1];
        matrix[0][0] = true;

        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = matrix[0][i - 1] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = matrix[i - 1][0] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = (matrix[i - 1][j] && (s2.charAt(i - 1) == s3.charAt(i + j - 1)))
                        || (matrix[i][j - 1] && (s1.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }

        return matrix[s2.length()][s1.length()];
    }
}
