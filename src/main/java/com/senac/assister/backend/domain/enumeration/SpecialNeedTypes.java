package com.senac.assister.backend.domain.enumeration;

public enum SpecialNeedTypes {
    PHYSICAL_DISABILITY("PHYSICAL_DISABILITY"),
    VISUAL_DISABILITY("VISUAL_DISABILITY"),
    MENTAL_DISABILITY("MENTAL_DISABILITY"),
    HEARING_DISABILITY("HEARING_DISABILITY"),
    ELDERLY("ELDERLY");

    private final String text;

    SpecialNeedTypes(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
