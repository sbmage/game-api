package com.sbmage.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class PortalMailSender {

	private final static Log log = LogFactory.getLog(PortalMailSender.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	private String from = "noreply@gmail.co.kr";

	public void sendForgotPasswordEmail(final Map<String, String> user, final String password) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");

				message.setTo(user.get("email"));
				message.setFrom(from);
				message.setSubject("[Sbmage CS] 임시비밀번호 안내");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("email", user.get("email"));
				model.put("password", password);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/forgotpassword.vm", "UTF-8", model);

				if (log.isDebugEnabled()) {
					log.debug("To : " + user.get("email") + "\n" + "From : " + from + "\n" + "Text : " + text + "\n");
				}

				message.setText(text, true);
			}
		};

		this.javaMailSender.send(preparator);
	}

	public void sendToAdministrator(final String kakaoId, final String email, final String subject, final String body, final String device, final String store,
			final String storeAccount, final String productInfo, final String orderDate) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");

				String receiverEmail = "helpdesk@gmail.co.kr";

				message.setTo(receiverEmail);
				// NOTE: 발신인이 정상 계정이 아니면, 안받아지는 경우가 있음. (ex. no-reply@gmail.co.kr)
				message.setFrom("helpdesk@gmail.co.kr");
				message.setSubject("[sbmage] 고객문의");

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("email", email);
				model.put("subject", subject);
				model.put("body", body);
				model.put("device", device);
				model.put("store", store);
				model.put("storeAccount", storeAccount);
				model.put("productInfo", productInfo);
				model.put("orderDate", orderDate);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/customerInquiry.vm", "UTF-8", model);

				message.setText(text, true);
			}
		};

		this.javaMailSender.send(preparator);
	}

}
