package example.estudiantes.repositorio;

import example.estudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstudianteRepositorio extends JpaRepository<Estudiante,Integer> {

}
