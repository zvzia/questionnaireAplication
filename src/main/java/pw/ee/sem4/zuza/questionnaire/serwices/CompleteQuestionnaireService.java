package pw.ee.sem4.zuza.questionnaire.serwices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.ee.sem4.zuza.questionnaire.dao.AnswerEn;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEn;
import pw.ee.sem4.zuza.questionnaire.dao.CompleteAnswersEnRepository;
import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEnRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CompleteQuestionnaireService {

    @Autowired
    CompleteAnswersEnRepository completeAnswersEnRepository;
    public Integer getCompletedQuestionCountForIntervieweeId(String intervieweeId) {
        List <CompleteAnswersEn> completeAnswersEn = completeAnswersEnRepository.findByIntervieweeId(intervieweeId);

        return completeAnswersEn.size();
    }

}
