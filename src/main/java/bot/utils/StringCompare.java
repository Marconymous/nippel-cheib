package bot.utils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Locale;

public class StringCompare {
    private static boolean inRange(float v) {
        return v <= 1 && v >= 0;
    }

    private static float convert(int distance, String x, String y) {
        return 1 - ((float) distance) / (float) Math.max(x.length(), y.length());
    }

    public static boolean levenshtein(String s, String comparison, float value) {
        if (!inRange(value)) {
            throw new InvalidParameterException("The float parameter value needs to be between 1 and 0! given > " + value);
        }

        return levenshtein(s, comparison) >= value;
    }

    public static float levenshtein(String s, String comparison) {
        return convert(Levenshtein.compare(s, comparison), s, comparison);
    }


    private static class Levenshtein {
        private static int cost(char a, char b) {
            return a == b ? 0 : 1;
        }

        private static int min(int... numbers) {
            return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
        }

        public static int compare(String x, String y) {
            x = x.toLowerCase(Locale.ROOT);
            y = y.toLowerCase(Locale.ROOT);

            if (x.equals("") || y.equals("")) return 0xffffff;

            int[][] dp = new int[x.length() + 1][y.length() + 1];

            for (int i = 0; i <= x.length(); i++) {
                for (int j = 0; j <= y.length(); j++) {
                    if (i == 0) {
                        dp[i][j] = j;
                    } else if (j == 0) {
                        dp[i][j] = i;
                    } else {
                        dp[i][j] = min(dp[i - 1][j - 1] + cost(x.charAt(i - 1), y.charAt(j - 1)), dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    }
                }
            }

            return dp[x.length()][y.length()];
        }
    }
}
