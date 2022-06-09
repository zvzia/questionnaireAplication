package pw.ee.sem4.zuza.questionnaire.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "question", schema = "main")
public class QuestionEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "questionnaire_id", nullable = false, insertable = false, updatable = false)
    private Integer questionnaireId;
    @Basic
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    private String description;
    @Basic
    @Column(name = "type", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @Basic
    @Column(name = "question_text", nullable = false, length = 5000)
    private String questionText;
    @Basic
    @Column(name = "metrics", nullable = false)
    private Boolean metrics;

    @Basic
    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id", nullable = false)
    private QuestionnaireEn questionnaireByQuestionnaireId;
    @OneToMany(mappedBy = "questionByQuestionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEn> answersById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Boolean getMetrics() {
        return metrics;
    }

    public void setMetrics(Boolean metrics) {
        this.metrics = metrics;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEn that = (QuestionEn) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (questionnaireId != null ? !questionnaireId.equals(that.questionnaireId) : that.questionnaireId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null) return false;
        if (metrics != null ? !metrics.equals(that.metrics) : that.metrics != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionnaireId != null ? questionnaireId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + (metrics != null ? metrics.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        return result;
    }

    public List<AnswerEn> getAnswersById() {
        return answersById;
    }

    public void setAnswersById(List<AnswerEn> answersById) {
        this.answersById = answersById;
    }

    public QuestionnaireEn getQuestionnaireByQuestionnaireId() {
        return questionnaireByQuestionnaireId;
    }

    public void setQuestionnaireByQuestionnaireId(QuestionnaireEn questionnaireByQuestionnaireId) {
        this.questionnaireByQuestionnaireId = questionnaireByQuestionnaireId;
    }
}
