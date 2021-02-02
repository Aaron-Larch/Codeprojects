package com.pdf.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Component
public class DownloadUtil {
	@Autowired
	private TemplateEngine templateEngine;
	
    @Autowired
    private ServletContext servletContext;

    public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName); //parse file name to isolate the file type
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType); //generate media type from string
            return mediaType;
        } catch (Exception e) { //error handling
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
	
	private String createPdf(String templateName, Map<String,String> map) {
		Assert.notNull(templateName, "The templateName can not be null"); //Check for template
		Context ctx = new Context();
		if (map != null) // check for data
		{
			Iterator<Entry<String, String>> itMap = map.entrySet().iterator(); //convert map into iterator
			while (itMap.hasNext()) {
				Map.Entry<String, String> pair = itMap.next(); //create temporary map object and assign it the current iterator value
				ctx.setVariable(pair.getKey().toString(), pair.getValue()); //store tem map into context object
			}
		}
			
		String processedHtml = templateEngine.process(templateName, ctx); //load context object into html file and convert to string
		return processedHtml;
	 }
	 
	public File Download (String templateName, Map<String,String> map, String fileName) {
		String content=createPdf(templateName, map); //convert .html file to a string
		FileOutputStream os = null;
    	File outputFile = new File(fileName); //important to NOT include file destination to prevent duplacite files
		try {
			os = new FileOutputStream(outputFile); //Assign an output stream to write to file
			ITextRenderer renderer = new ITextRenderer(); //declare Itext reader
			renderer.setDocumentFromString(content); //build .pdf from string
			renderer.layout();
			renderer.createPDF(os, false); //write .pdf to file object
			renderer.finishPDF(); //close ITextRender
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try { os.close(); } //close output stream
				catch (IOException e) { e.printStackTrace(); }
			}
		}
		return outputFile;
	}
	
	public ResponseEntity<InputStreamResource> FullDownloadService(String templateName, Map<String,String> map, String fileName){
		//generate .pdf document from .html page, give it a name and store it in a file object
        File file = Download(templateName, map, fileName);
        
        //connect file to an input stream to be read
        InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
			
			//Dynamically Generate appropriate data type for download file. Must match the stored file object
	        MediaType mediaType = getMediaTypeForFileName(this.servletContext, file.getName());
			
			//Generate Dynamic Download pop up window in browser
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()) // Content-Disposition
	                .contentType(mediaType) // Content-Type(File type)
	                .contentLength(file.length()) // Content-Length(File size)
	                .body(resource); //run a input stream to populate page
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
       
	}
}
