package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEn;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEnRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionEnRepository questionEnRepository;

    public QuestionEn getQuestionById(int id) {
        Optional<QuestionEn> questionEn = questionEnRepository.findById(id);

        if (questionEn.isPresent()) {
            System.err.println("=====q1id:" + questionEn.get().getId());
            //System.err.println("=====size:" + questionEn.get().getAnswersById().size());

            return questionEn.get();
        } else {
            return null;
        }
    }

    public QuestionEn getQuestionByOrderNumberAndQuestionnaireId(int orderNumber, int questionnaireId) {
        QuestionEn questionEn = questionEnRepository.findByOrderNumberAndQuestionnaireId(orderNumber, questionnaireId);
        return questionEn;
    }

    public void saveQuestionToDB(QuestionEn questionEn) {
        questionEnRepository.saveAndFlush(questionEn);
    }


    public void deleteQuestionFromDBById(Integer id) {
        questionEnRepository.deleteById(id);
    }

    public List<QuestionEn> getQuestionsByQuestionnaireId(Integer questionnaireId) {
        List<QuestionEn> questionEns = questionEnRepository.findByQuestionnaireId(questionnaireId);
        return questionEns;
    }

    public ArrayList<QuestionEn> getQuestionsByQuestionnaireIdSorted(Integer questionnaireId) {
        ArrayList<QuestionEn> questionEns = questionEnRepository.findByQuestionnaireIdOrderByOrderNumber(questionnaireId);
        return questionEns;
    }
}
