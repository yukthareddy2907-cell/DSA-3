package com.plagiarism.demo.algorithm;

public class EditDistance {


    public static int calculate(
            String first,
            String second
    ) {

        int n = first.length();

        int m = second.length();


        int[][] dp =
                new int[n + 1][m + 1];


        for (
                int i = 0;
                i <= n;
                i++
        ) {

            dp[i][0] = i;
        }


        for (
                int j = 0;
                j <= m;
                j++
        ) {

            dp[0][j] = j;
        }


        for (
                int i = 1;
                i <= n;
                i++
        ) {

            for (
                    int j = 1;
                    j <= m;
                    j++
            ) {

                if (
                        first.charAt(i - 1)
                                ==
                        second.charAt(j - 1)
                ) {

                    dp[i][j] =
                            dp[i - 1][j - 1];

                } else {

                    int insert =
                            dp[i][j - 1];

                    int delete =
                            dp[i - 1][j];

                    int replace =
                            dp[i - 1][j - 1];


                    dp[i][j] =
                            1 +
                            Math.min(
                                    insert,
                                    Math.min(
                                            delete,
                                            replace
                                    )
                            );
                }
            }
        }


        return dp[n][m];
    }


    public static double similarity(
            String first,
            String second
    ) {

        int distance =
                calculate(
                        first,
                        second
                );


        int maxLength =
                Math.max(
                        first.length(),
                        second.length()
                );


        if (maxLength == 0) {

            return 100.0;
        }


        double similarity =
                (
                        1.0
                                -
                        (
                                (double) distance
                                        /
                                maxLength
                        )
                ) * 100;


        return Math.round(
                similarity * 100
        ) / 100.0;
    }
}