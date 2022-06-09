package pw.ee.sem4.zuza.questionnaire.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "complete_answers", schema = "main", catalog = "")
public class CompleteAnswersEn{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "interviewee_id", nullable = false)
    private String intervieweeId;
    @Basic
    @Column(name = "answer_date", nullable = false)
    private Date answerDate;
    @Basic
    @Column(name = "questionaire_id", nullable = false)
    private Integer questionaireId;
    @Basic
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    @Basic
    @Column(name = "answer_id", nullable = false)
    private Integer answerId;
    @Basic
    @Column(name = "answer_value", nullable = true, length = 1000)
    private String answerValue;
    @Basic
    @Column(name = "answer_desc", nullable = true, length = 1000)
    private String answerDesc;
    @Basic
    @Column(name = "question_order_number", nullable = true)
    private Integer questionOrderNumber;

    public Integer getQuestionOrderNumber() {
        return questionOrderNumber;
    }

    public void setQuestionOrderNumber(Integer questionOrderNumber) {
        this.questionOrderNumber = questionOrderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntervieweeId() {
        return intervieweeId;
    }

    public void setIntervieweeId(String intervieweeId) {
        this.intervieweeId = intervieweeId;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Integer getQuestionaireId() {
        return questionaireId;
    }

    public void setQuestionaireId(Integer questionaireId) {
        this.questionaireId = questionaireId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getAnswerDesc() {
        return answerDesc;
    }

    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompleteAnswersEn that = (CompleteAnswersEn) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (intervieweeId != null ? !intervieweeId.equals(that.intervieweeId) : that.intervieweeId != null)
            return false;
        if (answerDate != null ? !answerDate.equals(that.answerDate) : that.answerDate != null) return false;
        if (questionaireId != null ? !questionaireId.equals(that.questionaireId) : that.questionaireId != null)
            return false;
        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;
        if (answerId != null ? !answerId.equals(that.answerId) : that.answerId != null) return false;
        if (answerValue != null ? !answerValue.equals(that.answerValue) : that.answerValue != null) return false;
        if (answerDesc != null ? !answerDesc.equals(that.answerDesc) : that.answerDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (intervieweeId != null ? intervieweeId.hashCode() : 0);
        result = 31 * result + (answerDate != null ? answerDate.hashCode() : 0);
        result = 31 * result + (questionaireId != null ? questionaireId.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        result = 31 * result + (answerValue != null ? answerValue.hashCode() : 0);
        result = 31 * result + (answerDesc != null ? answerDesc.hashCode() : 0);
        return result;
    }


}
