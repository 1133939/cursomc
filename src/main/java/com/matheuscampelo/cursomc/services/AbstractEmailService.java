package com.matheuscampelo.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.matheuscampelo.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	
	
@Override
public void sendOrderConfirmationEmail(Pedido pedido) {
	SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
	sendEmail(sm);
}

protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(pedido.getCliente().getEmail());
	sm.setFrom(sender);
	sm.setSubject("Pedido Confirmado - Código: "+pedido.getId());
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText(pedido.toString());
	return sm;
}

}
