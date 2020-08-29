package ar.com.noaa.api.boyas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.noaa.api.boyas.services.MuestraService;
import ar.com.noaa.api.boyas.utils.GeoUtils;

@SpringBootTest
class BoyasApplicationTests {

	@Autowired
	MuestraService muestraService;

	@Test
	void muestraChequearAlturaRoja() {
		// Nuestro test Unitario
		// Dado la altura 120, esperamos que el valor de respuesta sea ROJO

		String colorResultante = muestraService.encontrarColor(120.0);

		// assertTrue(colorResultante.equals("ROJO"));
		assertEquals("ROJO", colorResultante);

	}

	@Test
	void muestraChequearQueAlturaChicaNoSeaRoJO() {
		// TestUnitario: quiero chequear que una altura chica,(ej algo menor a 80)
		// devuelva cualquier cosa, pero no ROJO
		String colorResultante = muestraService.encontrarColor(79.0);

		assertNotEquals("ROJO", colorResultante);

	}

	@Test
	void muestraChequearHemisferioLaura() {
		String hemisferio = GeoUtils.chequearHemisferio(46.22);

		assertEquals("NORTE", hemisferio);
	}

	// Validar que una muestra de 10 de de color VERDE
	@Test
	void muestraChequearColorVerdeYda() {
		String colorResultante = muestraService.encontrarColor(10.0);
		assertEquals("VERDE", colorResultante);
	}

	// Validar que una muestra de 180 NO de color AMARILLO melisa
	@Test
	void muestraChequearNoColorVerde() {
		String colorResultante = muestraService.encontrarColor(180.0);
		assertNotEquals("AMARILLO", colorResultante);

	}

	@Test
	void muestraChequearQueAlturaSeaRojoDigneli() {
		String colorResultante = muestraService.encontrarColor(120.0);

		assertEquals("ROJO", colorResultante);
	}

	@Test
	void chequearCoordenadasEstenRangoNorteDigneli() {

		Double latitud = 80.00;
		String coordenada = GeoUtils.chequearHemisferio(latitud);
		assertEquals("NORTE", coordenada);
	}

	@Test
	void muestraChequearQueMuestraSeaAmarilloAndrea() {
		String colorResultante = muestraService.encontrarColor(-55.0);

		assertEquals("AMARILLO", colorResultante);

	}

	@Test
	void validarNoEnRangoPlanetario() {
		boolean rangoPlanetario = GeoUtils.rangoPlanetario(91.0, 181.0);
		assertFalse(rangoPlanetario, "No en el rango");
	}

	@Test
	void muestraChequearQueMuestraSeaVerdeAndrea() {

		String colorResultante = muestraService.encontrarColor(10.0);

		assertEquals("VERDE", colorResultante);
	}

	@Test
	void muestraChequearQueMuestraNoSeaAmarilloAndrea() {

		String colorResultante = muestraService.encontrarColor(180.0);
		assertNotEquals("AMARILLO", colorResultante);
	}

	@Test
	void muestraChequearQueNoSeaVerdeAndrea() {
		String colorResultante = muestraService.encontrarColor(-100.0);
		assertNotEquals("VERDE", colorResultante);

	}

	@Test
	void muestraChequearQueSeaRojoAndrea() {
		String colorResultante = muestraService.encontrarColor(120.0);
		assertEquals("ROJO", colorResultante);
	}

}
