import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncrementalIntegrationTests {
    private Service service;

    @BeforeEach
    public void setUp() throws IOException {
        String filenameStudent = "./Studenti.xml";
        String filenameTema = "./Teme.xml";
        String filenameNota = "./Note.xml";

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
        Path file = Paths.get("./Studenti.xml");
        Files.deleteIfExists(file);
        file = Paths.get("./Teme.xml");
        Files.deleteIfExists(file);
        file = Paths.get("./Note.xml");
        Files.deleteIfExists(file);
    }

    @Test
    public void test_addStudent_successfully() {
        var result = service.saveStudent("1", "Eugen", 933);
        assertEquals(1, result);
    }

    @Test
    public void test_addAssignment_successfully() {
        var studentResult = service.saveStudent("1", "Eugen", 933);
        assertEquals(1, studentResult);

        var result = service.saveTema("2", "Test Tema", 6, 4);
        assertEquals(1, result);
    }

    @Test
    public void test_addGrade_successfully() {
        final var studentId = "1";
        final var assignmentId = "2";

        var studentResult = service.saveStudent(studentId, "Fineas", 933);
        assertEquals(1, studentResult);

        var assignmentResult = service.saveTema(assignmentId, "Tema pentru testare", 6, 4);
        assertEquals(1, assignmentResult);

        var result = service.saveNota(studentId, assignmentId, 5, 5, "Foarte bun, dar nu prea.");
        assertEquals(1, result);
    }

}
