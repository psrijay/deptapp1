package com.example.android.dept;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

public class SyllabusFragment extends BaseFragment {


    private View rootView;
    private ImageView download,preview;
    private WebView webView;
    ProgressDialog pDialog;
    String fileUrl;
    public SyllabusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_syllabus, container, false);
        download=(ImageView)rootView.findViewById(R.id.download);
        preview=(ImageView)rootView.findViewById(R.id.preview);
        webView=(WebView)rootView.findViewById(R.id.webview);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(
                            Uri.parse("http://gate.iitr.ernet.in/wp-content/uploads/2016/07/Syllabi_GATE2017.pdf"), "application/pdf");
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    webView.setVisibility(View.VISIBLE);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    if (18 < Build.VERSION.SDK_INT ){
                        //18 = JellyBean MR2, KITKAT=19
                        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    }
                    webView.setWebChromeClient(new WebChromeClient() {

                        public void onProgressChanged(WebView view, int progress) {
                            pDialog.show();
                            getActivity().setProgress(progress * 100); //Make the bar disappear after URL is loaded

                            // Return the app name after finish loading
                            if(progress == 100)
                                pDialog.dismiss();
                        }

                    });
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            if (pDialog.isShowing()) {
                                pDialog.dismiss();
                            }
                        }
                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                            Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();

                        }

                    });

                    String pdfURL = "http://gate.iitr.ernet.in/wp-content/uploads/2016/07/Syllabi_GATE2017.pdf";
                    webView.loadUrl(
                            "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        new DownloadFile().execute("http://gate.iitr.ernet.in/wp-content/uploads/2016/07/Syllabi_GATE2017.pdf", "five-point-someone-chetan-bhagat_ebook.pdf");
                    } else {

                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
                else { //permission is automatically granted on sdk<23 upon installation
                    new DownloadFile().execute("http://gate.iitr.ernet.in/wp-content/uploads/2016/07/Syllabi_GATE2017.pdf", "five-point-someone-chetan-bhagat_ebook.pdf");
                }
            }
        });
        return rootView;
    }
    @Override
    public boolean onBackPressed() {
        if (webView.getVisibility()==View.VISIBLE) {
            webView.setVisibility(View.GONE);
            return true;
        } else {
            return false;
        }
    }
    private class DownloadFile extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!pDialog.isShowing())
                pDialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {

            fileUrl = strings[0];
            FileDownloader.downloadFile(fileUrl,getActivity());
            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getActivity(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            new DownloadFile().execute("http://gate.iitr.ernet.in/wp-content/uploads/2016/07/Syllabi_GATE2017.pdf", "five-point-someone-chetan-bhagat_ebook.pdf");
        }
    }
}
