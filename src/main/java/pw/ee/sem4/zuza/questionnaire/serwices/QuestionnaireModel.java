package pw.ee.sem4.zuza.questionnaire.serwices;

import pw.ee.sem4.zuza.questionnaire.dao.QuestionnaireEn;

import java.util.Date;

public class QuestionnaireModel {
    int id;
    String name;
    String description;
    Date created;
    Boolean active;


    public QuestionnaireEn parseToEn (){
        QuestionnaireEn questionnaireEn = new QuestionnaireEn();

        questionnaireEn.setId(id);
        questionnaireEn.setName(name);
        questionnaireEn.setDescription(description);
        questionnaireEn.setCreated(created);
        questionnaireEn.setActive(active);

        return questionnaireEn;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionnaireModel that = (QuestionnaireModel) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionnaireModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", active=" + active +
                '}';
    }
}
