package com.example.topquiz.modele;

import java.util.Collections;
import java.util.List;

public class BanqueQuestions {
    private List<Questions> questions;
    private int indexQuestionSuivante;

    public BanqueQuestions(List<Questions> questionList) {
        questions = questionList;

        // Shuffle the question list
        Collections.shuffle(questions);

        indexQuestionSuivante = 0;
    }

    public Questions getQuestion() {
        // Ensure we loop over the questions
        if (indexQuestionSuivante == questions.size()) {
            indexQuestionSuivante = 0;
        }

        // Please note the post-incrementation
        return questions.get(indexQuestionSuivante++);
    }
}
