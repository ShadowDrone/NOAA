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
	private double longitudInstalacion;
	private double latitudInstalacion;

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

	public double getLongitudInstalacion() {
		return longitudInstalacion;
	}

	public void setLongitudInstalacion(double longitudInstalacion) {
		this.longitudInstalacion = longitudInstalacion;
	}

	public double getLatitudInstalacion() {
		return latitudInstalacion;
	}

	public void setLatitudInstalacion(double latitudInstalacion) {
		this.latitudInstalacion = latitudInstalacion;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

}
