package gaussianblur.tutorial.com.gaussianblur;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView tvHi, tvDescription;
    private ImageView container;
    private LinearLayout layout;

    private RenderScriptGaussianBlur blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvHi = findViewById(R.id.tvHi);
        tvDescription = findViewById(R.id.tvDescription);

        layout = findViewById(R.id.layout);
        container = findViewById(R.id.container);

        container.setVisibility(View.GONE);

        blur = new RenderScriptGaussianBlur(LoginActivity.this);

        initComponents();
        establishBlur();
    }

    private void initComponents() {
        tvDescription.setTypeface(OverrideFonts.getTypeFace(getApplicationContext(), OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.LIGHT));
    }

    private void establishBlur() {
//        container.setVisibility(View.VISIBLE);
//
//        layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        layout.layout(0, 0, layout.getMeasuredWidth(), layout.getMeasuredHeight());
//
//        layout.buildDrawingCache(true);
//        layout.setDrawingCacheEnabled(true);
//        layout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
//
//        Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
//        container.setImageBitmap(blur.gaussianBlur(25, bitmap));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        container.setVisibility(View.VISIBLE);

        layout.setDrawingCacheEnabled(true);
        layout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        Bitmap bitmap = layout.getDrawingCache();

        container.setImageBitmap(blur.gaussianBlur(25, bitmap));

        layout.setVisibility(View.VISIBLE);
    }
}
