package com.alxl5.apdritualka;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;

public class MainActivity extends Activity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }
}
