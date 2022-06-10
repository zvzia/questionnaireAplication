package pw.ee.sem4.zuza.questionnaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface CompleteAnswersEnRepository extends JpaRepository<CompleteAnswersEn, Integer> {

    List<CompleteAnswersEn> findByIntervieweeId(String intervieweeId);

    ArrayList<CompleteAnswersEn> findByIntervieweeIdOrderByQuestionOrderNumber(String intervieweeId);

    @Query(value = "SELECT COUNT(*) FROM complete_answers WHERE answer_id=:answerId", nativeQuery = true)
    Integer getAnswerCountByAnswerId (Integer answerId);

    @Query(value = "SELECT answer_value FROM complete_answers WHERE question_id=:questionId", nativeQuery = true)
    Collection<String> getOpenAnswersByQuestionId (Integer questionId);

    ArrayList<CompleteAnswersEn> findByIntervieweeIdAndQuestionId(String intervieweeId, Integer questionId);
}
