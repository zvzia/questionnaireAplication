package pw.ee.sem4.zuza.questionnaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerEnRepository extends JpaRepository<AnswerEn, Integer> {

}
