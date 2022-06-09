package pw.ee.sem4.zuza.questionnaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuestionnaireEnRepository extends JpaRepository<QuestionnaireEn, Integer> {

    List<QuestionnaireEn> findAllByNameOrderByIdDesc(String name);

    QuestionnaireEn findByIdAndCreated(Integer id, Date date);
}