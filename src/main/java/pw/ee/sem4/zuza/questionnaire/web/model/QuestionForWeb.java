package pw.ee.sem4.zuza.questionnaire.web.model;

import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionForWeb {
    String questionText;
    String questionName;
    Integer questionId;
    Integer questionnaireId;
    List<AnswerForWeb> answerForWebList=new ArrayList<>();
    QuestionType questionType;
    String intervieweeId;
    Date answerDate;
    String answerValueForLimited;  //potrzebne żeby mógł działać radiobutton
    Integer completeAnswerIdForLimited;
    Integer questionOrderNumber;

    public Integer getCompleteAnswerIdForLimited() {
        return completeAnswerIdForLimited;
    }

    public void setCompleteAnswerIdForLimited(Integer completeAnswerIdForLimited) {
        this.completeAnswerIdForLimited = completeAnswerIdForLimited;
    }

    public Integer getQuestionOrderNumber() {
        return questionOrderNumber;
    }

    public void setQuestionOrderNumber(Integer questionOrderNumber) {
        this.questionOrderNumber = questionOrderNumber;
    }

    public String getIntervieweeId() {
        return intervieweeId;
    }

    public void setIntervieweeId(String intervieweeId) {
        this.intervieweeId = intervieweeId;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public String getAnswerValueForLimited() {
        return answerValueForLimited;
    }

    public void setAnswerValueForLimited(String answerValueForLimited) {
        this.answerValueForLimited = answerValueForLimited;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<AnswerForWeb> getAnswerForWebList() {
        return answerForWebList;
    }

    public void setAnswerForWebList(List<AnswerForWeb> answerForWebList) {
        this.answerForWebList = answerForWebList;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public static QuestionForWeb buildFromQuestionEn(QuestionEn questionEn) {
        QuestionForWeb questionForWeb = new QuestionForWeb();
        questionForWeb.setQuestionText(questionEn.getQuestionText());
        questionForWeb.setQuestionId(questionEn.getId());
        questionForWeb.setQuestionName(questionEn.getName());
        questionForWeb.setQuestionType(questionEn.getType());

        List<AnswerForWeb> answerForWebList = new ArrayList<>();
        for (AnswerEn answerEn : questionEn.getAnswersById()) {
            answerForWebList.add(AnswerForWeb.buildFromAnswerEn(answerEn));
        }

        questionForWeb.setAnswerForWebList(answerForWebList);
        questionForWeb.setQuestionOrderNumber(questionEn.getOrderNumber());

        return questionForWeb;
    }
}
