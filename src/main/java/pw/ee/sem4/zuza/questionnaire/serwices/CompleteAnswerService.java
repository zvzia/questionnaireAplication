package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEn;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEnRepository;
import pw.ee.sem4.zuza.questionnaire.web.model.AnswerForWeb;
import pw.ee.sem4.zuza.questionnaire.web.model.QuestionForWeb;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class CompleteAnswerService {

    @Autowired
    CompleteAnswersEnRepository completeAnswersEnRepository;

    public void saveCompleteAnswersToDB(QuestionForWeb questionForWeb) {


        if (questionForWeb.getQuestionType().name().equals("CLOSED_LIMITED")) {
            CompleteAnswersEn completeAnswersEn = new CompleteAnswersEn();

            completeAnswersEn.setId(questionForWeb.getCompleteAnswerIdForLimited());
            completeAnswersEn.setIntervieweeId(questionForWeb.getIntervieweeId());
            completeAnswersEn.setAnswerDate(questionForWeb.getAnswerDate());
            completeAnswersEn.setQuestionaireId(questionForWeb.getQuestionnaireId());
            completeAnswersEn.setQuestionId(questionForWeb.getQuestionId());
            if(questionForWeb.getAnswerValueForLimited() != null){
                completeAnswersEn.setAnswerId(Integer.parseInt(questionForWeb.getAnswerValueForLimited()));  //answerValue tutaj jest answer id kiedy jest radiobutton
            }
            completeAnswersEn.setQuestionOrderNumber(questionForWeb.getQuestionOrderNumber());
            completeAnswersEn.setAnswerValue(questionForWeb.getAnswerValueForLimited());
            if (completeAnswersEn.getAnswerValue() != null) {
                completeAnswersEnRepository.saveAndFlush(completeAnswersEn);
            }
        } else {
            for (AnswerForWeb answerForWeb : questionForWeb.getAnswerForWebList()) {
                CompleteAnswersEn completeAnswersEn = new CompleteAnswersEn();

                completeAnswersEn.setId(answerForWeb.getCompleteAnswerId());
                completeAnswersEn.setIntervieweeId(questionForWeb.getIntervieweeId());
                completeAnswersEn.setAnswerDate(questionForWeb.getAnswerDate());
                completeAnswersEn.setQuestionaireId(questionForWeb.getQuestionnaireId());
                completeAnswersEn.setQuestionId(questionForWeb.getQuestionId());
                completeAnswersEn.setAnswerId(answerForWeb.getAnswerId());
                completeAnswersEn.setQuestionOrderNumber(questionForWeb.getQuestionOrderNumber());
                completeAnswersEn.setAnswerValue(answerForWeb.getAnswerValue());
                if (completeAnswersEn.getAnswerValue() != null) {
                    completeAnswersEnRepository.saveAndFlush(completeAnswersEn);
                }
            }
        }
    }

    public Integer getLastCompleteQuestionByIntervieweeId(String intervieweeId) {
        ArrayList<CompleteAnswersEn> completeAnswersEns = completeAnswersEnRepository.findByIntervieweeIdOrderByQuestionOrderNumber(intervieweeId);
        return completeAnswersEns.get(completeAnswersEns.size() - 1).getQuestionId();
    }

    public Integer getAnswerCountByAnswerId(Integer answerId) {

        return null;
    }

    public QuestionForWeb fillFromCompleteAnswer(QuestionForWeb questionForWeb, Cookie myCookie) {
        ArrayList<CompleteAnswersEn> completeAnswersEns = completeAnswersEnRepository.findByIntervieweeIdAndQuestionId(myCookie.getValue(), questionForWeb.getQuestionId());

        if(questionForWeb.getQuestionType().name().equals("CLOSED_LIMITED")){
            for (AnswerForWeb answerForWeb : questionForWeb.getAnswerForWebList()) {
                for (CompleteAnswersEn completeAnswerEn : completeAnswersEns) {
                    if (Objects.equals(answerForWeb.getAnswerId(), completeAnswerEn.getAnswerId())){
                        questionForWeb.setAnswerValueForLimited(completeAnswerEn.getAnswerValue());
                        questionForWeb.setCompleteAnswerIdForLimited(completeAnswerEn.getId());
                    }
                }
            }
        }else{
            for (AnswerForWeb answerForWeb : questionForWeb.getAnswerForWebList()) {
                for (CompleteAnswersEn completeAnswerEn : completeAnswersEns) {
                    if (Objects.equals(answerForWeb.getAnswerId(), completeAnswerEn.getAnswerId())){
                        answerForWeb.setAnswerValue(completeAnswerEn.getAnswerValue());
                        answerForWeb.setCompleteAnswerId(completeAnswerEn.getId());
                    }
                }
            }
        }

        return questionForWeb;
    }

}
