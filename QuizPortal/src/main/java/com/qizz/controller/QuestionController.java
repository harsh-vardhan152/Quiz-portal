package com.qizz.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qizz.model.Quizz.Question;
import com.qizz.model.Quizz.Quiz;
import com.qizz.service.QuestionService;
import com.qizz.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add the Question
	@PostMapping("/")
	public ResponseEntity<Question> addQuiz(@RequestBody Question question) {

		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// update Question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuiz(@RequestBody Question question) {

		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get all question of any quiz

	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionofQuiz(@PathVariable("qid") Long qid) {
		// by this all the question we will get
//		Quiz quiz =new Quiz();
//		quiz.setqId(qid);		
//		Set<Question> questionofQuiz=this.questionService.getQuestionsofQuiz(quiz);
//		return ResponseEntity.ok(questionofQuiz);

		// i want number of question that i created in Quiz model so for that we need
		// this

		Quiz quiz = this.quizService.getQuiz(qid);

		Set<Question> questions = quiz.getQuestions();

		List list = new ArrayList(questions);

		if (list.size() > Integer.parseInt(quiz.getNumberofQuestion())) {

			list = list.subList(0, Integer.parseInt(quiz.getNumberofQuestion() + 1));
		}
		// for making our question in mismatch form
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	// get Single Question
	@GetMapping("/{quesId}")
	public Question getSingleQuestion(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	// delete question
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}

	// evaluate quiz that user as given
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {

		System.out.println(questions);
		double marksGot = 0;
		int correctAnswer = 0;
		int attempted = 0;
		for (Question q : questions) {
//			single user
//			System.out.println(q.getGivenAnswer());
			Question question = this.questionService.get(q.getQuesId());
			if (question.getAnswer().equals(q.getGivenAnswer())) {

				correctAnswer++;

				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;

			}
			if (q.getGivenAnswer() != null) {

				attempted++;
			}
		}
		
		Map<String, Object> map= Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);

	}

}
