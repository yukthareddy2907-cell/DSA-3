package com.plagiarism.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextProcessor {


    private static final Set<String> STOP_WORDS =
            new HashSet<>(
                    Arrays.asList(
                            "a",
                            "an",
                            "the",
                            "is",
                            "are",
                            "was",
                            "were",
                            "to",
                            "of",
                            "in",
                            "on",
                            "for",
                            "from",
                            "and",
                            "or",
                            "but",
                            "this",
                            "that",
                            "these",
                            "those",
                            "it",
                            "its",
                            "as",
                            "with",
                            "has",
                            "have",
                            "had",
                            "can",
                            "could",
                            "will",
                            "would"
                    )
            );


    public static List<String> tokenize(
            String text
    ) {

        String cleaned =
                text
                        .toLowerCase()
                        .replaceAll(
                                "[^a-z0-9\\s]",
                                " "
                        );


        if (cleaned.trim().isEmpty()) {

            return new ArrayList<>();
        }


        return new ArrayList<>(
                Arrays.asList(
                        cleaned
                                .trim()
                                .split("\\s+")
                )
        );
    }


    public static List<String> removeStopWords(
            List<String> words
    ) {

        List<String> result =
                new ArrayList<>();


        for (String word : words) {

            if (
                    !STOP_WORDS.contains(word)
            ) {

                result.add(word);
            }
        }


        return result;
    }


    public static String stem(
            String word
    ) {

        String[] suffixes = {

                "ingly",
                "edly",
                "ing",
                "ed",
                "ly",
                "es",
                "s"

        };


        for (String suffix : suffixes) {

            if (
                    word.endsWith(suffix)
                            &&
                    word.length()
                            >
                    suffix.length() + 2
            ) {

                return word.substring(
                        0,
                        word.length()
                                -
                                suffix.length()
                );
            }
        }


        return word;
    }


    public static List<String> stemWords(
            List<String> words
    ) {

        List<String> result =
                new ArrayList<>();


        for (String word : words) {

            result.add(
                    stem(word)
            );
        }


        return result;
    }
}