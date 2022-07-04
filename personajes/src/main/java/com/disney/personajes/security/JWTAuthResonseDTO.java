package com.disney.personajes.security;

public class JWTAuthResonseDTO {

    private String tokenDeAcceso;
    private String tipoDeTokem = "Bearer";

    public JWTAuthResonseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTokenDeAcceso() {
        return tokenDeAcceso;
    }

    public void setTokenDeAcceso(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTipoDeTokem() {
        return tipoDeTokem;
    }

    public void setTipoDeTokem(String tipoDeTokem) {
        this.tipoDeTokem = tipoDeTokem;
    }

    public JWTAuthResonseDTO() {
    }

    public JWTAuthResonseDTO(String tokenDeAcceso, String tipoDeTokem) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeTokem = tipoDeTokem;
    }
}
