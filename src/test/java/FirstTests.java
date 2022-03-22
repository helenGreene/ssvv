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

import java.io.File;
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
    public void setUp() throws IOException {
        String filenameStudent = "src/test/resources/fisiere/Studenti.xml";
        String filenameTema = "src/test/resources/fisiere/Teme.xml";
        String filenameNota = "src/test/resources/fisiere/Note.xml";

        Path studentFile = Files.createFile(Paths.get(filenameStudent));
        Files.write(studentFile, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);
        Path temaFile = Files.createFile(Paths.get(filenameTema));
        Files.write(temaFile, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);
        Path notaFile = Files.createFile(Paths.get(filenameNota));
        Files.write(notaFile, Collections.singletonList("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Entitati></Entitati>"), StandardCharsets.UTF_8);

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
        Files.deleteIfExists(file);
        file = Paths.get("src/test/resources/fisiere/Teme.xml");
        Files.deleteIfExists(file);
        file = Paths.get("src/test/resources/fisiere/Note.xml");
        Files.deleteIfExists(file);
    }

    @Test
    public void addStudent_validStudentGroup() { //1
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
    public void test_saveStudent_invalidGroupNe_negativeNumber() {//2
        int group  = -933;
        String id = "123";
        String name = "Eugen";
        //assertThrows(ValidationException.class, ()->this.service.saveStudent(id, name, group));
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidGroupNe_tooBigNumber() { //3
        int group  = 9999933;
        String id = "123";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidId_missingId() { //4
        int group  = 923;
        String id = "";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidId_idNull() { //5
        int group  = 913;
        String id = null;
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidName_missingName() {//6
        int group  = 913;
        String id = "123";
        String name = "";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void test_saveStudent_invalidName_nameNull() {//7
        int group  = 913;
        String id = "123";
        String name = null;
        assertEquals(service.saveStudent(id, name, group), 1);
    }


}
