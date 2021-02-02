package com.pdf.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

//import com.lowagie.text.DocumentException;

@Component
public class PdfGenaratorUtil {
	@Autowired
	private TemplateEngine templateEngine;
	public void createPdf(String templateName, Map<String,String> map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null"); //Check for template
		Context ctx = new Context();
		if (map != null) { // check for data
		     Iterator<Entry<String, String>> itMap = map.entrySet().iterator(); //convert map into iterator
		       while (itMap.hasNext()) {
			  Map.Entry<String, String> pair = itMap.next(); //create temporary map object and assign it the current iterator value
		          ctx.setVariable(pair.getKey().toString(), pair.getValue()); //store tem map into context object
			}
		}
		
		String processedHtml = templateEngine.process(templateName, ctx); //load context object into html file and convert to string
		  FileOutputStream os = null;
		  String fileName = UUID.randomUUID().toString(); //generate random file name
	        try {
	        	String home = System.getProperty("user.home"); //Dynamically assign user computer path to a string
	        	final File outputFile = new File(home+"/Downloads/" + fileName + ".pdf"); //declare file location and data type
	            os = new FileOutputStream(outputFile); //Assign an output stream to write to file

	            ITextRenderer renderer = new ITextRenderer(); //declare Itext reader
	            renderer.setDocumentFromString(processedHtml); //build .pdf from string
	            renderer.layout();
	            renderer.createPDF(os, false); //write .pdf to file
	            renderer.finishPDF(); //close ITextRender
	            System.out.println("PDF created successfully"); //check statement
	        }
	        finally {
	            if (os != null) { //check for output stream
	                try {
	                    os.close(); //close output stream
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
	}
}
