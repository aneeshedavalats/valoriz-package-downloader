package com.valoriz.pd.enums;

import lombok.Getter;

@Getter
public enum Project {

    AMB("Ambient 360"), GMG("GMG"), MGB("Magribi");

    private final String value;

    Project(String value) {
        this.value = value;
    }
}
