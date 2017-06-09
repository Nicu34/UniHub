package com.websystique.springmvc.controller;

import com.websystique.springmvc.dto.QuizDto;
import com.websystique.springmvc.model.Answer;
import com.websystique.springmvc.model.Question;
import com.websystique.springmvc.model.Score;
import com.websystique.springmvc.service.QuestionService;
import com.websystique.springmvc.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by nicu on 6/7/2017.
 */
@Controller
public class QuizController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ScoreService scoreService;

    private boolean wereAdded = false;

    @RequestMapping(value = "/quiz-setup", method = RequestMethod.GET)
    public String getStartPage(ModelMap modelMap) {
        modelMap.put("quizDto", new QuizDto());

        return "quiz-setup";
    }

    @RequestMapping(value = "/quiz-setup", method = RequestMethod.POST)
    public String setInitialQuizValues(@ModelAttribute QuizDto quizDto) {
        return "redirect:/quiz/" + quizDto.getQuestionsNumber() + "/" + quizDto.getQuestionsPerPage();
    }

    @RequestMapping(value = "/quiz/{limit}/{perPage}", method = RequestMethod.GET)
    public String getQuiz(ModelMap modelMap, @PathVariable Integer limit, @PathVariable  Integer perPage) {
        QuestionListWrapper questionListWrapper = new QuestionListWrapper();
        Set<Question> questions = getQuestionList(limit);
        questionListWrapper.setQuestions(questions);
        modelMap.put("questionListWrapper", questionListWrapper);
        modelMap.put("perPage", perPage);

        return "quiz";
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.POST)
    public String getQuizResults(@ModelAttribute("questionListWrapper") QuestionListWrapper questionListWrapper, ModelMap modelMap) {
        int correctAnswerNb = questionService.getCorrectQuestionsNumber(questionListWrapper);
        Integer questionsNumber = questionListWrapper.getQuestions().size();
        Score score = new Score();

        score.setAnswersNumber(questionsNumber);
        score.setCorrectAnswers(correctAnswerNb);
        score.setWrongAnswers(questionsNumber - correctAnswerNb);
        scoreService.save(score);

        modelMap.clear();
        modelMap.put("score", score);
        modelMap.put("bestScore", scoreService.getBestScore());

        return "score";
    }

    private Set<Question> getQuestionList(Integer limit) {
        if (!wereAdded) {
            Answer answer = new Answer("2");
            Answer answer2 = new Answer("100");
            Answer answer3 = new Answer("200");
            Answer answer4 = new Answer("300");
            Answer answer5 = new Answer("300");
            Answer answer6 = new Answer("100");

            Question question = new Question();
            question.setAnswersList(Arrays.asList(answer, new Answer("10"), new Answer("30")));
            question.setQuestion("1 + 1 = ");
            question.setCorrectAnswer(answer);

            Question question1 = new Question();
            question1.setQuestion("50 * 2 = ");
            question1.setCorrectAnswer(answer2);
            question1.setAnswersList(Arrays.asList(answer2, new Answer("210"), new Answer("300")));

            Question question2 = new Question();
            question2.setQuestion("199 + 1 = ");
            question2.setCorrectAnswer(answer3);
            question2.setAnswersList(Arrays.asList(answer3, new Answer("200"), new Answer("300")));

            Question question3 = new Question();
            question3.setQuestion("305 - 5 = ");
            question3.setCorrectAnswer(answer4);
            question3.setAnswersList(Arrays.asList(answer4, new Answer("200"), new Answer("333")));

            Question question4 = new Question();
            question4.setQuestion("30 * 10 = ");
            question4.setCorrectAnswer(answer5);
            question4.setAnswersList(Arrays.asList(answer5, new Answer("200"), new Answer("30")));

            questionService.save(question);
            questionService.save(question1);
            questionService.save(question2);
            questionService.save(question3);
            questionService.save(question4);

            wereAdded = true;
        }

        return questionService.findAll(limit);
    }
}
