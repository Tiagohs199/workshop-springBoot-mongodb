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
import com.tiago.workshopmongo.dto.CommentDTO;
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
		Post post3 = new Post(null, sdf.parse("02/06/2021"),"Ce o que", "Ces't parti pour Amsterdam",new AuthorDTO(alex));
		
		CommentDTO c1 = new CommentDTO("Let's go Bro", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("bon voiage", sdf.parse("24/06/2017"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Partiu mano", sdf.parse("27/04/2014"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1,post2,post3));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userReposiroty.save(maria);
	}

}
