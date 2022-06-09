package pw.ee.sem4.zuza.questionnaire.dao;

public enum QuestionType {
    CLOSED_LIMITED( "Zamknięte, ograniczone"),
    CLOSED_UNLIMITED("Zamknięte, nieograniczone"),
    OPEN ("Otwarte");


    String desc4web;

    QuestionType(String desc4web) {
        this.desc4web = desc4web;
    }

    public String getDesc4web() {
        return desc4web;
    }
}
