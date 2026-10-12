package com.plagiarism.demo.service;

import com.plagiarism.demo.algorithm.EditDistance;
import com.plagiarism.demo.algorithm.KMPAlgorithm;
import com.plagiarism.demo.algorithm.RabinKarpAlgorithm;

import com.plagiarism.demo.model.PlagiarismResponse;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PlagiarismService {


    public PlagiarismResponse analyze(
            String submittedText,
            String referenceText
    ) {


        // --------------------------------
        // 1. TOKENIZATION
        // --------------------------------

        List<String> submittedTokens =
                TextProcessor.tokenize(
                        submittedText
                );


        List<String> referenceTokens =
                TextProcessor.tokenize(
                        referenceText
                );


        // --------------------------------
        // 2. STOP WORD REMOVAL
        // --------------------------------

        List<String> submittedWords =
                TextProcessor.removeStopWords(
                        submittedTokens
                );


        List<String> referenceWords =
                TextProcessor.removeStopWords(
                        referenceTokens
                );


        // --------------------------------
        // 3. STEMMING
        // --------------------------------

        submittedWords =
                TextProcessor.stemWords(
                        submittedWords
                );


        referenceWords =
                TextProcessor.stemWords(
                        referenceWords
                );


        // --------------------------------
        // 4. MATCHING WORDS
        // --------------------------------

        Set<String> referenceSet =
                new HashSet<>(
                        referenceWords
                );


        List<String> matchingWords =
                new ArrayList<>();


        for (
                String word :
                submittedWords
        ) {

            if (
                    referenceSet.contains(word)
                            &&
                    !matchingWords.contains(word)
            ) {

                matchingWords.add(word);
            }
        }


        // --------------------------------
        // 5. WORD SIMILARITY
        // --------------------------------

        double wordSimilarity = 0;


        if (!submittedWords.isEmpty()) {

            wordSimilarity =
                    (
                            (double)
                                    matchingWords.size()
                                    /
                            submittedWords.size()
                    ) * 100;
        }


        // --------------------------------
        // 6. CREATE REFERENCE STRING
        // --------------------------------

        String referenceString =
                String.join(
                        " ",
                        referenceWords
                );


        List<String> matchingPhrases =
                new ArrayList<>();


        int kmpMatches = 0;

        int rabinKarpMatches = 0;


        // --------------------------------
        // 7. KMP + RABIN-KARP
        // --------------------------------

        for (
                int i = 0;
                i + 2 < submittedWords.size();
                i++
        ) {

            String phrase =
                    submittedWords.get(i)
                            + " "
                            +
                    submittedWords.get(i + 1)
                            + " "
                            +
                    submittedWords.get(i + 2);


            // KMP

            int kmpResult =
                    KMPAlgorithm.search(
                            referenceString,
                            phrase
                    );


            if (kmpResult != -1) {

                kmpMatches++;

                if (
                        !matchingPhrases
                                .contains(phrase)
                ) {

                    matchingPhrases.add(
                            phrase
                    );
                }
            }


            // Rabin-Karp

            int rabinResult =
                    RabinKarpAlgorithm.search(
                            referenceString,
                            phrase
                    );


            if (rabinResult != -1) {

                rabinKarpMatches++;
            }
        }


        // --------------------------------
        // 8. EDIT DISTANCE
        // --------------------------------

        String submittedString =
                String.join(
                        " ",
                        submittedWords
                );


        int editDistance =
                EditDistance.calculate(
                        submittedString,
                        referenceString
                );


        double editSimilarity =
                EditDistance.similarity(
                        submittedString,
                        referenceString
                );


        // --------------------------------
        // 9. FINAL SIMILARITY
        // --------------------------------

        double finalSimilarity =
                (
                        wordSimilarity * 0.60
                                +
                        editSimilarity * 0.40
                );


        finalSimilarity =
                Math.round(
                        finalSimilarity * 100
                ) / 100.0;


        // --------------------------------
        // 10. STATUS
        // --------------------------------

        String status;


        if (finalSimilarity < 20) {

            status = "Low Similarity";

        } else if (finalSimilarity < 50) {

            status = "Moderate Similarity";

        } else {

            status = "High Similarity";
        }


        // --------------------------------
        // 11. RESPONSE
        // --------------------------------

        PlagiarismResponse response =
                new PlagiarismResponse();


        response.setSimilarity(
                finalSimilarity
        );


        response.setEditSimilarity(
                editSimilarity
        );


        response.setMatchedWords(
                matchingWords.size()
        );


        response.setTotalWords(
                submittedWords.size()
        );


        response.setKmpMatches(
                kmpMatches
        );


        response.setRabinKarpMatches(
                rabinKarpMatches
        );


        response.setEditDistance(
                editDistance
        );


        response.setStatus(
                status
        );


        response.setMatchingWords(
                matchingWords
        );


        response.setMatchingPhrases(
                matchingPhrases
        );


        return response;
    }
}