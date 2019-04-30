package com.matheuscampelo.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheuscampelo.cursomc.model.ItemPedido;
import com.matheuscampelo.cursomc.model.PagamentoComBoleto;
import com.matheuscampelo.cursomc.model.Pedido;
import com.matheuscampelo.cursomc.model.enums.EstadoPagamento;
import com.matheuscampelo.cursomc.repositories.ItemPedidoRepository;
import com.matheuscampelo.cursomc.repositories.PagamentoRepository;
import com.matheuscampelo.cursomc.repositories.PedidoRepository;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
@Autowired
private PedidoRepository repository;
@Autowired
private PagamentoRepository pagamentoRepository;
@Autowired 
private ProdutoService produtoService;
@Autowired
private ItemPedidoRepository itemPedidoRepository;
@Autowired
private BoletoService boletoService;
@Autowired
private ClienteService clienteService;
@Autowired
private EmailService emailService;

public Pedido find(Integer id) {
	Optional<Pedido> pedido = repository.findById(id);
	return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "+
	id+" Tipo: "+Pedido.class.getName()));
}
@Transactional
public Pedido insert(Pedido pedido) {
	pedido.setId(null);
	pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
	pedido.setInstante(new Date());
	pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
	pedido.getPagamento().setPedido(pedido);
	if(pedido.getPagamento() instanceof PagamentoComBoleto) {
		PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
		boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
	}
	pedido = repository.save(pedido);
	
	pagamentoRepository.save(pedido.getPagamento());
	for(ItemPedido itemPedido : pedido.getItens()) {
		itemPedido.setDesconto(0d);
		itemPedido.setProduto(produtoService.find(itemPedido.getProduto().getId()));
		itemPedido.setPreco(itemPedido.getProduto().getPreco());
		itemPedido.setPedido(pedido);
		
	}
	itemPedidoRepository.saveAll(pedido.getItens());
	emailService.sendOrderConfirmationHtmlEmail(pedido);
	return pedido;
	 
	
}
}
