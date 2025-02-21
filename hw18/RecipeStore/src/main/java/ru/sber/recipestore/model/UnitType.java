package ru.sber.recipestore.model;

public enum UnitType {
    GRAM("гр."),
    MILLILITER("мл."),
    PIECE("шт.");

    private String value;

    UnitType(String value) {
        this.value = value;
    }

    public String getDisplayValue() {
        return this.value;
    }

}
