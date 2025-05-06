package financeProjects.fastSystem.dataAcces.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import financeProjects.fastSystem.entities.concretes.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	boolean existsByPersonPojo_TcKimlikNo(String tcKimlikNo);
	Optional<Person> findByPersonPojo_TcKimlikNo(String tcKimlikNo);
}
