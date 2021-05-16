package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Anuncio {
	private String nome;
	private String cliente;
	private Date dataInicio;
	private Date dataTermino;
	private Double investimentoPorDia;	
	
	public Anuncio(String nome, String cliente, Date dataInicio, Date dataTermino, Double investimentoPorDia) {
		this.nome = nome;
		this.cliente = cliente;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.investimentoPorDia = investimentoPorDia;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataTermino() {
		return dataTermino;
	}
	
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	
	public Double getInvestimentoPorDia() {
		return investimentoPorDia;
	}
	
	public void setInvestimentoPorDia(Double investimentoPorDia) {
		this.investimentoPorDia = investimentoPorDia;
	}
	
	public Double valorTotalInvestido() {
		long diferenca = Math.abs(this.dataTermino.getTime() - this.dataInicio.getTime());
		long diff = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
		return this.investimentoPorDia * diff;
	}
	
	public int qtdmaximaVisualizacoes() {
		return Calculadora.projecaoVisualizacoes(this.valorTotalInvestido());
	}
	
	public int qtdmaximaCliques() {
		return Calculadora.projecaoCliques(this.valorTotalInvestido());
	}
	
	public int qtdMaximaCompartilhamentos() {
		return Calculadora.projecaoCompartilhamento(this.valorTotalInvestido());
	}
	
}
