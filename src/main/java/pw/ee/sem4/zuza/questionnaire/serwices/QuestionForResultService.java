package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEnRepository;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEn;
import pw.ee.sem4.zuza.questionnaire.web.model.AnswerForResult;
import pw.ee.sem4.zuza.questionnaire.web.model.QuestionForResult;

import java.util.ArrayList;

@Service
public class QuestionForResultService {

    @Autowired
    CompleteAnswersEnRepository completeAnswersEnRepository;
    @Autowired
    QuestionaireService questionaireService;

    public QuestionForResult builtQuestionForResultFromQuestionEn(QuestionEn questionEn) {
        QuestionForResult questionForResult = new QuestionForResult();
        questionForResult.setQuestionnaireId(questionEn.getQuestionnaireId());
        questionForResult.setQuestionnaireName(questionaireService.getQuestionnaireById(questionEn.getQuestionnaireId()).getName());
        questionForResult.setQuestionId(questionEn.getId());
        questionForResult.setQuestionOrderNumber(questionEn.getOrderNumber());
        questionForResult.setQuestionType(questionEn.getType());
        questionForResult.setQuestionText( questionEn.getQuestionText());
        ArrayList<AnswerForResult> answerForResults = new ArrayList<>();
        for (AnswerEn answerEn : questionEn.getAnswersById()) {
            AnswerForResult answerForResult = new AnswerForResult();
            answerForResult.setAnswerId(answerEn.getId());
            answerForResult.setAnswerText(answerEn.getAnswerText());
            answerForResult.setAnswerCount(completeAnswersEnRepository.getAnswerCountByAnswerId(answerEn.getId()));
            answerForResults.add(answerForResult);
        }
        questionForResult.setAnswersForResult(answerForResults);
        questionForResult.setMetrics(questionEn.getMetrics());
        questionForResult.setQuestionName(questionEn.getName());

        //dla wykresów
        questionForResult.setChartName("chart_" + Integer.toString(questionForResult.getQuestionId()));

        ArrayList<String> chartLabels = new ArrayList<>();
        for (AnswerForResult answerForResult : answerForResults) {
            chartLabels.add(answerForResult.getAnswerText());
        }
        questionForResult.setChartLabels(chartLabels);

        ArrayList<Integer> chartData = new ArrayList<>();
        for (AnswerForResult answerForResult : answerForResults) {
            chartData.add(answerForResult.getAnswerCount());
        }
        questionForResult.setChartData(chartData);


        return questionForResult;
    }

}
