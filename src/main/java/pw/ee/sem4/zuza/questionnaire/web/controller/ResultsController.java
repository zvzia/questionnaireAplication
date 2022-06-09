package pw.ee.sem4.zuza.questionnaire.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEnRepository;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEn;
import pw.ee.sem4.zuza.questionnaire.serwices.*;
import pw.ee.sem4.zuza.questionnaire.web.model.MetricsForResult;
import pw.ee.sem4.zuza.questionnaire.web.model.QuestionForResult;
import pw.ee.sem4.zuza.questionnaire.web.model.QuestionForWeb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class ResultsController {

    @Autowired
    QuestionaireService questionaireService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionForResultService questionForResultService;
    @Autowired
    CompleteAnswersEnRepository completeAnswersEnRepository;


    @RequestMapping({"/admin/questionnaireResult"})
    public String questionnaireResult(Integer qId, ModelMap modelMap) {
        QuestionnaireEn questionnaireEn = questionaireService.getQuestionnaireById(qId);
        Collection<QuestionEn> questionEns = questionnaireEn.getQuestionsById();
        ArrayList<QuestionForResult> questionForResults = new ArrayList<>();
        ArrayList<QuestionForResult> questionsForMetrics = new ArrayList<>();
        for (QuestionEn questionEn : questionEns) {
            /*if (questionEn.getMetrics()) {    //dodac jak zrobie juz podzial ze wzgledu na metryczke
                QuestionForResult questionForMetrics = questionForResultService.builtQuestionForResultFromQuestionEn(questionEn);
                questionsForMetrics.add(questionForMetrics);
            } else {*/
                QuestionForResult questionForResult = questionForResultService.builtQuestionForResultFromQuestionEn(questionEn);
                questionForResults.add(questionForResult);
            //}
        }
        MetricsForResult metricsForResult = new MetricsForResult();
        metricsForResult.setQuestionnaireId(qId);
        metricsForResult.setQuestionsForMetrics(questionsForMetrics);
        modelMap.put("questionForResults", questionForResults);
        modelMap.put("metricsForResult", metricsForResult);


        String questionnaireName = questionaireService.getQuestionnaireById(qId).getName();
        modelMap.put("questionnaireName", questionnaireName);

        return "questionnaireResult";
    }


    @RequestMapping({"/admin/questionnaireResult/openAnswersResult"})
    public String Result(Integer questionId, ModelMap modelMap) {
        Collection<String> openAnswers = completeAnswersEnRepository.getOpenAnswersByQuestionId(questionId);
        modelMap.put("openAnswers", openAnswers);

        String questionName = questionService.getQuestionById(questionId).getQuestionText();
        modelMap.put("questionName", questionName);
        modelMap.put("questionnaireId", questionService.getQuestionById(questionId).getQuestionnaireId());

        return "openAnswersResult";
    }

    /*@RequestMapping(value = {"/admin/questionnaireResult/dividedResults"}, params = {"divide"})
    public String fillQuestion(Integer qId, MetricsForResult metricsForResult, ModelMap modelMap, final HttpServletRequest req, final HttpServletResponse res) throws InterruptedException {

        MetricsForResult metricsForResultForDivision = metricsForResult;

        QuestionnaireEn questionnaireEn = questionaireService.getQuestionnaireById(qId);
        Collection<QuestionEn> questionEns = questionnaireEn.getQuestionsById();
        ArrayList<QuestionForResult> questionsForResults = new ArrayList<>();
        ArrayList<QuestionForResult> questionsForMetrics = new ArrayList<>();
        for (QuestionEn questionEn : questionEns) {
            if (questionEn.getMetrics()) {
                QuestionForResult questionForMetrics = questionForResultService.builtQuestionForResultFromQuestionEn(questionEn);
                questionsForMetrics.add(questionForMetrics);
            } else {
                QuestionForResult questionForResult = questionForResultService.builtQuestionForResultFromQuestionEn(questionEn);
                questionsForResults.add(questionForResult);
            }
        }
        metricsForResult = new MetricsForResult();
        metricsForResult.setQuestionnaireId(qId);
        metricsForResult.setQuestionsForMetrics(questionsForMetrics);
        metricsForResult.setQuestionIdForDivision(metricsForResultForDivision.getQuestionIdForDivision());
        modelMap.put("questionForResults", questionsForResults);
        modelMap.put("metricsForResult", metricsForResult);


        String questionnaireName = questionaireService.getQuestionnaireById(qId).getName();
        modelMap.put("questionnaireName", questionnaireName);

        //---------------------------------------------------

        ArrayList<QuestionForResult> dividedQuestionsForResults = new ArrayList<>();

        for (QuestionEn questionEn : questionEns) {
            if (!questionEn.getMetrics()) {
                QuestionForResult dividedQuestionForResult = questionForResultService.builtDividedQuestionForResultFromQuestionEn(questionEn ,metricsForResultForDivision.getQuestionIdForDivision());
                dividedQuestionsForResults.add(dividedQuestionForResult);
            }
        }


        return "questionnaireResult";
    }*/
}
