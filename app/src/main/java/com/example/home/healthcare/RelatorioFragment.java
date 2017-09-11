package com.example.home.healthcare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RelatorioFragment extends Fragment {

    MainActivity main = new MainActivity();
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
    static FileOutputStream output;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelatorioView = inflater.inflate(R.layout.activity_relatorio_fragment, container, false);
        try {
            criadoc();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
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

        mPageIndex = 0;
        if (null != savedInstanceState) {
            mPageIndex = savedInstanceState.getInt(STATE_CURRENT_PAGE_INDEX, 0);
        }
        return RelatorioView;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            openRenderer(main.getContext());
            showPage(mPageIndex);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(main.getContext(), "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mCurrentPage) {
            outState.putInt(STATE_CURRENT_PAGE_INDEX, mCurrentPage.getIndex());
        }
    }

    public void criadoc() throws IOException , DocumentException{
       /* Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            document.add(new Paragraph("Gerando PDF - Java"));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();*/

        Document doc = new Document(PageSize.A4);
        doc.setMargins(40, 40, 40, 80);
        doc.addCreationDate();

        File file = new File(FILE);
        file.createNewFile();
        if (file.exists()) {

            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(FILE));

            doc.open();

            Font fontBold = new Font(); //Fonte em Negrito
            fontBold.setStyle(Font.BOLD);

            Paragraph p0 = new Paragraph("MIX2B", fontBold);
            p0.setAlignment(Element.ALIGN_CENTER);

            doc.add(p0);
            doc.close();
            pdf.close();
        }
    }

    private void openRenderer(Context context) throws IOException {
        File file = new File(context.getCacheDir(), "relatorio.pdf");
        if (!file.exists()) {
            asset = context.getAssets().open(FILE);
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
        getActivity().setTitle("Healthcare "+ index + 1 +pageCount);
    }

    public int getPageCount() {
        return mPdfRenderer.getPageCount();
    }


}


