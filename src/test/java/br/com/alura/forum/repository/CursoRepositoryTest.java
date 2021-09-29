package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.hugolopes.springapi.model.Course;
import com.hugolopes.springapi.repository.CourseRepository;

@RunWith(SpringRunner.class)
@DataJpaTest /**
 "DataJpaTest" serve para facilitar os testes, com o controle transacional automático e para permitir a utilização do EntityManager de testes do próprio Spring.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String courseName = "HTML 5";
		
		Course html5 = new Course();
		html5.setName(courseName);
		html5.setCategory("Front-end");
		em.persist(html5);
		
		Course curso = repository.findByName(courseName);
		Assert.assertNotNull(curso);
		Assert.assertEquals(courseName, curso.getName());
	}
	
	@Test
	public void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado() {
		String nomeCurso = "JPA";
		Course curso = repository.findByName(nomeCurso);
		Assert.assertNull(curso);
	}

}
