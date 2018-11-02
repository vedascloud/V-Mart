package com.vedas.vmart.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;

import com.vedas.vmart.model.ContactUs;

public class ContactUsDAOImpl implements ContactUsDAO{
	
	//-------------------Send Mail to App Team------------------------
	
	@Override
	public List<ContactUs> sendMail(String contactUs) {
		
		ArrayList<ContactUs> list = new ArrayList<>();
					
		ContactUs cl = new ContactUs();
		
			String name = null;
			String emailaddress=null; 
			String message1 =null;
			
			System.out.println("contact json..." +contactUs);
			JSONObject obj = new JSONObject(contactUs);
			name = obj.getString("name");
			emailaddress = obj.getString("emailAddress");
			message1 = obj.getString("Message");
			
					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");

					Session session = Session.getInstance(props,
							new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() 
								{
									return new PasswordAuthentication("vedassales.team@gmail.com", "abcd1234@#");								
								}
						  	});

					try {
						
						Message message7 = new MimeMessage(session);
						message7.setFrom(new InternetAddress("vedassales.team@gmail.com"));
						message7.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vedassales.team@gmail.com"));
						message7.setSubject("Customer Suggestion !!!");
						
						String msg7="<body style='background-color:'#B1998B'>"
							 + "<div class=wrap>"+
				               "<div class=logo>"+
			                    "<a href=#>"+
			               "<img src=http://107.175.83.105/static/emailtemp.png width = 100%>"+"</a>"
			               +"</div>"
			               
			          	+"</div>"
			          	+"</div>"+
					 			"<table background=  http://localhost:8080/images/chant_counter.png  width=100%>"+"</tr>"+"<tr>"+"<td align= left valign = middle   font-family:Arial, Helvetica, sans-serif;>"
							    +"<br><br>"
					 			
							    +"<br>"+"Name : "+name+","
					 			+"<br>"+"Email : "+emailaddress+","
							    +"<br>"+"Message : "+message1+"."
					 			
							    +"<br><br>"
							    +"<font size=4 color=#C70039>"+"<b>"+
								"Best Wishes,"+"<br>"+
								"Join V-Mart App."+"<br><br>"+								
								"Please note: If you have not attempted to Help with V-Mart App, please ignore this email."
								+"</i>"+"</b>"+"</td>"+"</tr>"+"<tr bgcolor= white>"
								+"<td bgcolor='#564319' width=100%>"+"<tr>"+"<td>"+"<i>"+"<b>"+"</b>"+"</i>"+"</h2>"+"</td>"+"</tr>"+"</table>"+"</body>"
								;
						message7.setContent(msg7,"text/html");
						Transport.send(message7);													
												
					} 
					catch (MessagingException e) 
					{  
							 e.printStackTrace();
						    throw new RuntimeException(e);  
					}							
				
					cl.setMessage("Mail Sended");
					cl.setResponse("3");
					list.add(cl);		 
		 
	return list;	
	}	

}
