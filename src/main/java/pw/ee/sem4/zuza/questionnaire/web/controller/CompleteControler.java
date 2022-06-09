package pw.ee.sem4.zuza.questionnaire.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pw.ee.sem4.zuza.questionnaire.dao.*;
import pw.ee.sem4.zuza.questionnaire.serwices.*;
import pw.ee.sem4.zuza.questionnaire.web.model.AnswerForWeb;
import pw.ee.sem4.zuza.questionnaire.web.model.QuestionForWeb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class CompleteControler {

    @Autowired
    QuestionaireService questionaireService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    CompleteQuestionnaireService completeQuestionnaireService;
    @Autowired
    CompleteAnswerService completeAnswerService;
    @Autowired
    CompleteAnswersEnRepository completeAnswersEnRepository;


    @RequestMapping(value = {"/user/fillQuestionnaire"}, params = "qId")
    public String welcomePage(String qId, ModelMap modelMap, final HttpServletRequest req) {
        modelMap.put("qId", qId);

        QuestionnaireEn questionnaireEn = questionaireService.findQuestionnaireByIdAndCreated(qId);

        if (questionnaireEn == null) {
            modelMap.put("errorMessage", "Taka ankieta nie istnieje");
            return "questionnaireError";
        } else if (!questionnaireEn.getActive()) {
            modelMap.put("errorMessage", "Ta ankieta już nie jest aktywna");
            return "questionnaireError";
        }

        //sprawdza cookie czy zostala rozpoczeta ankieta
        Cookie myCookie = null;
        Cookie endCookie = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(qId)) {
                    myCookie = cookie;
                    break;
                }
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("end")) {
                    endCookie = cookie;
                    break;
                }
            }
        }

        //znalezione cookie dla tej konkretnej ankiety, szukam czy są już odpowiedzi
        Integer completedQuestionCount = null;
        if (myCookie != null) {
            completedQuestionCount = completeQuestionnaireService.getCompletedQuestionCountForIntervieweeId(myCookie.getValue());
        }


        //sprawdzanie czy ankieta nie została już wypełniona
        /*int questionsInQuestionnaire = questionnaireEn.getQuestionsById().size();

        if (completedQuestionCount != null && completedQuestionCount >= questionsInQuestionnaire) {
            modelMap.put("errorMessage", "Ta ankieta już została przez Ciebie wypełniona");
            return "questionnaireError";
        }*/

        if (endCookie != null) {
            modelMap.put("errorMessage", "Ta ankieta już została przez Ciebie wypełniona");
            return "questionnaireError";
        }


        boolean started = false;

        if (completedQuestionCount != null && completedQuestionCount > 0) {
            started = true;
        }


        modelMap.put("questionnaireEn", questionnaireEn);
        modelMap.put("started", started);
        return "startQuestionnaire";

    }


    @RequestMapping(value = {"/user/fillQuestionnaire/fillQuestion"}, params = {"step"})
    public String fillQuestion(QuestionEn questionEn, QuestionForWeb questionForWeb, String qId, ModelMap modelMap, final HttpServletRequest req, final HttpServletResponse res) throws InterruptedException {
        modelMap.put("qId", qId);
        QuestionnaireEn questionnaireEn = questionaireService.findQuestionnaireByIdAndCreated(qId);

        if (questionnaireEn == null) {
            modelMap.put("errorMessage", "Taka ankieta nie istnieje");
            return "questionnaireError";
        } else if (!questionnaireEn.getActive()) {
            modelMap.put("errorMessage", "Ta ankieta już nie jest aktywna");
            return "questionnaireError";
        }

        //sprawdza cookie czy zostala rozpoczeta ankieta
        Cookie myCookie = null;
        Cookie endCookie = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(qId)) {
                    myCookie = cookie;
                    break;
                }
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("end")) {
                    endCookie = cookie;
                    break;
                }
            }
        }

        //znalezione cookie dla tej konkretnej ankiety, szukam czy są już odpowiedzi
        Integer lastCompleteQuestionId = null;
        if (myCookie != null) {
            //completedQuestionCount = completeQuestionnaireService.getCompletedQuestionCountForIntervieweeId(myCookie.getValue());
            lastCompleteQuestionId = completeAnswerService.getLastCompleteQuestionByIntervieweeId(myCookie.getValue()); //TODO
        }

        //dodawanie cookie jesli nie przyszło z welcome page
        String newCookieVal = null;
        if (questionForWeb.getQuestionId() != null) {
            if (myCookie == null) {
                newCookieVal = createNewCookieVal();
            } else {
                newCookieVal = myCookie.getValue();
            }

            Cookie newCookie = new Cookie(qId, newCookieVal);
            res.addCookie(newCookie);
        }

        //dodawanie endCookie jeśli ankieta została zakończona
        if (req.getParameter("step").equals("end")) {
            Cookie newEndCookie = new Cookie("end", null);
            res.addCookie(newEndCookie);
        }


        //sprawdzanie czy ankieta nie została już wypełniona
        int questionsInQuestionnaire = questionnaireEn.getQuestionsById().size();
        /*if (completedQuestionCount != null && completedQuestionCount >= questionsInQuestionnaire) {
            modelMap.put("errorMessage", "Ta ankieta już została przez Ciebie wypełniona");
            return "questionnaireError";
        }
        ArrayList<QuestionEn> questionEns = questionService.getQuestionsByQuestionnaireIdSorted(questionnaireEn.getId());
        if (lastCompleteQuestionId != null && lastCompleteQuestionId.equals(questionEns.get(questionEns.size() - 1).getId())) {
            modelMap.put("errorMessage", "Ta ankieta już została przez Ciebie wypełniona");
            return "questionnaireError";
        }*/
        if (endCookie != null) {
            modelMap.put("errorMessage", "Ta ankieta już została przez Ciebie wypełniona");
            return "questionnaireError";
        }


        //-----------------------------------------------------------------------------
        //zapisywanie odpowiedźi

        if (questionForWeb.getQuestionId() != null) {
            questionForWeb.setIntervieweeId(newCookieVal);
            questionForWeb.setAnswerDate(new Date(System.currentTimeMillis()));
            String[] qIdParts = qId.split("_");
            questionForWeb.setQuestionnaireId(Integer.parseInt(qIdParts[0]));
            completeAnswerService.saveCompleteAnswersToDB(questionForWeb);
        }


        //-----------------------------------------------------------------------------
        //proces wyswietlania pytan

        ArrayList<QuestionEn> questionEns = questionService.getQuestionsByQuestionnaireIdSorted(questionnaireEn.getId());

        String step = req.getParameter("step");
        QuestionForWeb questionForWebNew = new QuestionForWeb();

        boolean lastQuestion = false;

        if (questionForWeb.getQuestionId() == null && !step.equals("continue")) {
            questionForWebNew = QuestionForWeb.buildFromQuestionEn(questionEns.get(0));
        } else if (step.equals("end")) {
            return "endPage";
        }else if (step.equals("continue")) {
            int lastCompleteQuestionIndex = getLastCompletedQuestionIndex(questionEns, lastCompleteQuestionId);
            questionForWebNew = QuestionForWeb.buildFromQuestionEn(questionEns.get(lastCompleteQuestionIndex));

            if (lastCompleteQuestionIndex == questionsInQuestionnaire - 1) {
                lastQuestion = true;
            }
        } else {
            if (step.equals("next")) {
                int nextQuestionIndex = getNextQuestionIndex(questionEns, questionForWeb);
                if (nextQuestionIndex == questionsInQuestionnaire - 1) {
                    lastQuestion = true;
                }
                questionForWebNew = QuestionForWeb.buildFromQuestionEn(questionEns.get(nextQuestionIndex));

            } else if (step.equals("prev")) {
                int prevQuestionIndex = getPrevQuestionIndex(questionEns, questionForWeb);

                if (prevQuestionIndex < 0) {
                    modelMap.put("questionnaireEn", questionnaireEn);
                    modelMap.put("started", false);
                    return "startQuestionnaire";
                } else {
                    questionForWebNew = QuestionForWeb.buildFromQuestionEn(questionEns.get(prevQuestionIndex));
                }
            } else if (step.equals("last")) {
                questionForWebNew = QuestionForWeb.buildFromQuestionEn(questionEns.get(questionsInQuestionnaire - 1));
            }
        }


        if (lastCompleteQuestionId != null) {
            questionForWebNew = completeAnswerService.fillFromCompleteAnswer(questionForWebNew, myCookie);
        }

        modelMap.put("lastQuestion", lastQuestion);
        modelMap.put("questionForWeb", questionForWebNew);


        return "fillQuestion";
    }

    private String createNewCookieVal() throws InterruptedException {
        long val1 = new Random(System.currentTimeMillis()).nextLong();
        Thread.sleep(200);
        long val2 = new Random(System.currentTimeMillis()).nextLong();
        int val3 = Long.valueOf(val1 + val2).hashCode();
        return val1 + "_" + val2 + "_" + val3;
    }

    private int getPrevQuestionIndex(ArrayList<QuestionEn> questionEns, QuestionForWeb questionForWeb) {
        int count = 0;
        for (QuestionEn questionEn : questionEns) {
            if (Objects.equals(questionEn.getId(), questionForWeb.getQuestionId())) {
                break;
            }
            count++;
        }

        return count - 1;
    }

    private int getNextQuestionIndex(ArrayList<QuestionEn> questionEns, QuestionForWeb questionForWeb) {
        int count = 0;
        for (QuestionEn questionEn : questionEns) {
            count++;
            if (Objects.equals(questionEn.getId(), questionForWeb.getQuestionId())) {
                break;
            }
        }

        return count;
    }

    private int getLastCompletedQuestionIndex(ArrayList<QuestionEn> questionEns, Integer lastCompletedQuestionId) {
        int count = 0;
        for (QuestionEn questionEn : questionEns) {
            if (Objects.equals(questionEn.getId(), lastCompletedQuestionId)) {
                break;
            }
            count++;
        }

        return count;
    }
}
