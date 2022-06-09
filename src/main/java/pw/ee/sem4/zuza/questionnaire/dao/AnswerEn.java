package pw.ee.sem4.zuza.questionnaire.dao;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "main")
public class AnswerEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "answer_text", nullable = false, length = 5000)
    private String answerText;
    @Basic
    @Column(name = "question_id", nullable = false, insertable = false, updatable = false)
    private Integer questionId;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private QuestionEn questionByQuestionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEn answerEn = (AnswerEn) o;

        if (id != null ? !id.equals(answerEn.id) : answerEn.id != null) return false;
        if (name != null ? !name.equals(answerEn.name) : answerEn.name != null) return false;
        if (answerText != null ? !answerText.equals(answerEn.answerText) : answerEn.answerText != null) return false;
        if (questionId != null ? !questionId.equals(answerEn.questionId) : answerEn.questionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        return result;
    }

    public QuestionEn getQuestionByQuestionId() {
        return questionByQuestionId;
    }

    public void setQuestionByQuestionId(QuestionEn questionByQuestionId) {
        this.questionByQuestionId = questionByQuestionId;
    }
}
