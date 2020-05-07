package EmailTest;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 


public class Email2{
	/** The message recipient. */
	protected String message_recip = "sal.horquita@gce.org";
	/* What's it all about, Email title */
	protected String message_subject = "Can I have a moment of your time";
	/** The message CC recipient. */
	protected String message_cc = "alexander.green@gce.org";
	/** The message Sender. */
	protected String message_sender = "aaron.larch@gce.org"; // can be any email id 
	/** The message body */
	protected String message_body ="This is actual message. I, Aaron, am sending you an email message throught"
			+" a Java programing email service of my own creation. Because some how,"
			+" this makes more sence to me than trying to learn how to use Outlook.";
	protected String message_body2 ="The solution was To use the Local Email Server and the TLS Port. And By Using The Port It also encrips";
	/** The JavaMail session object */
	protected Session session;
	/** The JavaMail message object */
	protected Message mesg;
	/** Do the work: send the mail to the SMTP server.  */
	public void doSend() {
		// We need to pass info to the mail server as a Properties, since
		// JavaMail (wisely) allows room for LOTS of properties...
		System.out.println("TLSEmail Start");
		Properties properties = new Properties();
		
		// Your LAN must define the local SMTP server as "mailhost"
		// for this simple-minded version to be able to send mail...
		properties.put("mail.smtp.host", "smtp.outlook.com"); // smtp.gmail.com?
		properties.put("mail.smtp.port", "587"); //TLS Port
		properties.put("mail.smtp.auth", "true"); //enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication("a.larch", "******"); //This is my cross server attempt
	        	return new PasswordAuthentication(message_sender, "Spring2020");
	         }
	     };

		// Create the Session object
		session = Session.getDefaultInstance(properties, authenticator);
		session.setDebug(true);	// Verbose!
		try {
			// create a message
			mesg = new MimeMessage(session);
			// From Address - this should come from a Properties...
			mesg.setFrom(new InternetAddress(message_sender));
			// TO Address
			InternetAddress toAddress = new InternetAddress(message_recip);
			mesg.addRecipient(Message.RecipientType.TO, toAddress);
			// CC Address
			InternetAddress ccAddress = new InternetAddress(message_cc);
			mesg.addRecipient(Message.RecipientType.CC, ccAddress);
			// The Subject
			mesg.setSubject(message_subject);
			// Now the message body.
			mesg.setText(message_body2);
			// XXX I18N: use setText(msgText.getText(), charset)
			
			// Finally, send the message!
			Transport.send(mesg);
			} catch (MessagingException ex) {
				while ((ex = (MessagingException)ex.getNextException()) != null) {ex.printStackTrace();}
				}
		}
		/** Simple test case driver 
		 * @throws MessagingException */
	public static void main(String[] av) {
		//Email2 sm = new Email2();
		//sm.doSend();
		
		try {
			List<String> inputPath=new ArrayList<String>();
			inputPath.add("C:/Users/gce/Desktop/Timesheet Tracker.xlsx");
			//inputPath.add("C:/Users/gce/Desktop/Project summory version 2..docx");
			
			ArrayList<String> ccEmail=new ArrayList<String>();
			//ccEmail.add("sal.horquita@gce.org");
			//ccEmail.add("a.larch@yahoo.com");
			
			TextformatDemo.send(
					"smtp.outlook.com",
					"sal.horquita@gce.org",
					"aaron.larch@gce.org",
					ccEmail,
					false,
					"My War Report",
					"I'm sending you this with my cusom dynamic Email server. because this is way more fun than Navigating Cytrix. "+
					" will also be makeing a new update to my Git hub server next week looking forward to that.",
					inputPath);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
