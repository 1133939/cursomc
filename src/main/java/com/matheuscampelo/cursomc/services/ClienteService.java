package com.matheuscampelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.dto.ClienteDTO;
import com.matheuscampelo.cursomc.dto.ClienteNewDTO;
import com.matheuscampelo.cursomc.model.Cidade;
import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.model.Endereco;
import com.matheuscampelo.cursomc.model.enums.TipoCliente;
import com.matheuscampelo.cursomc.repositories.CidadeRepository;
import com.matheuscampelo.cursomc.repositories.ClienteRepository;
import com.matheuscampelo.cursomc.repositories.EnderecoRepository;
import com.matheuscampelo.cursomc.services.exceptions.DataIntegrityException;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
@Autowired
private ClienteRepository repository;
@Autowired
private CidadeRepository repoCidade;
@Autowired
private EnderecoRepository repoEndereco;



public Cliente find(Integer id){
Optional <Cliente> cliente= repository.findById(id);
return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "
+ id +", Tipo: " + Cliente.class.getName() ));
}
public Cliente insert(Cliente cliente) {
	cliente.setId(null);
	cliente = repository.save(cliente);
	repoEndereco.saveAll(cliente.getEnderecos());
	return cliente;
}

public Cliente update(Cliente cliente) {
	Cliente newObj = find(cliente.getId());
	updateData(newObj, cliente);
	return repository.save(newObj);
}
public void delete(Integer id) {
	find(id);
	try {
	repository.deleteById(id);
	}catch (DataIntegrityViolationException e) {
		throw new DataIntegrityException("Não é possivel excluir Cliente que possui Pedidos");
	}
}
public List<Cliente> findAll(){
	return repository.findAll();
}
public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String direction){
	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
	return repository.findAll(pageRequest);
	
}
public Cliente fromDTO(ClienteDTO clienteDTO) {
	return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(),null,null);
}
public Cliente fromDTO(ClienteNewDTO clienteDTO) {
	Cliente cli = new Cliente(null, clienteDTO.getNome(),clienteDTO.getEmail(),clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()));
	Optional <Cidade> cid = repoCidade.findById(clienteDTO.getCidadeId());
	Endereco end = new Endereco(null, clienteDTO.getLogradouro(),clienteDTO.getNumero(),clienteDTO.getComplemento(),clienteDTO.getBairro(),clienteDTO.getCep(),cli,cid.orElse(null));
	cli.getEnderecos().add(end);
	cli.getTelefones().add(clienteDTO.getTelefone1());
	if(clienteDTO.getTelefone2()!=null) {
	cli.getTelefones().add(clienteDTO.getTelefone2());	
	}
	if(clienteDTO.getTelefone3()!=null) {
	cli.getTelefones().add(clienteDTO.getTelefone3());	
	}
	
	return cli;
}
private void updateData(Cliente newObj, Cliente cliente) {
	newObj.setNome(cliente.getNome());
	newObj.setEmail(cliente.getEmail());
}
}
