package pw.ee.sem4.zuza.questionnaire.web.model;

import java.util.ArrayList;

public class MetricsForResult {
    int questionnaireId;
    ArrayList<QuestionForResult> questionsForMetrics;
    Integer questionIdForDivision;

    public Integer getQuestionIdForDivision() {
        return questionIdForDivision;
    }

    public void setQuestionIdForDivision(Integer questionIdForDivision) {
        this.questionIdForDivision = questionIdForDivision;
    }

    public ArrayList<QuestionForResult> getQuestionsForMetrics() {
        return questionsForMetrics;
    }

    public void setQuestionsForMetrics(ArrayList<QuestionForResult> questionsForMetrics) {
        this.questionsForMetrics = questionsForMetrics;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}
