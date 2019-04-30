package com.matheuscampelo.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.matheuscampelo.cursomc.model.Pedido;

public interface EmailService {
void sendOrderConfirmationEmail(Pedido pedido);

void sendEmail(SimpleMailMessage msg);

void sendOrderConfirmationHtmlEmail(Pedido pedido);

void sendHtmlEmail(MimeMessage msg);
}
