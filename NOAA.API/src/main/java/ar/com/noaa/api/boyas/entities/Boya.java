package ar.com.noaa.api.boyas.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "boya")
public class Boya {
	@Column(name = "id_boya")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String color;
	private Double longitudInstalacion;
	private Double latitudInstalacion;

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
