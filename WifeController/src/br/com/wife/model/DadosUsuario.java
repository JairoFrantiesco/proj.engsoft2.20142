package br.com.wife.model;

public class DadosUsuario {
	
		private String nome;
		private Integer tempoAtualizacao; //Em minutos :P
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getTempoAtualizacao() {
			return tempoAtualizacao;
		}
		public void setTempoAtualizacao(Integer tempoAtualizacao) {
			this.tempoAtualizacao = tempoAtualizacao;
		}

}
