package pw.ee.sem4.zuza.questionnaire.dao;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questionnaire", schema = "main")
public class QuestionnaireEn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    private String description;
    @Basic
    @Column(name = "created", nullable = false)
    private Date created;
    @Basic
    @Column(name = "active", nullable = false)
    private Boolean active;
    @OneToMany(mappedBy = "questionnaireByQuestionnaireId" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy ("orderNumber asc ")
    private Collection<QuestionEn> questionsById;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionnaireEn that = (QuestionnaireEn) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    public Collection<QuestionEn> getQuestionsById() {
        return questionsById;
    }


    public void setQuestionsById(Collection<QuestionEn> questionsById) {
        this.questionsById = questionsById;
    }

    public ArrayList<Integer> getQuestionsIds(){
        ArrayList<Integer> ids = new ArrayList<>();

        for (QuestionEn questionEn : questionsById) {
            ids.add(questionEn.getId());
        }

        return ids;
    }
}
