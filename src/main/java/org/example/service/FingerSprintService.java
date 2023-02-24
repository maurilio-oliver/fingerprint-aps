package org.example.service;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FingerSprintService {

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

    public Number compare(FingerprintImage probe, FingerprintImage cand){

        FingerprintTemplate probable = new FingerprintTemplate(probe);
        FingerprintTemplate candidate = new FingerprintTemplate(cand);
        FingerprintMatcher matcher = new FingerprintMatcher(probable);
        Double equality = matcher.match(candidate);

        return equality ;
    }

    public static void main(String[] args) {
        FingerSprintService fss = new FingerSprintService();
        System.out.println( fss.compare(
                fss.getFingerSprint("src/main/resources/101_1.tif"),
                fss.getFingerSprint("src/main/resources/101_1.tif")
        ));

    }


}
