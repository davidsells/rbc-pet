package com.persist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persist.model.Category;
import com.persist.model.CategoryRepository;

@SpringBootApplication
@EnableResourceServer
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ResourceApplication implements CommandLineRunner {
	@Autowired
	CategoryRepository categoryRepository;


	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n\n\n\n\n\n\n We are here. \n\n\n\n\n\n");
		Category cat = new Category("Testing");
		System.out.println("What did we get: "+cat.toString());
		System.out.println("What did we get: "+categoryRepository.toString());
		Arrays.asList("Dog,Cat,Snake,Bird,Lizard,Frog,Hamster".split(",")).forEach(a -> {
			categoryRepository.save(new Category(a));
		});
		
		List<Category> categories = categoryRepository.findAll();
		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
			Category type = (Category) iterator.next();
			System.out.println("HERE: "+type.getName());
		}
		System.out.println("finished");
	}

}

@RestController
@RequestMapping("/v1")
class Resources {
	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping("/message/")
	public Message home() {
		return new Message("Hello World and Potato Chips! ");
	}

	@RequestMapping("/pet/")
	public Pet pet() {
		return new Pet("Spot","Dog");
	}

	@RequestMapping("/category/")
	public List<Category> category() {
		return categoryRepository.findAll();
	}
}

class Pet {
	private String name;
	private String type;
	Pet(){
	}
	public Pet(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
