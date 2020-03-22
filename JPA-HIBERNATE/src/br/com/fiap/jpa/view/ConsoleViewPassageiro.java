package br.com.fiap.jpa.view;

import java.util.Calendar;

import br.com.fiap.jpa.dao.PassageiroDAO;
import br.com.fiap.jpa.entity.Genero;
import br.com.fiap.jpa.entity.Passageiro;
import br.com.fiap.jpa.exception.FailCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;
import br.com.fiap.jpa.impl.PassageiroDAOImpl;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class ConsoleViewPassageiro {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		PassageiroDAO dao = new PassageiroDAOImpl(em);
		Passageiro passageiro = new Passageiro(1,"Ricardo Shibuya",Calendar.getInstance(),Genero.MASCULINO);
		
		try {
			dao.create(passageiro);
			dao.commit();
			System.out.println("Passageiro criado com sucesso");
		}catch(FailCommitException e) {
			System.out.println("Falha ao criar passageiro");
		}
		
		try {
			Passageiro busca = dao.read(1);
			System.out.println(busca.getNome());
		}catch(KeyNotFoundException e) {
			System.out.println("Nome do passageiro n�o localizado");
		}
		
		try {
			passageiro.setNome("Marcelo Shibuya");
			dao.update(passageiro);
			dao.commit();
			System.out.println("Nome atualizado");
		}catch(FailCommitException e) {
			System.out.println("N�o foi poss�vel atualizar o nome");
		}
		
		try {
			dao.delete(1);
			dao.commit();
			System.out.println("Passageiro removido");
		}catch(KeyNotFoundException e) {
			System.out.println("Passageiro n�o localizado");
		}catch(FailCommitException e) {
			System.out.println("Erro ao remover passageiro");
		}
		
		em.close();
		fabrica.close();

	}

}
