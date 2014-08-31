package br.com.wife.controller;

import java.util.ArrayList;

import br.com.wife.model.Rastreamento;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RastreamentoController control = new RastreamentoController();
		
		ArrayList<Rastreamento> test = control.getAll();
		
		System.out.println(test.size());
		
	}

}
