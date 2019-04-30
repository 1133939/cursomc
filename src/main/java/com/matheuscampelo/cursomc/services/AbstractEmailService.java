package com.matheuscampelo.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.matheuscampelo.cursomc.model.Pedido;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
	
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
protected String htmlFromTemplatePedido(Pedido pedido) {
	Context context = new Context();
	context.setVariable("pedido", pedido);
	return templateEngine.process("email/confirmacaoPedido", context);
}
public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
	try {
	MimeMessage mm = prepareMimeMessageFromPedido(pedido);
	sendHtmlEmail(mm);
}catch(MessagingException e) {
	sendOrderConfirmationEmail(pedido);
}
}

private MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
	mmh.setTo(pedido.getCliente().getEmail());
	mmh.setFrom(sender);
	mmh.setSubject("Confirmação de Pedido, número: "+pedido.getId());
	mmh.setSentDate(new Date(System.currentTimeMillis()));
	mmh.setText(htmlFromTemplatePedido(pedido), true);
	return mimeMessage;
}

}
