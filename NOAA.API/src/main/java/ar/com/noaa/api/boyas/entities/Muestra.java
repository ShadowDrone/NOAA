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
    private double longitud;
    private double latitud;
    private double alturaMar;
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

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getAlturaMar() {
        return alturaMar;
    }

    public void setAlturaMar(double alturaMar) {
        this.alturaMar = alturaMar;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
    }

}