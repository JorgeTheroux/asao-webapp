package dgtic.core.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.entity.ReportesTecnicos;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("reportes_tecnicos/pdf")
public class ReporteTecnicoPdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ReportesTecnicos> datos= (List<ReportesTecnicos>) model.get("todos");
        PdfPTable tabla=new PdfPTable(2);
        PdfPCell celda=new PdfPCell(new Phrase("Datos reporte"));
        celda.setBackgroundColor(new Color(167,218,255));
        celda.setPadding(8f);
        tabla.addCell(celda);
        tabla.addCell("Plagas");
        for (ReportesTecnicos reportesTecnicos:datos) {
            tabla.addCell(String.valueOf(reportesTecnicos.getCliente()));
            tabla.addCell(String.valueOf( reportesTecnicos.getPlagas()));
        }

        document.add(tabla);

    }
}
