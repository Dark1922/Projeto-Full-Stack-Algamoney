package br.com.algamoney.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algamoney.api.model.Categoria;
import br.com.algamoney.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaResource {

	private final CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
}
