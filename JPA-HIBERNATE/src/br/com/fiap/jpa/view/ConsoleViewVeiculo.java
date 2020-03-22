package br.com.fiap.jpa.view;

import java.util.Calendar;

import br.com.fiap.jpa.dao.VeiculoDAO;
import br.com.fiap.jpa.entity.Veiculo;
import br.com.fiap.jpa.exception.FailCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;
import br.com.fiap.jpa.impl.VeiculoDAOImpl;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class ConsoleViewVeiculo {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		VeiculoDAO dao = new VeiculoDAOImpl(em);
		br.com.fiap.jpa.entity.Veiculo veiculo = new Veiculo(1,"DSF-1212","Branco",Calendar.getInstance());
		
		try {
			dao.create(veiculo);
			dao.commit();
			System.out.println("Veiculo criado");
		}catch(FailCommitException e) {
			System.out.println("N�o foi poss�vel criar um veiculo");
		}
		
		try {
			Veiculo busca = dao.read(1);
			System.out.println(busca.getPlaca());
		}catch(KeyNotFoundException e) {
			System.out.println("N�o foi poss�vel localizar a placa");
		}
		
		try {
			veiculo.setCor("Azul");
			dao.update(veiculo);
			dao.commit();
			System.out.println("Cor do ve�culo atualizado");
		}catch(FailCommitException e) {
			System.out.println("N�o foi poss�vel atualizar a cor do ve�culo");
		}
		
		try {
			dao.delete(1);
			dao.commit();
			System.out.println("Veiculo removido");
		}catch(KeyNotFoundException e) {
			System.out.println("Ve�culo n�o encontrado");
		}catch(FailCommitException e) {
			System.out.println("N�o foi poss�vel remover o ve�culo");
		}
		
		em.close();
		fabrica.close();
		
		

	}

}
