package com.nateiot.base.common;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author Will WM. Zhang
 *
 */
public class EmailUtil {

	private static JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	
	private static String protocol;
	private static String host;
	private static Integer port;
	private static String username;
	private static String password;

	static {
		setEmailConfig();
	}
	
	/**
	 * @param to 收件人，多个收件人用空格隔开
	 * @param subject 主题
	 * @param htmlText 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String to, String subject, String htmlText)
			throws MessagingException {
		EmailUtil.sendMail(to, null, null, subject, htmlText);
	}

	/**
	 * @param to 收件人，多个收件人用空格隔开
	 * @param cc 抄送，多个抄送时用空格隔开
	 * @param subject 主题
	 * @param htmlText 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String to, String cc, String subject, String htmlText)
			throws MessagingException {
		EmailUtil.sendMail(to, cc, null, subject, htmlText);
	}	

	/**
	 * @param to 收件人，多个收件人用空格隔开
	 * @param cc 抄送，多个抄送时用空格隔开
	 * @param bcc 暗送，多个暗送时用空格隔开
	 * @param subject 主题
	 * @param htmlText 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String to, String cc, String bcc, String subject, String htmlText)
			throws MessagingException {
		try {
			setJavaMailSender();
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg,"UTF-8");
			msgHelper.setFrom(username);
			msgHelper.setTo(StringUtils.split(to));
			if (!StringUtils.isEmpty(cc)) {
				msgHelper.setCc(StringUtils.split(cc));
			}
			if (!StringUtils.isEmpty(bcc)) {
				msgHelper.setBcc(StringUtils.split(bcc));
			}
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);
			javaMailSender.send(msg);
		}
		catch (MessagingException e) {
			throw e;
		}
		finally {
			setEmailConfig();
		}
	}		
	
	// ********** 默认从系统配置中获取邮件服务配置信息 ********** //
	private static void setEmailConfig() {
		setProtocol(ConfigUtil.getConfigValue("EmailConfig", "protocol"));
		setHost(ConfigUtil.getConfigValue("EmailConfig", "host"));
		setPort(Integer.valueOf(ConfigUtil.getConfigValue("EmailConfig", "port")));
		setUsername(ConfigUtil.getConfigValue("EmailConfig", "username"));
		setPassword(ConfigUtil.getConfigValue("EmailConfig", "password"));
	}
	
	private synchronized static void setJavaMailSender() {
		javaMailSender.setProtocol(protocol);
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail." + protocol + ".auth", "true");
		javaMailSender.setJavaMailProperties(javaMailProperties);
	}

	/**
	 * @param protocol 邮件服务器协议，默认为smtp
	 */
	public synchronized static void setProtocol(String protocol) {
		EmailUtil.protocol = protocol;
	}

	/**
	 * @param host 邮件服务器主机
	 */
	public synchronized static void setHost(String host) {
		EmailUtil.host = host;
	}

	/**
	 * @param port 邮件服务器端口，默认为25
	 */
	public synchronized static void setPort(Integer port) {
		EmailUtil.port = port;
	}

	/**
	 * @param username 邮箱用户名，即邮箱地址
	 */
	public synchronized static void setUsername(String username) {
		EmailUtil.username = username;
	}

	/**
	 * @param password 邮箱密码
	 */
	public synchronized static void setPassword(String password) {
		EmailUtil.password = password;
	}
	
}
