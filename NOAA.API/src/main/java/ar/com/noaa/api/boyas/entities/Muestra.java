package ar.com.noaa.api.boyas.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "muestra")
public class Muestra {
    @Column(name = "id_muestra")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date horario;
    private String matricula;
    private Double longitud;
    private Double latitud;
    private Double alturaMar;
    @ManyToOne
    @JoinColumn(name = "id_boya", referencedColumnName = "id_boya")
    private Boya boya;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getAlturaMar() {
        return alturaMar;
    }

    public void setAlturaMar(Double alturaMar) {
        this.alturaMar = alturaMar;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
    }

}