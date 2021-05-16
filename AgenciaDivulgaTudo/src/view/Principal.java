package view;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AnuncioTUI anuncioTUI = new AnuncioTUI(sc);
		anuncioTUI.menu();
		sc.close();
	}
}
