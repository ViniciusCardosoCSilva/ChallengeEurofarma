package br.com.eurofarma.infoQuik.model;

public enum UserRole {
    TREINADOR("treinador"),
    FUNCIONARIO("funcionario");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
