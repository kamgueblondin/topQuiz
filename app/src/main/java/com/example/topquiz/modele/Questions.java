package com.example.topquiz.modele;

import java.util.List;

public class Questions {
    private String question;
    private List<String> reponses;
    private int indexBonneReponse;

    public Questions(String question, List<String> reponses, int indexBonneReponse) {
        this.question = question;
        this.reponses = reponses;
        this.indexBonneReponse = indexBonneReponse;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getReponses() {
        return reponses;
    }

    public int getIndexBonneReponse() {
        return indexBonneReponse;
    }
}
