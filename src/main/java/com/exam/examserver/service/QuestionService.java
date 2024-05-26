package com.exam.examserver.service;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    //add Question
    public Question addQuestion(Question question);
    //update Question
    public Question updateQuestion(Question question);
    //get All Questions
    public Set<Question> getAllQuestion();
    //get Question By Id
    public Question getQuestion(Long qId);
    //Get question of Quiz
    public Set<Question> getQuestionOfQuiz(Quiz quiz);
    //Delete Question
    public void deleteQuestion(Long quesId);

    public Question get(Long questionId);
}
