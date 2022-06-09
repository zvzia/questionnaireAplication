package pw.ee.sem4.zuza.questionnaire.web.model;

import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;

public class AnswerForWeb {
    private Integer completeAnswerId;
    private String answerText;
    private Integer answerId;
    private String answerName;

    private String answerValue;



    public static AnswerForWeb buildFromAnswerEn(AnswerEn answerEn) {
        AnswerForWeb answerForWeb = new AnswerForWeb();
        answerForWeb.setAnswerId(answerEn.getId());
        answerForWeb.setAnswerText(answerEn.getAnswerText());
        answerForWeb.setAnswerName(answerEn.getName());

        return answerForWeb;
    }

    public Integer getCompleteAnswerId() {
        return completeAnswerId;
    }

    public void setCompleteAnswerId(Integer completeAnswerId) {
        this.completeAnswerId = completeAnswerId;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }
}
