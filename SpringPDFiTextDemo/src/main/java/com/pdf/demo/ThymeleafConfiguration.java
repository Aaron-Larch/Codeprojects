package com.pdf.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfiguration{
	
	@Bean //Create a special viewer for jsp files
    InternalResourceViewResolver jspViewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates-2/"); //select file location
        viewResolver.setSuffix(".jsp"); //declare file type
        viewResolver.setViewClass(JstlView.class); //declare media type
        return viewResolver;
    }
	
	@Bean
	 public SpringTemplateEngine textTemplateEngine() {
		 SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		 //load templates into a deployabule template engine
		 templateEngine.addTemplateResolver(htmlTemplateResolver());
		 //templateEngine.addTemplateResolver(jspTemplateResolver());
		 return templateEngine;
	 }

	 private ITemplateResolver htmlTemplateResolver() {
		 ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		 templateResolver.setOrder(1); //select order for running multiple media types
		 templateResolver.setPrefix("templates/"); //select file location, note will always run from resource folder unless told otherwise
		 templateResolver.setSuffix(".html"); //declare file type
		 templateResolver.setTemplateMode(TemplateMode.HTML); //declare media type
		 templateResolver.setCharacterEncoding("UTF-8"); //set encoding, optional
		templateResolver.setCacheable(false);
		 templateResolver.setCheckExistence(true);// check for other template resolvers
		 return templateResolver;
	 }
	 
	 private ITemplateResolver jspTemplateResolver(){
		 ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		 templateResolver.setOrder(2); //select order for running multiple media types
		 templateResolver.setPrefix("/WEB-INF/templates-2/"); //select file location, note path must be included if the folders you are looking for share the same name
		 templateResolver.setSuffix(".jsp"); //declare file type
		 templateResolver.setTemplateMode(TemplateMode.JAVASCRIPT);//declare media type
		 templateResolver.setCharacterEncoding("UTF-8"); //set encoding, optional
		 templateResolver.setCacheable(false);
		 templateResolver.setCheckExistence(true); // check for other template resolvers
		 return templateResolver;
	}
	
	 
}
