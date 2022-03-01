package br.com.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.algamoney.api.model.Categoria;
import br.com.algamoney.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaResource {

	private final CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria caregoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(caregoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(categoriaSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long id) {
		  Optional<Categoria> categoria = categoriaRepository.findById(id);
	        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
}
