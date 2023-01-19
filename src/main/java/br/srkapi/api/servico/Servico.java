package br.srkapi.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.srkapi.api.models.Mensagem;
import br.srkapi.api.models.User;
import br.srkapi.api.repository.Repositorio;

@Service
public class Servico {

	@Autowired 
	private Mensagem mensagem;
	
	@Autowired 
	private Repositorio acao;
	
	// validação para o cadastro de pessoas;
	public ResponseEntity<?> cadastrar(User obj){
		if(obj.getNome().equals("")) {
			mensagem.setMensagem("O nome precisa ser preenchiodo");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else if(obj.getIdade() < 0) {
			mensagem.setMensagem(("A idade informada é inválida"));
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
		}
	}
	// método para selecionar pessoas
	public ResponseEntity<?> selecionar(){
		return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
	}
	// método para selecionar pessoas atraves do código;
	public ResponseEntity<?> selecionarPeloCodigo(int codigo){
		if(acao.countByCodigo(codigo) == 0 ) {
			mensagem.setMensagem("Nenhum usuário possui esse código");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
		}
	}
	// Método para editar dados
	public ResponseEntity<?> editar(User obj){
		if(acao.countByCodigo(obj.getCodigo()) == 0) {
			mensagem.setMensagem("Codigo inexistente");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}else if(obj.getNome().equals("")) {
			mensagem.setMensagem("Nome inválido");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else if(obj.getIdade() < 0) {
			mensagem.setMensagem("Idade inválida");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
		}
	}
	// Método para remover dados;
	public ResponseEntity<?> remover(int codigo){
		if(acao.countByCodigo(codigo) == 0){
			mensagem.setMensagem("Usuário não encontrado");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}else {
			User obj = acao.findByCodigo(codigo);
			acao.delete(obj);
			mensagem.setMensagem("Usuário removido com sucesso");
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
	}
	
}
