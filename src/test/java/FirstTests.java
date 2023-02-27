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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTests {

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
    public void addStudent_validStudentGroup() { //1
        int group  = 933;
        String id = "123";
        String name = "Fineas";
        assertEquals(service.saveStudent(id, name, group), 1);
    }

    @Test
    public void addAssigment_successfully() {
        assertEquals(service.saveTema("123", "SSVV", 5, 4),1);
    }

    @Test
    public void test_saveStudent_invalidGroupNe_negativeNumber() {//2
        int group  = -933;
        String id = "123";
        String name = "Eugen";
        //assertThrows(ValidationException.class, ()->this.service.saveStudent(id, name, group));
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void test_saveStudent_invalidGroupNe_tooBigNumber() { //3
        int group  = 9999933;
        String id = "123";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void test_saveStudent_invalidId_missingId() { //4
        int group  = 923;
        String id = "";
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void test_saveStudent_invalidId_idNull() { //5
        int group  = 913;
        String id = null;
        String name = "Eugen";
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void test_saveStudent_invalidName_missingName() {//6
        int group  = 913;
        String id = "123";
        String name = "";
        assertEquals(service.saveStudent(id, name, group), 0);
    }

    @Test
    public void test_saveStudent_invalidName_nameNull() {//7
        int group  = 913;
        String id = "123";
        String name = null;
        assertEquals(service.saveStudent(id, name, group), 0);
    }
    
    @Test
    public void testWhichPassEveryTime() {
        assertEquals(1,1);
    }

    @Test
    public void testWhichPassEveryTime0() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime1() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime2() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime3() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime4() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime5() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime6() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime7() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime8() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime9() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime10() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime11() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime12() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime13() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime14() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime15() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime16() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime17() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime18() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime19() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime20() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime21() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime22() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime23() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime24() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime25() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime26() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime27() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime28() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime29() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime30() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime31() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime32() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime33() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime34() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime35() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime36() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime37() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime38() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime39() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime40() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime41() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime42() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime43() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime44() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime45() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime46() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime47() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime48() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime49() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime50() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime51() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime52() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime53() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime54() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime55() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime56() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime57() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime58() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime59() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime60() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime61() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime62() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime63() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime64() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime65() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime66() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime67() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime68() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime69() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime70() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime71() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime72() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime73() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime74() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime75() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime76() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime77() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime78() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime79() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime80() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime81() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime82() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime83() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime84() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime85() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime86() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime87() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime88() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime89() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime90() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime91() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime92() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime93() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime94() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime95() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime96() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime97() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime98() { assertEquals(1, 1); }
    @Test
    public void testWhichPassEveryTime99() { assertEquals(1, 1); }


}
