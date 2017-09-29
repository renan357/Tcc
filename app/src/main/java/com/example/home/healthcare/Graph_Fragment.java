package com.example.home.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Graph_Fragment extends Fragment{

    static View graphView;
    static WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        graphView = inflater.inflate(R.layout.activity_graph__fragment, container, false);
        webView = (WebView) graphView.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/index.html");


        String summary =
                "<html>" +
                        "<head>        " +
                        " <title>My First chart using FusionCharts XT - Using JavaScript</title>      " +
                        "<script type=\"text/javascript\" src=\"FusionCharts/FusionCharts.js\"></script>" +
                        "</head> " +
                        "<body>" +
                        " <div id=\"chartContainer\">FusionCharts XT will load here!</div>" +
                        "<script type=\"text/javascript\">" +
                        " var myChart = new FusionCharts( \"FusionCharts/Column3D.swf\",\"myChartId\", \"400\",\"300\", \"0\", \"1\" );" +
                        "myChart.setXMLUrl(\"Data.xml\");" +
                        "myChart.render(\"chartContainer\");" +
                        "</script>" +
                        "</body>" +
                        "</html>";
        return graphView;
    }
}
