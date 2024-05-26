package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.service.QuestionService;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    //add Question
    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }
    //update Question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }
    //get All Questions of any Quiz
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getQuestionsOFQuiz(@PathVariable("qId") Long qId){
        //Ik tareeka ya bhi ha
//        Quiz quiz = new Quiz();
//        quiz.setqId(qId);
//        Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionOfQuiz);
        Quiz quiz = this.quizService.getQuizById(qId);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())){
            list.subList(0 , Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    //get All Questions of Quiz Only use by Admin
    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<?> getQuestionsOFQuizAdmin(@PathVariable("qId") Long qId){
        Quiz quiz = new Quiz();
        quiz.setqId(qId);
        Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);
    }
    //get Single Question
    @GetMapping("/{quesId}")
    public Question getSingleQuestion(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }
    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }
    //evaluating quiz
    @PostMapping("eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
    {
        System.out.println(questions);
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;
        for(Question q:questions){
            //get single question
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())){
                //correct
                correctAnswers++;
                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot += marksSingle;
            }
            if (q.getGivenAnswer() != null){
                attempted++;
            }
            }
        Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return ResponseEntity.ok(map);
    }
}
