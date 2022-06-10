package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;
import pw.ee.sem4.zuza.questionnaire.dao.AnswerEnRepository;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionEnRepository;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEn;

import java.util.List;
import java.util.Optional;
@Service
public class AnswerService {
    @Autowired
    AnswerEnRepository answerEnRepository ;

    public AnswerEn getAnswerById(int id) {
        Optional<AnswerEn> answerEn = answerEnRepository.findById(id);

        if (answerEn.isPresent()) {
            System.err.println("=====q1id:" + answerEn.get().getId());

            return answerEn.get();
        } else {
            return null;
        }
    }

    public List<AnswerEn> getAllAnswers() {
        List<AnswerEn> answerEnList = answerEnRepository.findAll();
        return answerEnList;
    }

    public void saveAnswerToDB(AnswerEn answerEn) {
        answerEnRepository.saveAndFlush(answerEn);
    }


    public void deleteAnswerFromDBById(Integer id) {
        answerEnRepository.deleteById(id);
    }
}
