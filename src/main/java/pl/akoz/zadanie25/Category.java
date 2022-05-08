package pl.akoz.zadanie25;

public enum Category {

    DOM("HOUSE"),
    OGRÓD("GARDEN"),
    SAMOCHÓD("CAR");

    public final String translation;

    Category(String translation) {
        this.translation = translation;
    }
}
