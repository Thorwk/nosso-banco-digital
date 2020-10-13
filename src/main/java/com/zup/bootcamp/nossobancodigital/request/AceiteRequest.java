package com.zup.bootcamp.nossobancodigital.request;

import javax.validation.constraints.NotBlank;

public class AceiteRequest {

    @NotBlank(message = "Aceite é obrigatório!")
    private String aceite;

    public AceiteRequest() { }

    public String getAceite() { return aceite; }

    public void setAceite(String aceite) { this.aceite = aceite; }
}
