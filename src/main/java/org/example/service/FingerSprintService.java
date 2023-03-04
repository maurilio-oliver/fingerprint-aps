package org.example.service;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.example.helper.FilerHelper;

import org.example.repository.FingerprintRepository;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class FingerSprintService {

    private FingerprintRepository fingerprintRepository = new FingerprintRepository();

    public FingerprintImage getFingerSprint(String uri) {
        byte[] encoded;
        FingerprintImage image;
        try {
            encoded = Files.readAllBytes(Path.of(uri));
            image = new FingerprintImage(encoded);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Number compare(FingerprintImage probe, FingerprintImage cand){

        FingerprintTemplate probable = new FingerprintTemplate(probe);
        FingerprintTemplate candidate = new FingerprintTemplate(cand);
        FingerprintMatcher matcher = new FingerprintMatcher(probable);
        Double equality = matcher.match(candidate);



        return equality ;
    }

    public String test(String candidate){
        String id = "";
        try {
           List<Path> paths = Files.walk(Path.of("src/main/resources/fingerprint/"), 1, FileVisitOption.FOLLOW_LINKS).toList();

            for (Path path : paths) {
                if (!(path.toString().equals("src/main/resources/fingerprint"))) {
                    Number equelity = this.compare(
                            this.getFingerSprint(path.toString()),
                            this.getFingerSprint(candidate)
                    );

                    if (equelity.intValue() >= 60) {
                        id = path.getFileName().toString();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(id);
        return id;
    }

    public Boolean checkUser(String candidate, String proble) {
        Number equelity = this.compare(
                this.getFingerSprint(proble),
                this.getFingerSprint(candidate)
        );
        return equelity.intValue() > 60;
    }


    public static void main(String[] args) throws IOException {
        FingerSprintService fss = new FingerSprintService();

    fss.test("src/main/resources/fingerprint/6.tif");



    }


}
