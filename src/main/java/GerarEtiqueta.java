import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeCodabar;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class GerarEtiqueta {

    public ByteArrayOutputStream gerarEtiquetas() throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        document.addTitle("Etiquetas");
        document.setMargins(0, 0, 0, 0);
        document.open();

        // ### INICIO DOCUMENTO ###
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f); // 100% = 21cm
        table.setSpacingBefore(0);
        table.setWidths(new float[]{.5f, .5f}); // 50% = 10.5cm

        PdfPCell cell = new PdfPCell(gerarEtiqueta(writer));
        cell.setFixedHeight(93.5433f); // 93.5433pt = 3.3cm
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(PdfPCell.NO_BORDER); // comentar caso queira ver a divisão

        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);

        document.add(table);
        document.close();

        return baos;
    }

    public PdfPTable gerarEtiqueta(PdfWriter pdfDoc){
        PdfPTable table = new PdfPTable(1);
        table.addCell(cellCenter(new Phrase("TESTE DA SILVA", createFont(18, true))));
        table.addCell(cellCenter(new Phrase("TESTE / DF", createFont(12, false))));
        table.addCell(createNarcode("08431862084", pdfDoc));
        table.addCell(cellCenter(new Phrase("084.318.620-84", createFont(10, false))));
        return table;
    }

    private PdfPCell cellCenter(Phrase phrase){
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static PdfPCell createNarcode(String code, PdfWriter pdfDoc) {
        BarcodeCodabar codabar = new BarcodeCodabar();
        codabar.setCode("A" + code + "C");
        codabar.setFont(null);
        codabar.setBarHeight(22.6772f); // 22.6772pt = 0,8cm
        Image image = codabar.createImageWithBarcode(pdfDoc.getDirectContent(), null, null);

        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(22.6772f); // 22.6772pt = 0,8cm
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }

    private Font createFont(float size, boolean bold){
        Font font = FontFactory.getFont(bold ? FontFactory.HELVETICA_BOLD : FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);
        font.setSize(size);
        return font;
    }
}
