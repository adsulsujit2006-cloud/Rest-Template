package com.notification_services.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.notification_services.services.EmailServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServicesImpl implements EmailServices{
	
	  @Autowired
	    private  JavaMailSender javaMailSender;

	    @Value("${spring.mail.username}")
	    private String fromEmail;
	    
	    @Value("${app.mail.otp-validity-minutes:10}")
	    private int otpValidityMinutes;


	@Override
	public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) {
		

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setFrom(fromEmail, "Book My Show");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(buildOtpHtml(text, otp), true);   // true = HTML

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | java.io.UnsupportedEncodingException e) {
            throw new MailSendException("Failed to send email to " + userEmail, e);
        }

	}
	
	private String buildOtpHtml(String text, String otp) {
        String safeText = HtmlUtils.htmlEscape(text);
        String safeBrand = HtmlUtils.htmlEscape("Book My Show");
        String safeOtp = HtmlUtils.htmlEscape(otp);

        String template =
            "<!DOCTYPE html><html><head><meta charset='UTF-8'>"
          + "<meta name='viewport' content='width=device-width,initial-scale=1'></head>"
          + "<body style='margin:0;padding:0;background:#f2f2f2;font-family:Arial,Helvetica,sans-serif;'>"
          + "<table role='presentation' width='100%' cellpadding='0' cellspacing='0' style='background:#f2f2f2;padding:24px 0;'>"
          + "<tr><td align='center'>"
          + "<table role='presentation' width='480' cellpadding='0' cellspacing='0' "
          + "style='max-width:480px;width:100%;background:#ffffff;border-radius:8px;overflow:hidden;'>"

          // Header
          + "<tr><td style='background:#1f2533;padding:20px;text-align:center;'>"
          + "<span style='color:#ffffff;font-size:22px;font-weight:bold;letter-spacing:0.5px;'>"
          + "{{BRAND}}</span></td></tr>"
          + "<tr><td style='background:#f84464;height:4px;font-size:0;line-height:0;'>&nbsp;</td></tr>"

          // Body
          + "<tr><td style='padding:32px 28px 8px 28px;color:#333333;'>"
          + "<p style='margin:0 0 12px 0;font-size:18px;font-weight:bold;'>Verify your email</p>"
          + "<p style='margin:0 0 24px 0;font-size:14px;line-height:22px;color:#555555;'>{{TEXT}}</p>"

          // OTP box
          + "<div style='text-align:center;margin:0 0 24px 0;'>"
          + "<div style='display:inline-block;background:#fff5f7;border:2px dashed #f84464;"
          + "border-radius:8px;padding:16px 32px;'>"
          + "<span style='font-size:34px;font-weight:bold;letter-spacing:10px;color:#f84464;'>{{OTP}}</span>"
          + "</div></div>"

          + "<p style='margin:0 0 8px 0;font-size:13px;color:#555555;text-align:center;'>"
          + "This OTP is valid for <b>{{MINUTES}} minutes</b>.</p>"
          + "</td></tr>"

          // Security note
          + "<tr><td style='padding:8px 28px 28px 28px;'>"
          + "<div style='background:#fafafa;border-left:4px solid #f84464;padding:12px 14px;"
          + "font-size:12px;line-height:18px;color:#666666;'>"
          + "<b>Never share this OTP</b> with anyone, including our staff. "
          + "If you didn't request this, you can safely ignore this email."
          + "</div></td></tr>"

          // Footer
          + "<tr><td style='background:#f7f7f7;padding:16px;text-align:center;"
          + "font-size:11px;color:#999999;'>"
          + "&copy; {{BRAND}}. This is an automated message, please do not reply."
          + "</td></tr>"

          + "</table></td></tr></table></body></html>";

        return template
                .replace("{{BRAND}}", safeBrand)
                .replace("{{TEXT}}", safeText)
                .replace("{{OTP}}", safeOtp)
                .replace("{{MINUTES}}", String.valueOf(otpValidityMinutes));
    }
	
	 private String buildOtpPlainText(String text, String otp) {
	        return text + "\n\nYour OTP: " + otp
	                + "\nValid for " + otpValidityMinutes + " minutes."
	                + "\nDo not share this OTP with anyone.";
	    }

}
