package crossline.cl.fragment.controls;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment implements View.OnClickListener
{
    private WebView webView;
    private EditText edtUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_web_view, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {
        webView = (WebView)v.findViewById(R.id.webView);
        edtUrl = (EditText)v.findViewById(R.id.edtUrl);

        initWebView();

        v.findViewById(R.id.btnGo).setOnClickListener(this);
        v.findViewById(R.id.btnBack).setOnClickListener(this);
        v.findViewById(R.id.btnForward).setOnClickListener(this);
    }

    private void initWebView()
    {
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://www.google.cl");
        edtUrl.setText("http://www.google.cl");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnGo:
                webView.loadUrl(edtUrl.getText().toString());
                hideKeyboard();
                break;
            case R.id.btnBack:
                if(webView.canGoBack())
                {
                    webView.goBack();
                }
                break;
            case R.id.btnForward:
                if(webView.canGoForward())
                {
                    webView.goForward();
                }
                break;
        }
    }

    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtUrl.getWindowToken(), 0);
    }
}
