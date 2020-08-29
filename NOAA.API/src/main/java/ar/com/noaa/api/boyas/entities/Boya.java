package ar.com.noaa.api.boyas.entities;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "boya")
public class Boya {
	@Column(name = "boya_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "color_luz")
	private String color;
	@Column(name = "longitud_instalacion")
	private Double longitudInstalacion;
	@Column(name = "latitud_instalacion")
	private Double latitudInstalacion;

	@JsonIgnore
	@OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Muestra> muestras = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getLongitudInstalacion() {
		return longitudInstalacion;
	}

	public void setLongitudInstalacion(Double longitudInstalacion) {
		this.longitudInstalacion = longitudInstalacion;
	}

	public Double getLatitudInstalacion() {
		return latitudInstalacion;
	}

	public void setLatitudInstalacion(Double latitudInstalacion) {
		this.latitudInstalacion = latitudInstalacion;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

}
