package com.tiago.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tiago.workshopmongo.domain.Post;
import com.tiago.workshopmongo.domain.User;
import com.tiago.workshopmongo.dto.AuthorDTO;
import com.tiago.workshopmongo.repository.PostRepository;
import com.tiago.workshopmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{
	
	
	@Autowired
	private UserRepository userReposiroty;
	@Autowired
	private PostRepository postReposiroty;
	
	

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null,"Maria Brown","maria@gmail.com");
		User alex = new User(null,"Alex green","alex@gmail.com");
		User bob = new User(null,"Bob Grey","bob@gmail.com");
		
		userReposiroty.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("07/06/2021"),"Let's go", "Lets go to Amsterdam",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("02/06/2021"),"Ces't parti", "Ces't parti pour Amsterdam",new AuthorDTO(maria));
		
		postReposiroty.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userReposiroty.save(maria);
	}

}
