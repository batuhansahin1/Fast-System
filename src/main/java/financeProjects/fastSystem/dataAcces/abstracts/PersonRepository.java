package financeProjects.fastSystem.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import financeProjects.fastSystem.entities.concretes.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	boolean existsByPersonPojo_TcKimlikNo(String tcKimlikNo);
}
