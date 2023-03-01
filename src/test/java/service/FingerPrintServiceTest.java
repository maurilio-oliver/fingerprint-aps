package service;

import org.example.helper.FilerHelper;
import org.example.model.FingerPrint;
import org.example.service.FingerSprintService;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FingerPrintServiceTest {
    private FingerSprintService service = new FingerSprintService();

    @Test
    public void saveTest() throws IOException {
        FingerPrint fingerPrint = new FingerPrint();
        fingerPrint.setTest(new String(Files.readAllBytes(Path.of("src/test/resources/101_1.tif"))).getBytes());
        //fingerPrint.setFingerPrint(FilerHelper.UTF_FOR_BASE64(fingerPrint.getFingerPrint().));
        this.service.save(fingerPrint);
        System.out.println(fingerPrint.getFingerPrint());
        //System.out.println( this.service.getFingerSprint("src/test/resources/101_1.tif"));
    }
}
