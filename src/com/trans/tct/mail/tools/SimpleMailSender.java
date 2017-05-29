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
 * 简单邮件（不带附件的邮件）发送器
 */
public class SimpleMailSender {
	
	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(),
				authenticator);
		// sendMailSession.setDebug(true);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);

			Address[] tos = new Address[mailInfo.getToAddress().length];
		
			for (int i = 0; i < mailInfo.getToAddress().length; i++) {
				Address to = new InternetAddress(mailInfo.getToAddress()[i]);
				tos[i] = to;
			}
			
			mailMessage.setRecipients(Message.RecipientType.TO, tos);

			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());

			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();

			for (File mi : mailInfo.getAttachFileNames()) {
				MimeBodyPart attch1 = new MimeBodyPart();
				mainPart.addBodyPart(attch1);
				attch1.setFileName(mi.getName());
				DataSource ds1 = new FileDataSource(mi.getPath().toString());
				attch1.setDataHandler(new DataHandler(ds1));
			}

			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=GBK");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// 发送目录下的多个文件
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
