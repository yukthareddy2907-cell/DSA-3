package com.plagiarism.demo.model;

import java.util.List;

public class PlagiarismResponse {

    private double similarity;

    private double editSimilarity;

    private int matchedWords;

    private int totalWords;

    private int kmpMatches;

    private int rabinKarpMatches;

    private int editDistance;

    private String status;

    private List<String> matchingWords;

    private List<String> matchingPhrases;


    public double getSimilarity() {

        return similarity;
    }


    public void setSimilarity(
            double similarity
    ) {

        this.similarity = similarity;
    }


    public double getEditSimilarity() {

        return editSimilarity;
    }


    public void setEditSimilarity(
            double editSimilarity
    ) {

        this.editSimilarity =
                editSimilarity;
    }


    public int getMatchedWords() {

        return matchedWords;
    }


    public void setMatchedWords(
            int matchedWords
    ) {

        this.matchedWords =
                matchedWords;
    }


    public int getTotalWords() {

        return totalWords;
    }


    public void setTotalWords(
            int totalWords
    ) {

        this.totalWords =
                totalWords;
    }


    public int getKmpMatches() {

        return kmpMatches;
    }


    public void setKmpMatches(
            int kmpMatches
    ) {

        this.kmpMatches =
                kmpMatches;
    }


    public int getRabinKarpMatches() {

        return rabinKarpMatches;
    }


    public void setRabinKarpMatches(
            int rabinKarpMatches
    ) {

        this.rabinKarpMatches =
                rabinKarpMatches;
    }


    public int getEditDistance() {

        return editDistance;
    }


    public void setEditDistance(
            int editDistance
    ) {

        this.editDistance =
                editDistance;
    }


    public String getStatus() {

        return status;
    }


    public void setStatus(
            String status
    ) {

        this.status = status;
    }


    public List<String> getMatchingWords() {

        return matchingWords;
    }


    public void setMatchingWords(
            List<String> matchingWords
    ) {

        this.matchingWords =
                matchingWords;
    }


    public List<String> getMatchingPhrases() {

        return matchingPhrases;
    }


    public void setMatchingPhrases(
            List<String> matchingPhrases
    ) {

        this.matchingPhrases =
                matchingPhrases;
    }
}