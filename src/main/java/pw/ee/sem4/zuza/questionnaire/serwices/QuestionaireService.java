/*
 * Created by .
 * User: gaika
 * Date: 08.04.2022
 * Time: 20:49
 * GóZIK
 */
package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEnRepository;

import java.util.*;

@Service
public class QuestionaireService {

    @Autowired
    QuestionnaireEnRepository questionnaireEnRepository;

    public String metoda(String str) {
        System.err.println("Metoda dostała w serwisie" + str);
        return str + "returned";
    }


    public List<QuestionnaireEn> getAllQuestionnaires() {
        List<QuestionnaireEn> questionnaireEnList = questionnaireEnRepository.findAll();
        return questionnaireEnList;
    }

    public QuestionnaireEn getQuestionnaireById(int id) {
        Optional<QuestionnaireEn> questionnaireEn = questionnaireEnRepository.findById(id);

        return questionnaireEn.orElse(null);
    }

    public void saveQuestionnaireToDB(QuestionnaireEn questionnaireEn) {
        questionnaireEnRepository.saveAndFlush(questionnaireEn);
    }

    public void deleteQuestionnaireFromDB(QuestionnaireEn questionnaireEn) {
        questionnaireEnRepository.delete(questionnaireEn);
    }

    public void deleteQuestionnaireFromDBById(Integer id) {
        questionnaireEnRepository.deleteById(id);
    }

    public QuestionnaireEn findQuestionnaireByIdAndCreated(String webParam) {
        String[] parts = webParam.split("_");
        int questionnaireId = Integer.parseInt(parts[0]);
        Date questionnaireDate = new Date(Long.parseLong(parts[1]));

        QuestionnaireEn questionnaireEn = questionnaireEnRepository.findByIdAndCreated(questionnaireId, questionnaireDate);

        return questionnaireEn;
    }
}
