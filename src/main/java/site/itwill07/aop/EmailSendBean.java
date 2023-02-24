package site.itwill07.aop;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

//���� ���� ����� �����ϴ� Ŭ����
public class EmailSendBean {
	//���� ������ ���� JavaMailSender ��ü�� �����ϴ� �ʵ�
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	//������ �����ϴ� �޼ҵ� - �ٽɰ��ɸ��
	// => �޴� ��� �̸��� �ּ�, ����, ������ �Ű������� ���޹޾� ����
	// => �޴� ��� �̸��� �ּ� ��ȯ - �޼��� ���
	public String sendEmail(String email, String subject, String content) {
		//JavaMailSender.createMimeMessage() : MimeMessage ��ü�� ��ȯ�ϴ� �޼ҵ�
		//MimeMessage : ���� ���� ���� ������ �����ϱ� ���� Ŭ����
		MimeMessage message=mailSender.createMimeMessage();
		
		try {
			//MimeMessage.setSubject(String subject) : ���� ������ �����ϴ� �޼ҵ�
			message.setSubject(subject);
			
			//MimeMessage.setText(String content) : ���� ����(�ؽ�Ʈ)�� �����ϴ� �޼ҵ�
			message.setText(content);
			
			//MimeMessage.setRecipients(RecipientType type, InternetAddress email)
			// => �̸����� �޴� ����� ���� ������ �����ϴ� �޼ҵ� - �޴� ����� �̸��� �ּ� ����
			// => �޴� ����� �̸��� �ּҴ� InternetAddress ��ü ��� ���ڿ�(String) ����
			//InternetAddress.parse(String addressList) : ���ڿ��� ���޹޾� InternetAddress ��ü��
			//��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
			
			//JavaMailSender.send(MimeMessage message) : ������ �����ϴ� �޼ҵ�
			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return email;
	}
}
















