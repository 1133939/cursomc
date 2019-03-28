package com.matheuscampelo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheuscampelo.cursomc.model.Categoria;
import com.matheuscampelo.cursomc.model.Produto;
import com.matheuscampelo.cursomc.repositories.CategoriaRepository;
import com.matheuscampelo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
private CategoriaRepository repositoryCategoria;	
	@Autowired
private ProdutoRepository repositoryProduto;	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	Categoria cat1 = new Categoria (null, "Informática");
	Categoria cat2 = new Categoria (null, "Escritório");
	
	Produto p1 = new Produto (null,"Computador",2000.00);
	Produto p2 = new Produto (null,"Impressora",800.00);
	Produto p3 = new Produto (null,"Mouse",80.00);

	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	repositoryCategoria.saveAll(Arrays.asList(cat1,cat2));
	repositoryProduto.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
