package dao;

import java.util.ArrayList;
import java.util.List;

import model.Anuncio;

public class AnuncioDAO {
	private static List<Anuncio> anuncios = new ArrayList<Anuncio>();
	
	public static void adicionar(Anuncio anuncio) {
		anuncios.add(anuncio);
	}
	
	public static List<Anuncio> getAnuncios() {
		return new ArrayList<Anuncio>(anuncios);
	}
}
