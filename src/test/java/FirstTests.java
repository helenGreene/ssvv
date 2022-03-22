import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import repository.StudentXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstTests {

    private Service service;

    @BeforeEach
    public void setUp() {
        String filenameStudent = "src/test/resources/fisiere/Studenti.xml";
        String filenameTema = "src/test/resources/fisiere/Teme.xml";
        String filenameNota = "src/test/resources/fisiere/Note.xml";

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, filenameStudent);
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, filenameTema);
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, filenameNota);


        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Path file = Paths.get("src/test/resources/fisiere/Studenti.xml");
        Files.write(file, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);
        file = Paths.get("src/test/resources/fisiere/Teme.xml");
        Files.write(file, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);
        file = Paths.get("src/test/resources/fisiere/Note.xml");
        Files.write(file, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);

    }

    @Test
    public void addStudent_validStudentGroup() {
        int group  = 933;
        String id = "123";
        String name = "Fineas";
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void addAssigment_successfully() {
        assertEquals(service.saveTema("123", "SSVV", 4, 5),0);
    }

    @Test
    public void test_saveStudent_invalidGroupNe_negativeNumber() {
        int group  = -933;
        String id = "123";
        String name = "Eugen";
        //assertThrows(ValidationException.class, ()->this.service.saveStudent(id, name, group));
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidGroupNe_tooBigNumber() {
        int group  = 9999933;
        String id = "123";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidId_missingId() {
        int group  = 923;
        String id = "";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidId_idNull() {
        int group  = 913;
        String id = null;
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidName_missingName() {
        int group  = 913;
        String id = "123";
        String name = "";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidName_nameNull() {
        int group  = 913;
        String id = "123";
        String name = null;
        assertEquals(service.saveStudent(id, name, group), 1);
    }


}
