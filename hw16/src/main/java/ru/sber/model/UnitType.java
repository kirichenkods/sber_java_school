package ru.sber.model;

public enum UnitType {
    GRAM("гр."),
    MILLILITER("мл."),
    PIECE("шт.");

    private String value;

    UnitType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
