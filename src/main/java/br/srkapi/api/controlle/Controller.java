package br.srkapi.api.controlle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.srkapi.api.models.Cliente;
import br.srkapi.api.models.User;
import br.srkapi.api.repository.Repositorio;
import br.srkapi.api.servico.Servico;
import jakarta.validation.Valid;


@RestController
public class Controller {

	@Autowired
	private Repositorio acao;
	
	@Autowired Servico servico;
	
	@PostMapping("/api")
	public ResponseEntity<?> cadastrar( @RequestBody User obj ) {
		return servico.cadastrar(obj);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> selecionar(){
		return servico.selecionar();
	}
	
	@GetMapping("/api/contador")
	public long contador() {
		return acao.count();
	}
	
	@GetMapping("/api/nomes")
	public List<User> ordenarNomes(){
		return acao.findByOrderByNomeAsc();
	}
	
	@GetMapping("/api/ordenarNomes/{nome}")
	public List<User> ordernarNomes(@PathVariable String nome){
		return acao.findByNomeOrderByIdadeAsc(nome);
	}

	@GetMapping("/api/nomeContain/{termo}")
	public List<User> nomeContain(@PathVariable String termo){
		return acao.findByNomeContaining(termo);
	}
	
	@GetMapping("/api/comecacom/{termo}")
	public List<User> comecaCom(@PathVariable String termo){
		return acao.findByNomeStartsWith(termo);
	}
	
	@GetMapping("/api/terminacom/{termo}")
	public List<User> terminaCom(@PathVariable String termo){
		return acao.findByNomeEndsWith(termo);
	}
	
	@GetMapping("/api/somaidades")
	public int somaIdades() {
		return acao.somaIdades();
	}
	
	@GetMapping("/api/idadeMaiorIgual/{termo}")
	public List<User> idadeMaior(@PathVariable int termo){
		return acao.idadeMaiorIgual(termo);
	}
	
	@PutMapping("/api")
	public ResponseEntity<?> editar(@RequestBody User obj) {
		return servico.editar(obj);
	}
	
	@GetMapping("/api/{codigo}")
	public ResponseEntity<?> selecionarPorCodigo(@PathVariable int codigo){
			return servico.selecionarPeloCodigo(codigo);
	}
	
	@GetMapping("/api/nomes/{nome}")
	public User selecionarPorNome(@PathVariable String nome) {
			return acao.findByNome(nome);
	}
	
	@DeleteMapping("/api/{codigo}")
	public ResponseEntity<?> remover(@PathVariable int codigo) {
		return servico.remover(codigo);
	}
	
	@PostMapping("/user")
	public User membros(@RequestBody User x) {
		return x;
	}
	
	@GetMapping("/status")
	public ResponseEntity<?> status() {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/cliente")
	public void cliente(@Valid @RequestBody Cliente obj) {
		
	}
	
}
