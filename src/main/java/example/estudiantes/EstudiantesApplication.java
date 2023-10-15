package example.estudiantes;

import example.estudiantes.modelo.Estudiante;
import example.estudiantes.sevicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {

		logger.info("Iniciando la aplicacion...");
		//levantar la fabrica de spring

		SpringApplication.run(EstudiantesApplication.class, args);

		logger.info("Aplicacion finalizada");
	}


	@Override
	public void run(String... args) throws Exception {

		logger.info("Ejecutando metodo run de Spring... "+ nl);

		var salir = false;
		var consola=new Scanner(System.in);
		while (!salir){
			mostrarMenu();
			salir =ejecuarOpciones(consola);
			logger.info("");

		}//fin while

	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
				
				*** Systema de Estudiantes ***
				
				1. Listar Estudiantes 
				2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar estudiante
				6. Salir
				
				Elige una opcion:
				
				""");
	}
	private boolean ejecuarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1->{
				//lisra
				logger.info(nl + "Listado de Estudiantes: " +nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));

			}

			case 2 ->{

				//buscar
				logger.info("Introduce el id estudiiante a buscar:");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if (estudiante  != null){
					logger.info("Estudiante encontrado: " +estudiante + nl );

				}else {
					logger.info("Estudiante No esncontrado con Id: " + idEstudiante + nl);

				}

			}

			case 3->{

				//Agregar estudiante
				logger.info("Agregar Estudiante : " + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				//crear el objeto estudiante
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: " + estudiante +nl);


			}

			case 4 ->{
				//modificar
				logger.info("Modificar estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante !=null){

					logger.info("Agregar Estudiante : " + nl);
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email:  ");
					var email = consola.nextLine();
					//crear el objeto estudiante

					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante Modificado: " + estudiante + nl);

				}else {
					logger.info("Estudiante No encontrado con id: " +idEstudiante);
				}
			}

			case 5 -> {//eliminar estudiante
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if (estudiante !=null){

					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante + nl);
				}else {
					logger.info("Estudiante No encontrado con id: " + idEstudiante);
				}
			}

			case 6 -> {

				//salir
				logger.info("Hasta Pronto! " + nl + nl);
				salir= true;
			}

			default -> {

				logger.info("Opcion No reconocida: " +opcion + nl);
			}
		}//fin switch
		return salir;
	}


}
