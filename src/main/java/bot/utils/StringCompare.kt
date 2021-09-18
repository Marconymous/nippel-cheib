package bot.utils

import bot.utils.StringCompare
import java.security.InvalidParameterException
import java.util.Arrays
import java.util.Locale

object StringCompare {
    private fun inRange(v: Float): Boolean {
        return v <= 1 && v >= 0
    }

    private fun convert(distance: Int, x: String, y: String): Float {
        return 1 - distance.toFloat() / Math.max(x.length, y.length).toFloat()
    }

    fun levenshtein(s: String, comparison: String, value: Float): Boolean {
        if (!inRange(value)) {
            throw InvalidParameterException("The float parameter value needs to be between 1 and 0! given > $value")
        }
        return levenshtein(s, comparison) >= value
    }

    fun levenshtein(s: String, comparison: String): Float {
        return convert(Levenshtein.compare(s, comparison), s, comparison)
    }

    private object Levenshtein {
        private fun cost(a: Char, b: Char): Int {
            return if (a == b) 0 else 1
        }

        private fun min(vararg numbers: Int): Int {
            return Arrays.stream(numbers).min().orElse(Int.MAX_VALUE)
        }

        fun compare(x: String, y: String): Int {
            var x = x
            var y = y
            x = x.toLowerCase(Locale.ROOT)
            y = y.toLowerCase(Locale.ROOT)
            if (x == "" || y == "") return 0xffffff
            val dp = Array(x.length + 1) { IntArray(y.length + 1) }
            for (i in 0..x.length) {
                for (j in 0..y.length) {
                    if (i == 0) {
                        dp[i][j] = j
                    } else if (j == 0) {
                        dp[i][j] = i
                    } else {
                        dp[i][j] = min(
                            dp[i - 1][j - 1] + cost(
                                x[i - 1], y[j - 1]
                            ), dp[i - 1][j] + 1, dp[i][j - 1] + 1
                        )
                    }
                }
            }
            return dp[x.length][y.length]
        }
    }
}