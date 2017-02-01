package br.com.autoservice.mb;

import br.com.autoservice.modelo.Agendamento;

public class TesteE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String t = null;
		
		System.out.println("teste de null "+t);
		
		
		Agendamento a = new Agendamento();
		
		a.setDescricao(t);
		
		System.out.println(a.getDescricao());

	}

}
