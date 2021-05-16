package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.AnuncioBO;
import model.Anuncio;

public class AnuncioTUI {

	private Scanner sc;

	public AnuncioTUI(Scanner sc) {
		this.sc = sc;
	}

	public Date getDataInicioAnuncio() {
		Date dataInicio = null;
		boolean isFormatted = false;
		do {
			System.out.print("Informe a data de início do anúncio(DD/MM/AAAA): ");
			try {
				dataInicio = AnuncioBO.formatoDataAnuncio(sc.nextLine());
				isFormatted = true;
			} catch (ParseException e) {
				System.out.println("Erro no formato da data, ela deve ser informada com DD/MM/AAAA\n");
				sc.nextLine();
			}
		} while (!isFormatted);

		return dataInicio;
	}

	public Date getDataTerminoAnuncio() {
		Date dataTermino = null;
		boolean isFormatted = false;
		do {
			System.out.print("Informe a data de termino do anúncio(DD/MM/AAAA): ");
			try {
				dataTermino = AnuncioBO.formatoDataAnuncio(sc.nextLine());
				isFormatted = true;
			} catch (ParseException e) {
				System.out.println("Erro no formato da data, ela deve ser informada com DD/MM/AAAA\n");
				sc.nextLine();
			}
		} while (!isFormatted);
		return dataTermino;
	}

	public String getNomeCliente() {
		boolean isValidInput = false;
		String cliente = "";
		do {
			System.out.print("Informe o nome do cliente: ");
			try {
				cliente = sc.nextLine();
				isValidInput = true;
			} catch (Exception e) {
				System.out.println("Entrada errada, por favor tente novamente\n");
				sc.nextLine();
			}
		} while (!isValidInput);

		return cliente;
	}

	public String getNomeAnuncio() {
		boolean isValidInput = false;
		String anuncio = "";
		do {
			System.out.print("Informe o nome do anúncio: ");
			try {
				anuncio = sc.nextLine();
				isValidInput = true;
			} catch (Exception e) {
				System.out.println("Entrada errada, por favor tente novamente\n");
				sc.nextLine();
			}
		} while (!isValidInput);

		return anuncio;
	}

	public Double getValorInvestimentoPorDia() {
		Double investimentoPorDia = 0.0;
		boolean isValidInput = false;
		do {
			System.out.print("Informe o valor que será investido por dia: ");
			try {
				investimentoPorDia = sc.nextDouble();
				isValidInput = true;
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Entrada errada, por favor tente novamente\n");
				sc.nextLine();
			}
		} while (!isValidInput);
		return investimentoPorDia;
	}

	public void cadastrarAnuncio() {
		String nome = getNomeAnuncio();
		String cliente = getNomeCliente();
		Date dataInicio = getDataInicioAnuncio();
		Date dataTermino = getDataTerminoAnuncio();
		Double investimentoPorDia = getValorInvestimentoPorDia();
		AnuncioBO.cadastrarAnuncio(nome, cliente, dataInicio, dataTermino, investimentoPorDia);
		System.out.println("\nANÚNCIO CADASTRADO COM SUCESSO!\n");
	}

	public void gerarRelatorioAnuncios(List<Anuncio> anuncios) {
		if (anuncios == null || anuncios.isEmpty()) {
			System.out.println("\nSEM ANÚNCIOS CADASTRADOS.");
		} else {
			System.out.println("RELATÓRIO DE ANÚNCIOS");
			for (int i = 0; i < anuncios.size(); i++) {
				System.out.println("------------------------------------------------------------------");
				System.out.println("Nome do anúncio: " + anuncios.get(i).getNome());
				System.out.println("Valor total investido: " + anuncios.get(i).valorTotalInvestido());
				System.out.println("Quantidade máxima de visualizações: " + anuncios.get(i).qtdmaximaVisualizacoes());
				System.out.println("Quantidade máxima de cliques: " + anuncios.get(i).qtdmaximaCliques());
				System.out.println(
						"Quantidade máxima de compartilhamentos: " + anuncios.get(i).qtdMaximaCompartilhamentos());
				System.out.println("------------------------------------------------------------------");
			}

		}
	}

	public List<Anuncio> AnunciosIntervaloTempo(List<Anuncio> anuncios, Date dataInicio, Date dataFim) {
		List<Anuncio> anunciosIntervalo = new ArrayList<Anuncio>();
		if (anuncios != null && !anuncios.isEmpty()) {		
			for (Anuncio anuncio : anuncios) {
				if (anuncio.getDataInicio().getTime() >= dataInicio.getTime()
						&& anuncio.getDataTermino().getTime() <= dataFim.getTime()) {
					anunciosIntervalo.add(anuncio);
				}
			}
		}
		return anunciosIntervalo;
	}

	public List<Anuncio> AnunciosClientes(List<Anuncio> anuncios, String cliente) {
		List<Anuncio> anunciosCliente = new ArrayList<Anuncio>();
		if (anuncios != null && !anuncios.isEmpty()) {	
			for (Anuncio anuncio : anuncios) {
				if (anuncio.getCliente().equals(cliente)) {
					anunciosCliente.add(anuncio);
				}
			}
		}
		return anunciosCliente;
	}

	public List<Anuncio> AnunciosIntervaloTempoCliente(List<Anuncio> anuncios, Date dataInicio, Date dataFim,
			String cliente) {
		List<Anuncio> anunciosIntervaloCliente = new ArrayList<Anuncio>();
		if (anuncios != null && !anuncios.isEmpty()) {	
			List<Anuncio> anunciosIntervalo = AnunciosIntervaloTempo(anuncios, dataInicio, dataFim);
			anunciosIntervaloCliente = AnunciosClientes(anunciosIntervalo, cliente);
		}
		return anunciosIntervaloCliente;
	}

	public void menu() {
		int op = -1;
		System.out.println("****SISTEMA GERENCIADOR DE ANÚNCIOS****\n");
		do {
			System.out.println("\nFUNCIONALIDADES DISPONÍVEIS:");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Digite 1 para cadastrar um anúncio");
			System.out.println("Digite 2 para ver o relatório dos anúncios");
			System.out.println("Digite 3 para ver o relatório dos anúncios por intervalo de tempo");
			System.out.println("Digite 4 para ver o relatório dos anúncios por cliente");
			System.out.println("Digite 5 para ver o relatório dos anúncios por intervalo de tempo e por cliente");
			System.out.println("------------------------------------------------------------------");
			System.out.println("DIGITE 0 PARA SAIR");
			System.out.println("------------------------------------------------------------------");
			try {
				op = sc.nextInt();
				sc.nextLine();
				operacao(op);
			} catch (Exception e) {
				System.out.println("Entrada errada, por favor tente novamente\n");
				sc.nextLine();
			}
		} while (op != 0);
	}

	public void operacao(int op) {
		List<Anuncio> anuncios = null;
		if (op != 1) {
			anuncios = AnuncioBO.listaAnuncios();
		}
		switch (op) {
		case 1:
			cadastrarAnuncio();
			break;
		case 2:
			gerarRelatorioAnuncios(anuncios);
			break;
		case 3:
			gerarRelatorioAnuncios(
					AnunciosIntervaloTempo(anuncios, getDataInicioAnuncio(), getDataTerminoAnuncio()));
			break;
		case 4:
			gerarRelatorioAnuncios(AnunciosClientes(anuncios, getNomeCliente()));
			break;
		case 5:
			gerarRelatorioAnuncios(AnunciosIntervaloTempoCliente(anuncios, getDataInicioAnuncio(),
					getDataTerminoAnuncio(), getNomeCliente()));
			break;
		}
	}

}
