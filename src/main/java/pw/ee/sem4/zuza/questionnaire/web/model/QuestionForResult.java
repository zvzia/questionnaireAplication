package pw.ee.sem4.zuza.questionnaire.web.model;

import pw.ee.sem4.zuza.questionnaire.dao.QuestionType;

import java.util.ArrayList;

public class QuestionForResult {
    Integer questionnaireId;
    String questionnaireName;
    Integer questionId;
    Integer questionOrderNumber;
    QuestionType questionType;
    String questionName;
    String questionText;
    ArrayList<AnswerForResult> answersForResult;
    Boolean metrics;

    String chartName;
    ArrayList<String> chartLabels;
    ArrayList<Integer> chartData;



    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Boolean getMetrics() {
        return metrics;
    }

    public void setMetrics(Boolean metrics) {
        this.metrics = metrics;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionOrderNumber() {
        return questionOrderNumber;
    }

    public void setQuestionOrderNumber(Integer questionOrderNumber) {
        this.questionOrderNumber = questionOrderNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<AnswerForResult> getAnswersForResult() {
        return answersForResult;
    }

    public void setAnswersForResult(ArrayList<AnswerForResult> answersForResult) {
        this.answersForResult = answersForResult;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public ArrayList<String> getChartLabels() {
        return chartLabels;
    }

    public void setChartLabels(ArrayList<String> chartLabels) {
        this.chartLabels = chartLabels;
    }

    public ArrayList<Integer> getChartData() {
        return chartData;
    }

    public void setChartData(ArrayList<Integer> chartData) {
        this.chartData = chartData;
    }
}
