package br.srkapi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.srkapi.api.models.User;

@Repository
public interface Repositorio extends CrudRepository<User, Integer>{
	
	List<User> findAll();
	
	User findByCodigo(int codigo);

	User findByNome(String nome);
	
	List <User> findByOrderByNomeAsc();
	
	List <User> findByNomeOrderByIdadeAsc(String nome);
	
	List <User> findByNomeContaining(String termo);
	
	List<User> findByNomeStartsWith(String termo);
	
	List<User> findByNomeEndsWith(String termo);
	
	@Query(value = "SELECT SUM(idade) FROM usuarios", nativeQuery = true)
	int somaIdades();
	
	@Query(value = "SELECT * FROM usuarios WHERE idade >= :idade", nativeQuery = true)
	List<User> idadeMaiorIgual(int idade);
	
	int countByCodigo(int codigo);
	
	
}
