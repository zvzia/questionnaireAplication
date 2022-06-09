/*
 * Created by .
 * User: gaika
 * Date: 05.04.2022
 * Time: 18:42
 * GóZIK
 */
package pw.ee.sem4.zuza.questionnaire.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEn;
import pw.ee.sem4.zuza.questionnaire.serwices.AnswerService;
import pw.ee.sem4.zuza.questionnaire.serwices.QuestionService;
import pw.ee.sem4.zuza.questionnaire.serwices.QuestionaireService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

    static Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    QuestionaireService questionaireService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    @RequestMapping({"/admin", "/admin/questionnaireList"})
    public String showSeedstarters(ModelMap modelMap) {

        List<QuestionnaireEn> allQuestionnairs = questionaireService.getAllQuestionnairs();
        modelMap.put("allQuestionnairs", allQuestionnairs);

        log.debug("Lista ankiet" + modelMap);

        return "questionnaireList";
    }

    @RequestMapping({"/admin/editQuestionnaire"})
    public String editQuestionnaire(QuestionnaireEn questionnaireEn, BindingResult bdResult, ModelMap modelMap) {

        log.debug("edit {}, {}", questionnaireEn.getId(), bdResult);
        questionnaireEn = questionaireService.getQuestionnaireById(questionnaireEn.getId());
        modelMap.put("questionnaireEn", questionnaireEn);
        log.debug("pobrano z serwisu ankiete {}", questionnaireEn);

        Collection<QuestionEn> allQuestions = questionnaireEn.getQuestionsById();
        modelMap.put("allQuestions", allQuestions);

        return "editQuestionnaire";
    }

    @RequestMapping({"/admin/editQuestionnaire/editQuestion"})
    public String editQuestion(QuestionEn questionEn, BindingResult bdResult, ModelMap modelMap) {

        log.debug("edit {}, {}", questionEn.getId(), bdResult);
        questionEn = questionService.getQuestionById(questionEn.getId());
        modelMap.put("questionEn", questionEn);
        log.debug("pobrano z serwisu pytanie {}", questionEn);

       /* Collection<AnswerEn> answers = questionEn.getAnswersById();
        modelMap.put("answers", answers);
*/
        return "editQuestion";
    }

    @RequestMapping(value = {"/admin/saveQuestionnaire"}, method = {RequestMethod.POST}, params = {"save"})
    public String saveQuestionnaire(QuestionnaireEn questionnaireEn, final BindingResult bindingResult, ModelMap modelMap, final HttpServletRequest req) {
        System.out.println(req);
        Collection<QuestionEn> questions = new ArrayList<>();

        if (questionnaireEn.getId() == null) {
            questionnaireEn.setCreated(new Date());
        } else {
            questions = questionaireService.getQuestionnaireById(questionnaireEn.getId()).getQuestionsById();
        }
        questionnaireEn.setQuestionsById(questions);
        questionaireService.saveQuestionnaireToDB(questionnaireEn);

        return "redirect:/admin/editQuestionnaire?id=" + questionnaireEn.getId().toString();
    }

    @RequestMapping(value = {"/admin/editQuestionnaire/saveQuestion"}, method = {RequestMethod.POST}, params = {"save"})
    public String saveQuestion(QuestionEn questionEn, final BindingResult bindingResult, ModelMap modelMap, final HttpServletRequest req) {
        System.out.println(req);
        if (bindingResult.hasErrors()) {
            return "editQuestion";
        }

        QuestionnaireEn dbQuestionnaireEn = questionaireService.getQuestionnaireById(questionEn.getQuestionnaireId());
        questionEn.setQuestionnaireByQuestionnaireId(dbQuestionnaireEn);
        if (questionEn.getAnswersById() != null) {
            for (AnswerEn answer : questionEn.getAnswersById()) {
                answer.setQuestionByQuestionId(questionEn);
            }
        }

        questionService.saveQuestionToDB(questionEn);
        return "redirect:/admin/editQuestionnaire/editQuestion?id=" + questionEn.getId().toString();
//        return "editQuestion";
    }

    @RequestMapping(value = {"/admin/editQuestionnaire/saveQuestion"}, method = {RequestMethod.POST}, params = {"addAnswer"})
    public String addAnswerToQuestion(QuestionEn questionEn, final BindingResult bindingResult, ModelMap modelMap, final HttpServletRequest req) {
        System.out.println(req);

        if (questionEn.getAnswersById() == null) {
            questionEn.setAnswersById(new ArrayList<>());
        }
        questionEn.getAnswersById().add(new AnswerEn());
        modelMap.put("goTo", "na");
        return "editQuestion";
    }

    @RequestMapping(value = {"/admin/editQuestionnaire/saveQuestion"}, method = {RequestMethod.POST}, params = {"deleteAnswer"})
    public String deleteAnswerfromQuestion(QuestionEn questionEn, final BindingResult bindingResult, ModelMap modelMap, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("deleteAnswer"));

        questionEn.getAnswersById().remove(rowId.intValue());
        modelMap.put("goTo", "del");
        return "editQuestion";
    }


    @RequestMapping({"/admin/newQuestionnaire"})
    public String newQuestionnaire(QuestionnaireEn questionnaireEn) {
        return "editQuestionnaire";
    }

    @RequestMapping({"/admin/editQuestionnaire/newQuestion"})
    public String newQuestion(QuestionnaireEn questionnaireEn, QuestionEn questionEn, final BindingResult bindingResult, ModelMap modelMap) {
        System.err.println("test");
        return "editQuestion";
    }

    @RequestMapping({"/admin/deleteQuestionnaire"})
    public String deleteQuestionnaire(QuestionnaireEn questionnaireEn) {
        questionaireService.deleteQuestionnaireFromDBById(questionnaireEn.getId());
        return "redirect:/admin";
    }

    @RequestMapping({"/admin/editQuestionnaire/deleteQuestion"})
    public String deleteQuestion(QuestionEn questionEn) {
        //QuestionEn questionEn1 = questionService.getQuestionById(questionEn.getId());
        questionService.deleteQuestionFromDBById(questionEn.getId());
        return "redirect:/admin/editQuestionnaire?id=" + questionEn.getQuestionnaireId();
    }

    @RequestMapping({"/admin/editQuestionnaire/editQuestion/deleteAnswer"})
    public String deleteAnswer(AnswerEn answerEn) {
        answerService.deleteAnswerFromDBById(answerEn.getId());
        return "redirect:/admin/editQuestionnaire/editQuestion?id=" + answerEn.getQuestionId();
    }




}
