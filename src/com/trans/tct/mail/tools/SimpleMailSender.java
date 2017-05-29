package com.trans.tct.mail.tools;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.trans.tct.monkey.C;

/**
 * ���ʼ��������������ʼ���������
 */
public class SimpleMailSender {
	
	/**
	 * ��HTML��ʽ�����ʼ�
	 * 
	 * @param mailInfo
	 *            �����͵��ʼ���Ϣ
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			// �����Ҫ�����֤���򴴽�һ��������֤��
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(),
				authenticator);
		// sendMailSession.setDebug(true);
		try {
			// ����session����һ���ʼ���Ϣ
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// �����ʼ���Ϣ�ķ�����
			mailMessage.setFrom(from);

			Address[] tos = new Address[mailInfo.getToAddress().length];
		
			for (int i = 0; i < mailInfo.getToAddress().length; i++) {
				Address to = new InternetAddress(mailInfo.getToAddress()[i]);
				tos[i] = to;
			}
			
			mailMessage.setRecipients(Message.RecipientType.TO, tos);

			// �����ʼ���Ϣ������
			mailMessage.setSubject(mailInfo.getSubject());
			// �����ʼ���Ϣ���͵�ʱ��
			mailMessage.setSentDate(new Date());

			// MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
			Multipart mainPart = new MimeMultipart();

			for (File mi : mailInfo.getAttachFileNames()) {
				MimeBodyPart attch1 = new MimeBodyPart();
				mainPart.addBodyPart(attch1);
				attch1.setFileName(mi.getName());
				DataSource ds1 = new FileDataSource(mi.getPath().toString());
				attch1.setDataHandler(new DataHandler(ds1));
			}

			// ����һ������HTML���ݵ�MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// ����HTML����
			html.setContent(mailInfo.getContent(), "text/html; charset=GBK");
			mainPart.addBodyPart(html);
			// ��MiniMultipart��������Ϊ�ʼ�����
			mailMessage.setContent(mainPart);
			// �����ʼ�
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// ����Ŀ¼�µĶ���ļ�
	public static void sendMail(String logPath,String[] mailtos) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(C.MailHost);
		 mailInfo.setMailServerPort(C.serverPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(C.MailUsername);
		mailInfo.setPassword(C.MailPassword);
		mailInfo.setFromAddress(C.MailFrom);
		mailInfo.setToAddress(mailtos);
		mailInfo.setSubject(C.emailsubject);
		mailInfo.setContent(C.emailcontent);
		File[] attachFileNames = { new File(logPath) };
		mailInfo.setAttachFileNames(attachFileNames);
		SimpleMailSender.sendHtmlMail(mailInfo);
	}


	public static void main(String[] args) {
		
	}
}
