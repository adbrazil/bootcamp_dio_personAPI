package one.digitalinnovation.personapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.enums.PhoneType;

@RestController
public class teste {
	
	@GetMapping("/")
	public String  helloMessage() 
	{
		Person person = new Person();
		person.setFirstName("Teste");


		return "Hello" + person.getFirstName() ;
	}
	 

}
