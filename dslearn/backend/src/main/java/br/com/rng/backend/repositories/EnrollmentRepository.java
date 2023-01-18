package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Course;
import br.com.rng.backend.entities.Enrollment;
import br.com.rng.backend.entities.pk.EnrollmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {

}