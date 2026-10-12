package com.plagiarism.demo.algorithm;

public class RabinKarpAlgorithm {

    private static final long PRIME =
            1000000007L;

    private static final int BASE =
            256;


    public static int search(
            String text,
            String pattern
    ) {

        int n = text.length();

        int m = pattern.length();


        if (m == 0) {

            return 0;
        }


        if (m > n) {

            return -1;
        }


        long patternHash = 0;

        long textHash = 0;

        long highestPower = 1;


        for (
                int i = 0;
                i < m - 1;
                i++
        ) {

            highestPower =
                    (
                            highestPower
                                    *
                            BASE
                    ) % PRIME;
        }


        for (
                int i = 0;
                i < m;
                i++
        ) {

            patternHash =
                    (
                            patternHash * BASE
                                    +
                            pattern.charAt(i)
                    ) % PRIME;


            textHash =
                    (
                            textHash * BASE
                                    +
                            text.charAt(i)
                    ) % PRIME;
        }


        for (
                int i = 0;
                i <= n - m;
                i++
        ) {

            if (
                    patternHash ==
                            textHash
            ) {

                boolean match = true;


                for (
                        int j = 0;
                        j < m;
                        j++
                ) {

                    if (
                            text.charAt(i + j)
                                    !=
                            pattern.charAt(j)
                    ) {

                        match = false;

                        break;
                    }
                }


                if (match) {

                    return i;
                }
            }


            if (i < n - m) {

                textHash =
                        (
                                BASE *
                                        (
                                                textHash
                                                        -
                                                text.charAt(i)
                                                        *
                                                highestPower
                                        )
                                        +
                                text.charAt(i + m)
                        ) % PRIME;


                if (textHash < 0) {

                    textHash += PRIME;
                }
            }
        }


        return -1;
    }
}