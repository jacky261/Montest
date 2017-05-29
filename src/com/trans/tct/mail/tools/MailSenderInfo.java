package com.trans.tct.mail.tools;

import java.io.File;
import java.util.Properties;

/**
 * �����ʼ���Ҫʹ�õĻ�����Ϣ
 */
public class MailSenderInfo {
    // �����ʼ��ķ�������IP�Ͷ˿�
    private String mailServerHost;
    private String mailServerPort = "25";
    // �ʼ������ߵĵ�ַ
    private String fromAddress;
    // �ʼ������ߵĵ�ַ
    private String[] toAddress;

    public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	// ��½�ʼ����ͷ��������û���������
    private String userName;
    private String password;
    // �Ƿ���Ҫ�����֤
    private boolean validate = true;
    
    // �ʼ�����
    private String subject;
    // �ʼ����ı�����
    private String content;
    
    // �ʼ��������ļ���
    private File[] attachFileNames;
    
    
    public File[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(File[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	/**
     * ����ʼ��Ự����
     */
    public Properties getProperties(){
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }
    
    public String getMailServerHost() {
        return mailServerHost;
    }
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }
    public String getMailServerPort() {
        return mailServerPort;
    }
    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }
    public boolean isValidate() {
        return validate;
    }
    public void setValidate(boolean validate) {
        this.validate = validate;
    }
    public String getFromAddress() {
        return fromAddress;
    }
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String textContent) {
        this.content = textContent;
    } 

    
}
