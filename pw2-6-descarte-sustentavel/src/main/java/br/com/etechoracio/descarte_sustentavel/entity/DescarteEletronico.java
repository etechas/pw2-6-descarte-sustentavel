package br.com.etechoracio.descarte_sustentavel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "descarte_eletronico")
public class DescarteEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String matriculaAluno;

    @NotBlank
    @Column(nullable = false)
    private String tipoItem;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double pesoGramas;

    private Integer pontosGerados;

    private Boolean validado;

    public DescarteEletronico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Double getPesoGramas() {
        return pesoGramas;
    }

    public void setPesoGramas(Double pesoGramas) {
        this.pesoGramas = pesoGramas;
    }

    public Integer getPontosGerados() {
        return pontosGerados;
    }

    public void setPontosGerados(Integer pontosGerados) {
        this.pontosGerados = pontosGerados;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }
}
