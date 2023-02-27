package com.rabbit.dto;

import java.io.Serializable;

public class TestDTO implements Serializable {
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
