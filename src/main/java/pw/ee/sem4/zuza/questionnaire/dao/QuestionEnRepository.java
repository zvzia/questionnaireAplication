package pw.ee.sem4.zuza.questionnaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface QuestionEnRepository extends JpaRepository<QuestionEn, Integer> {
    List<QuestionEn> findAllByNameOrderByIdDesc(String name);
    QuestionEn findByOrderNumberAndQuestionnaireId(int orderNumber, int questionnaireId);

    List<QuestionEn> findByQuestionnaireId(Integer questionnaireId);
    ArrayList<QuestionEn> findByQuestionnaireIdOrderByOrderNumber(Integer questionnaireId);

}