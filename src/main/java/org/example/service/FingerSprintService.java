package org.example.service;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.example.helper.FilerHelper;
import org.example.model.FingerPrint;
import org.example.repository.FingerprintRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public Boolean checkUser(Integer personId) {

        return null;
    }
    public void save(FingerPrint fingerPrint) {
        String encode = FilerHelper.UTF_FOR_BASE64(fingerPrint.getFingerPrint());
        fingerPrint.setFingerPrint(encode);
        this.fingerprintRepository.save(fingerPrint);
    }

    public static void main(String[] args) {
        FingerSprintService fss = new FingerSprintService();
        System.out.println( fss.compare(
                fss.getFingerSprint("src/main/resources/101_1.tif"),
                fss.getFingerSprint("src/main/resources/103_6.tif")
        ));



    }


}
