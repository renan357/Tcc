package com.example.home.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class RelatorioFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_fragment);
    }

    public void criadoc(){
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF_DevMedia.pdf"));
            document.open();

            // adicionando um parágrafo no documento
            document.add(new Paragraph("Gerando PDF - Java"));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
