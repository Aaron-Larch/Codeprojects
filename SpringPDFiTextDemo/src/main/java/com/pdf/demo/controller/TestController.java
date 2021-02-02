package com.pdf.demo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.pdf.demo.model.City;
import com.pdf.demo.service.ICityService;
import com.pdf.demo.util.DownloadUtil;
import com.pdf.demo.util.GeneratePdfReport;
import com.pdf.demo.util.PdfGenaratorUtil;

@Controller
public class TestController {
	@Autowired
    private ICityService cityService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@Autowired
	DownloadUtil downloadutil;
	
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE) //declare file type for page
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<City> cities = (List<City>) cityService.findAll(); //retreave data for page

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities); //generate pdf as a byte array

        HttpHeaders headers = new HttpHeaders(); //declare header variable
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf"); //populate header and declare file name

        //generate pdf web page with apropeate header
        return ResponseEntity
                .ok()
                .headers(headers) //run headers
                .contentType(MediaType.APPLICATION_PDF) //declare media type
                .body(new InputStreamResource(bis)); //run a input stream to populate page
    }
    
    @RequestMapping("/PdfTest") //a way to make two different pages run off of the same method
  	public ModelAndView TestPDF() {
  		ModelAndView model = new ModelAndView();
  		System.out.println("hello");
  		Map<String,String> data = new HashMap<String,String>(); //Generate dynamic values for .html page
  		data.put("OrderID","100000001");
  		data.put("name","James");
  		data.put("product", "Soap");
  		data.put("ammount", "1,000 bars");
  		data.put("discount", "10% off");
  		data.put("price", "$12,450");
  		data.put("Compony", "Aquasisions inc.");
  		model.addObject("name", "Sally"); //declare objects to send to .jsp page
  	    try {
  	    	//generate .pdf document from .html page, and download it onto hardrive
  	    	pdfGenaratorUtil.createPdf("Greetings2",data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	model.setViewName("NewFile"); //declare jsp page
        return model;  //go to jsp page 
  	}
    
    @RequestMapping("/download") //a way to scan and download any page
  	public String Downloadtest() throws DocumentException, IOException {
        String fileName = "Tommy"; //Declare file name
		String home = System.getProperty("user.home"); //Dynamically assign user computer path to a string
		String fileNameFinal = home+"/Downloads/" + fileName + ".pdf"; //declare file location and data type
        URL link = new URL("http://localhost:8082/pdfreport"); //declare website to a url variable

        InputStream in = new BufferedInputStream(link.openStream()); //connect url to an input stream to be read
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];//declare file size in bytes
        int n = 0;
        while (-1!=(n=in.read(buf))) //assign each byte of url to a temp value 
        {
           out.write(buf, 0, n);//write temp value to output stream
        }//close all opened streams
        out.close();
        in.close();
        byte[] response = out.toByteArray(); //set output to an array

        FileOutputStream fos = new FileOutputStream(fileNameFinal);//connect stream to file
        fos.write(response); //write content to file
        fos.close(); //close stream
    	return "redirect:/PdfTest"; //redirect to main web page
    }
    
    @RequestMapping("/download1") //A formal way of downloading files through a browser
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
    	Map<String,String> data = new HashMap<String,String>(); //Generate dynamic values for .html page
  		data.put("OrderID","100000001");
  		data.put("name","James");
  		data.put("product", "Soap");
  		data.put("ammount", "1,000 bars");
  		data.put("discount", "10% off");
  		data.put("price", "$12,450");
  		data.put("Compony", "Aquasisions inc.");
  		
  		//generate .pdf document from .html page, give it a name and store it in a file object
        File file = downloadutil.Download("Greetings2", data, "Purchace Receipt.pdf");
        
        //connect file to an input stream to be read
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        //Dynamically Generate appropriate data type for download file. Must match the stored file object
        MediaType mediaType = downloadutil.getMediaTypeForFileName(this.servletContext, file.getName());
        
        //Generate Dynamic Download pop up window in browser
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()) // Content-Disposition
                .contentType(mediaType) // Content-Type(File type)
                .contentLength(file.length()) // Content-Length(File size)
                .body(resource); //run a input stream to populate page
    }
    
    @RequestMapping("/download2") //A formal way of downloading files through a browser
    public ResponseEntity<InputStreamResource> downloadFile2() {
    	Map<String,String> data = new HashMap<String,String>(); //Generate dynamic values for .html page
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
  	    Calendar cal = Calendar.getInstance(), ship = Calendar.getInstance();
  	  	ship.add(Calendar.DATE, 8);
    	
    	data.put("OrderID","100000001");
  		data.put("name","James");
  		data.put("product", "Soap");
  		data.put("ammount", "1,000 bars");
  		data.put("discount", "10% off");
  		data.put("price", "$12,450");
  		data.put("Compony", "Aquasisions inc.");
  		data.put("today", dateFormat.format(cal.getTime()));
  		data.put("itemp", "$5.34/bar");
  		data.put("Dilivery", dateFormat.format(ship.getTime()));
  		
        return downloadutil.FullDownloadService("Receipt", data, "ReceiptStyle2.pdf");
    }
}
