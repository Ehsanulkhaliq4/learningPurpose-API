package com.exam.examserver.service;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    //add Quiz
    public Quiz addQuiz(Quiz quiz);
    //update Quiz
    public Quiz updateQuiz(Quiz quiz);
    //get All Quizzes
    public Set<Quiz> getAllQuizzes();
    //get Quiz By Id
    public Quiz getQuizById(Long quizId);
    //delete Quiz
    public void deleteQuiz(Long quizId);
    //get quizzes of specific category
    public List<Quiz> getQuizzesOfCategory(Category category);
    //get Active quizzes
    public List<Quiz> getActiveQuizzes();
    //get Active quizzes of specific category
    public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
