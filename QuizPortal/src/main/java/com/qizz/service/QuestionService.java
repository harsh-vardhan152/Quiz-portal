package com.qizz.service;

import java.util.Set;

import com.qizz.model.Quizz.Question;
import com.qizz.model.Quizz.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public Set<Question> getQuestionsofQuiz(Quiz quiz);

	public void deleteQuestion(Long questionId);

	public Question get(Long questionId);

}
