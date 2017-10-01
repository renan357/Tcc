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


        String summary = "<html>\n" +
                "<head>\n" +
                "\t<title>My first chart using FusionCharts Suite XT</title>\n" +
                "\t<script type=\"text/javascript\" src=\"/assets/fusioncharts.js\"></script>\n" +
                "\t<script type=\"text/javascript\" src=\"/assets/fusioncharts.charts.js\"></script>\n" +
                "\t<script type=\"text/javascript\">\n" +
                "\tFusionCharts.ready(function(){\n" +
                "    var revenueChart = new FusionCharts({\n" +
                "        \"type\": \"msline\",\n" +
                "        \"renderAt\": \"chartContainer\",\n" +
                "        \"width\": \"340\",\n" +
                "        \"height\": \"460\",\n" +
                "        \"dataFormat\": \"json\",\n" +
                "        \"dataSource\": {\n" +
                "    \"chart\": {\n" +
                "        \"caption\": \"Sales - 2013 v 2012\",\n" +
                "        \"xaxisname\": \"Month\",\n" +
                "        \"yaxisname\": \"Revenue\",\n" +
                "        \"showvalues\": \"0\",\n" +
                "        \"numberprefix\": \"$\",\n" +
                "        \"legendborderalpha\": \"50\",\n" +
                "        \"showborder\": \"0\",\n" +
                "        \"bgcolor\": \"FFFFFF,FFFFFF\",\n" +
                "        \"plotgradientcolor\": \" \",\n" +
                "        \"showalternatehgridcolor\": \"0\",\n" +
                "        \"showplotborder\": \"0\",\n" +
                "        \"labeldisplay\": \"WRAP\",\n" +
                "        \"divlinecolor\": \"CCCCCC\",\n" +
                "        \"showcanvasborder\": \"0\",\n" +
                "        \"canvasborderalpha\": \"0\",\n" +
                "        \"legendshadow\": \"0\",\n" +
                "        \"linethickness\": \"3\"\n" +
                "    },\n" +
                "    \"categories\": [\n" +
                "        {\n" +
                "            \"category\": [\n" +
                "                {\n" +
                "                    \"label\": \"Jan\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Feb\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Mar\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Apr\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"May\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Jun\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Jul\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Aug\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Sep\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Oct\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Nov\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Dec\",\n" +
                "                    \"stepSkipped\": false,\n" +
                "                    \"appliedSmartLabel\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"dataset\": [\n" +
                "        {\n" +
                "            \"seriesname\": \"2013\",\n" +
                "            \"color\": \"008ee4\",\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"value\": \"27400\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"29800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"25800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"26800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"29600\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"32600\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"31800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"36700\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"29700\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"31900\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"34800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"24800\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"seriesname\": \"2012\",\n" +
                "            \"color\": \"f8bd19\",\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"value\": \"10000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"11500\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"12500\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"15000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"11000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"9800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"11800\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"19700\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"21700\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"21900\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"22900\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"20800\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "\t}\n" +
                "\t});\n" +
                "    revenueChart.render();\n" +
                "\t})\n" +
                "\t</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"chartContainer\"></div>\n" +
                "</body>\n" +
                "</html>";

        //webView.loadDataWithBaseURL(null, summary, "text/html", "utf8", null);
        return graphView;
    }
}
