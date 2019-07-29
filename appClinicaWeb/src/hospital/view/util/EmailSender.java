package hospital.view.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public static void enviarMail(String para, String asunto, String msg)
			throws MessagingException, UnsupportedEncodingException {
		String emailDe = "xxxxxxxxx@gmail.com";
		String password = "xxxxxxxxxx";

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true"); // Seguridad de la capa de transporte
		props.put("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", emailDe); // Nombre del usuario
		props.put("mail.smtp.auth", "true"); // Si requiere o no usuario y password para conectarse.
		Session sesionMensaje = Session.getDefaultInstance(props);
		sesionMensaje.setDebug(true);// Para obtener un log de salida más extenso
		try {

			Message mensaje = new MimeMessage(sesionMensaje);
			// Asignamos el "de o from" al header del correo.
			mensaje.setFrom(new InternetAddress(emailDe));
			// Asignamos el "para o to" al header del correo.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
			// Asignamos el asunto
			mensaje.setSubject(asunto);
			// Asignamos el contenido HTML, tan grande como nosotros queramos
			mensaje.setContent(new String(msg.getBytes("UTF-8"), "UTF-8"), "text/html; charset=UTF-8");
			// Enviamos el correo
			Transport t = sesionMensaje.getTransport("smtp");
			// Aqui usuario y password de email
			System.out.println("Antes de conectarse");
			t.connect(emailDe, password);
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			t.close();
			System.out.println("Mensaje enviado");
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
			throw new MessagingException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
			throw new UnsupportedEncodingException(e.getMessage());
		}
	}
}
