package com.example.home.healthcare;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RelatorioFragment extends Fragment {

    private static final String STATE_CURRENT_PAGE_INDEX = "current_page_index";
    private static final String FILE = "/data/user/0/com.example.home.healthcare/cache/relatorio.pdf";
    static ParcelFileDescriptor mFileDescriptor;
    static PdfRenderer mPdfRenderer;
    static PdfRenderer.Page mCurrentPage;
    static ImageView mImageView;
    static Button mButtonPrevious;
    static Button mButtonNext;
    static int mPageIndex;
    static View RelatorioView;
    static InputStream asset;
    static BancoActions banco;
    ProgressDialog pDialog;
    MainActivity main = new MainActivity();
    static FileOutputStream output;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        banco = new BancoActions(main.getContext());
        RelatorioView = inflater.inflate(R.layout.activity_relatorio_fragment, container, false);
        mImageView = (ImageView) RelatorioView.findViewById(R.id.imagerelatorio);
        mButtonPrevious = (Button) RelatorioView.findViewById(R.id.previous);
        mButtonNext = (Button) RelatorioView.findViewById(R.id.next);
        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage(mCurrentPage.getIndex() - 1);
            }
        });
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage(mCurrentPage.getIndex() + 1);
            }
        });
        new task().execute();
        mPageIndex = 0;
        if (null != savedInstanceState) {
            mPageIndex = savedInstanceState.getInt(STATE_CURRENT_PAGE_INDEX, 0);
        }
        return RelatorioView;
    }



    class task extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(main.getContext());
            pDialog.setMessage("Gerando relatório..."+"\n"+"Por favor, aguarde!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try {
                criadoc();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String data = dateFormat.format(new Date(System.currentTimeMillis())).toString();
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
            Date hora1 = Calendar.getInstance().getTime();
            String hora= horaFormat.format(hora1).toString();
            composeEmail("renan.moreto357@gmail.com","Relatório de medidas HealthCare",data + " "+ hora);
            try {
                openRenderer(main.getContext());
                showPage(mPageIndex);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(main.getContext(), "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void composeEmail(String de ,String titulo, String texto) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, de);
        intent.putExtra(Intent.EXTRA_SUBJECT, titulo);
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        writeToExternal(main.getContext(),"relatorio.pdf");
        File file = new File(main.getContext().getExternalFilesDir(null) + File.separator + "relatorio.pdf");
        if (!file.exists() || !file.canRead()) {
            Toast.makeText(main.getContext(), "Attachment Error", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (intent.resolveActivity(main.getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void writeToExternal(Context context, String filename){
        try {
            File file = new File(context.getExternalFilesDir(null), filename); //Get file location from external source
            InputStream is = new FileInputStream(context.getFilesDir() + File.separator + filename); //get file location from internal
            OutputStream os = new FileOutputStream(file); //Open your OutputStream and pass in the file you want to write to
            byte[] toWrite = new byte[is.available()]; //Init a byte array for handing data transfer
            Log.i("Available ", is.available() + "");
            int result = is.read(toWrite); //Read the data from the byte array
            Log.i("Result", result + "");
            os.write(toWrite); //Write it to the output stream
            is.close(); //Close it
            os.close(); //Close it
            Log.i("Copying to", "" + context.getExternalFilesDir(null) + File.separator + filename);
            Log.i("Copying from", context.getFilesDir() + File.separator + filename + "");
        } catch (Exception e) {
            Toast.makeText(context, "File write failed: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show(); //if there's an error, make a piece of toast and serve it up
        }
    }

    @Override
    public void onStart() {
        super.onStart();
       /* try {
            openRenderer(main.getContext());
            showPage(mPageIndex);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(main.getContext(), "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mCurrentPage) {
            outState.putInt(STATE_CURRENT_PAGE_INDEX, mCurrentPage.getIndex());
        }
    }

    public void criadoc() throws IOException , DocumentException{

        Document doc = new Document(PageSize.A4);
        doc.setMargins(40, 40, 40, 80);
        doc.addCreationDate();

        File file = new File(main.getContext().getFilesDir(), "relatorio.pdf" );
        file.createNewFile();
        if (file.exists()) {

            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(file));

            doc.open();

            Font fontBold = new Font(); //Fonte em Negrito
            fontBold.setStyle(Font.BOLD);

            Paragraph p0 = new Paragraph("HealthCare", fontBold);
            p0.setAlignment(Element.ALIGN_CENTER);
            doc.add(p0);

            doc.add(Chunk.NEWLINE);

            LineSeparator line1 = new LineSeparator();
            doc.add(line1);

            doc.add(Chunk.NEWLINE);

            Paragraph p1 = new Paragraph("Relatório de medidas cadastradas no banco de dados: ");
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);

            doc.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);

            PdfPCell c1 = new PdfPCell(new Phrase("Data/hora"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Pressão Sistólica"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Pressão Diastólica"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Batimentos Cardíacos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);

            banco.open();
            List<String> listsys = new ArrayList<String>();
            listsys = banco.getsys();
            //String[] sys = list.toArray(new String[list.size()]);
            banco.close();
            banco.open();
            List<String> listdia = new ArrayList<String>();
            listdia = banco.getdia();
            //String[] dia= list.toArray(new String[list.size()]);
            banco.close();
            banco.open();
            List<String> listpulse = new ArrayList<String>();
            listpulse = banco.getpulse();
            //String[] pulse = list.toArray(new String[list.size()]);
            banco.close();
            banco.open();
            List<String> listdate = new ArrayList<String>();
            listdate = banco.getdata();
            //String[] date =list.toArray(new String[list.size()]);
            banco.close();
            banco.open();
            List<String> listtime = new ArrayList<String>();
            listtime = banco.gettime();
            //String[] time = list.toArray(new String[list.size()]);
            banco.close();

            for(int i = 0; i < listsys.size(); i++){
                table.addCell(listdate.get(i)+ "\n" +listtime.get(i));
                table.addCell(listsys.get(i));
                table.addCell(listdia.get(i));
                table.addCell(listpulse.get(i));
            }

            doc.add(table);

            doc.close();
            pdf.close();
        }
    }

    private void openRenderer(Context context) throws IOException {
        File file = new File(context.getFilesDir(),"relatorio.pdf");
        if (!file.exists()) {
            asset = context.getAssets().open(file.toString());
            output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        }
        mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        if (mFileDescriptor != null) {
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        }
    }

    private void showPage(int index) {
        if (mPdfRenderer.getPageCount() <= index) {
            return;
        }
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        mImageView.setImageBitmap(bitmap);
        updateUi();
    }

    private void updateUi() {
        int index = mCurrentPage.getIndex();
        int pageCount = mPdfRenderer.getPageCount();
        mButtonPrevious.setEnabled(0 != index);
        mButtonNext.setEnabled(index + 1 < pageCount);
        //getActivity().setTitle("Healthcare "+ index + 1 +pageCount);
    }

    public int getPageCount() {
        return mPdfRenderer.getPageCount();
    }


}


