package pw.ee.sem4.zuza.questionnaire.web.model;

import java.util.ArrayList;

public class MetricsForResult {
    int questionnaireId;
    ArrayList<QuestionForResult> questionsForMetrics;


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
