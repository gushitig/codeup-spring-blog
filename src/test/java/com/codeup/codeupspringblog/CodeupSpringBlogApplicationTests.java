package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupSpringBlogApplication.class)
@AutoConfigureMockMvc
public class CodeupSpringBlogApplicationTests {

	private User testUser;
	private HttpSession httpSession;

	@Autowired
	private MockMvc mvc;

	@Autowired
	UserRepository userDao;

	@Autowired
	PostRepository postDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Before
	public void setup() throws Exception {

		testUser = userDao.findByUsername("testUser");

		// Creates the test user if not exists
		if(testUser == null){
			User newUser = new User();
			newUser.setUsername("testUser");
			newUser.setPassword(passwordEncoder.encode("pass"));
			newUser.setEmail("testUser@codeup.com");
			testUser = userDao.save(newUser);
		}

		// Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
		httpSession = this.mvc.perform(MockMvcRequestBuilders.post("/login").with(csrf())
						.param("username", "testUser")
						.param("password", "pass"))
				.andExpect(status().is(HttpStatus.FOUND.value()))
				.andExpect(redirectedUrl("/posts"))
				.andReturn()
				.getRequest()
				.getSession();
	}


	@Test
	public void contextLoads() {
		// Sanity Test, just to make sure the MVC bean is working
		assertNotNull(mvc);
	}

	@Test
	public void testIfUserSessionIsActive() throws Exception {
		// It makes sure the returned session is not null
		assertNotNull(httpSession);
	}


	@Test
	public void testCreatePost() throws Exception {
		// Makes a Post request to /ads/create and expect a redirection to the Ad
		this.mvc.perform(
						MockMvcRequestBuilders.post("/posts/create").with(csrf())
								.session((MockHttpSession) httpSession)
								// Add all the required parameters to your request like this
								.param("title", "test")
								.param("body", "123 testing"))
				.andExpect(status().is3xxRedirection());
	}


	@Test
	public void testShowPost() throws Exception {

		Post existingPost = postDao.findAll().get(0);

		// Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
		this.mvc.perform(MockMvcRequestBuilders.get("/posts/" + existingPost.getId()))
				.andExpect(status().isOk())
				// Test the dynamic content of the page
				.andExpect(MockMvcResultMatchers.content().string(containsString(existingPost.getBody())));
	}

	@Test
	public void testPostIndex() throws Exception {
		Post existingPost = postDao.findAll().get(0);

		// Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
		this.mvc.perform(MockMvcRequestBuilders.get("/posts"))
				.andExpect(status().isOk())
				// Test the static content of the page
				.andExpect(MockMvcResultMatchers.content().string(containsString("All posts")))
				// Test the dynamic content of the page
				.andExpect(MockMvcResultMatchers.content().string(containsString(existingPost.getTitle())));
	}


	@Test
	public void testEditPost() throws Exception {
		// Gets the first Ad for tests purposes
		Post existingPost = postDao.findAll().get(0);
		String id = String.valueOf(existingPost.getId());


		// Makes a Post request to /ads/{id}/edit and expect a redirection to the Ad show page
		this.mvc.perform(
						MockMvcRequestBuilders.post("/posts/create").with(csrf())
								.session((MockHttpSession) httpSession)
								.param("title", "edited title")
								.param("body", "edited description")
								.param("id", id))
				.andExpect(status().is3xxRedirection());

		// Makes a GET request to /ads/{id} and expect a redirection to the Ad show page
		this.mvc.perform(MockMvcRequestBuilders.get("/posts"))
				.andExpect(status().isOk())
				// Test the dynamic content of the page
				.andExpect(MockMvcResultMatchers.content().string(containsString("edited title")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("edited description")));
	}


	@Test
	public void testDeletePost() throws Exception {
		// Creates a test Ad to be deleted
		this.mvc.perform(
						MockMvcRequestBuilders.post("/posts/create").with(csrf())
								.session((MockHttpSession) httpSession)
								.param("title", "post to be deleted")
								.param("body", "won't last long"))
				.andExpect(status().is3xxRedirection());

		// Get the recent Ad that matches the title
		Post existingPost = postDao.findByTitle("post to be deleted");

		// Makes a Post request to /ads/{id}/delete and expect a redirection to the Ads index
		this.mvc.perform(
						MockMvcRequestBuilders.post("/posts/" + existingPost.getId() + "/delete").with(csrf())
								.session((MockHttpSession) httpSession)
								.param("id", String.valueOf(existingPost.getId())))
				.andExpect(status().is3xxRedirection());
	}




}
