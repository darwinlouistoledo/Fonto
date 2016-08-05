package me.darwinlouistoledo.fontoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.darwinlouistoledo.fonto.Fonto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvLato = (TextView) findViewById(R.id.tvLato);
        tvLato.setTypeface(Fonto.with(this).useFont(FontNames.FONT_LATO));

        TextView tvMonteserrat = (TextView) findViewById(R.id.tvMonteserrat);
        tvMonteserrat.setTypeface(Fonto.with(this).useFont(FontNames.FONT_MONTSERRAT));

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutContainer);
        Fonto.with(this).applyFontToView(linearLayout, FontNames.FONT_LATO);

    }

}
