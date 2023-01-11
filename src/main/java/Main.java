import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] argv) throws DocumentException, IOException {
        GerarEtiqueta gerarEtiqueta = new GerarEtiqueta();
        ByteArrayOutputStream stream = gerarEtiqueta.gerarEtiquetas();

        File pdf = new File("etiqueta.pdf");
        FileUtils.writeByteArrayToFile(pdf, stream.toByteArray());
    }
}
