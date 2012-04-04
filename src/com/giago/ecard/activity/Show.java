package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.activity.utils.EcardActivity;
import com.giago.ecard.service.EcardDao;
import com.giago.ecard.utils.Template;
import com.giago.ecard.utils.analytic.Tracker;

public class Show extends EcardActivity {

    private static final String TEXT_HTML = "text/html";
    private static final String UTF_8 = "utf-8";
    private boolean previewMode = false;

    private static String[] templates;
    private static int currentTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
    }

    @Override
    protected boolean isActionBarEnabled() {
        return false;
    }

    @Override
    protected void trackPageView(Tracker tracker) {
        tracker.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update(getIntent());

    }

    private void update(Intent i) {
        checkMode(i);
        EcardIntent ecardIntent = getEcardIntent();
        ecardIntent.setTemplate(Template.getTemplatesNames(this)[currentTemplate]);
        Template template = new Template(ecardIntent.getIntent());
        String formatted = template.format(getApplicationContext());
        initializeWebView(formatted);
    }

    private void checkMode(Intent i) {
        if (new EcardIntent(i).isPreview()) {
            previewMode = true;
            return;
        }
        previewMode = false;
    }

    private void initializeWebView(String template) {
        WebView wv = (WebView) findViewById(R.id.ecardwebview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadDataWithBaseURL(Template.TEMPLATE_PATH, template, TEXT_HTML, UTF_8, Template.TEMPLATE_PATH);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        showPreviewBar();
    }

    private void showPreviewBar() {
        findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        final String id = getEcardIntent().getIntent().getStringExtra(EcardIntent.ID);
        final String personal = getEcardIntent().getIntent().getStringExtra(EcardIntent.IS_PERSONAL);
        if (!previewMode) {
            findViewById(R.id.delete).setVisibility(View.VISIBLE);
            findViewById(R.id.delete).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View paramView) {
                    getApplicationContext().getContentResolver().delete(EcardDao.ECARD_URI, "_id = ? ",
                            new String[] { "" + id });
                    startActivity(EcardIntent.getEcardActivityIntent(Show.this));
                }
            });

            if (personal.equals("1")) {
                findViewById(R.id.edit).setVisibility(View.VISIBLE);
            }
        }

        if (previewMode) {

            setupTemplatesIfNecessary();
            findViewById(R.id.save).setVisibility(View.VISIBLE);
            findViewById(R.id.save).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View paramView) {
                    EcardIntent ei = getEcardIntent();
                    ei.setPersonal();
                    ei.setTemplate(templates[currentTemplate]);
                    startService(ei.convertToInsertIntent());
                    setResult(RESULT_OK, null);
                    finish();
                }
            });

            findViewById(R.id.next).setVisibility(View.VISIBLE);
            findViewById(R.id.next).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View paramView) {
                    currentTemplate++;
                    if (currentTemplate >= templates.length) {
                        currentTemplate = 0;
                    }
                    changeTemplate();
                }
            });

            findViewById(R.id.previous).setVisibility(View.VISIBLE);
            findViewById(R.id.previous).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View paramView) {
                    currentTemplate--;
                    if (currentTemplate < 0) {
                        currentTemplate = templates.length - 1;
                    }
                    changeTemplate();
                }
            });
        }
    }

    private void changeTemplate() {
        EcardIntent ei = getEcardIntent();
        ei.setTemplate(templates[currentTemplate]);
        update(ei.getIntent());
        Template template = new Template(ei.getIntent());
        String formatted = template.format(getApplicationContext());
        WebView wv = (WebView) findViewById(R.id.ecardwebview);
        wv.loadDataWithBaseURL("", formatted, TEXT_HTML, UTF_8, "");
    }

    private void setupTemplatesIfNecessary() {
        if (templates != null) {
            return;
        }
        templates = Template.getTemplatesNames(this);
    }

    private EcardIntent getEcardIntent() {
        return new EcardIntent(getIntent());
    }

}
