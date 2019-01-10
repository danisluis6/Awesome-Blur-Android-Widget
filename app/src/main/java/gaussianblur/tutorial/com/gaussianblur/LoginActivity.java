package gaussianblur.tutorial.com.gaussianblur;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        setStatusBarGradiant(this);
    }

    private void initComponents() {
        tvHi.setTypeface(OverrideFonts.getTypeFace(getApplicationContext(), OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.BOLD));
        tvDescription.setTypeface(OverrideFonts.getTypeFace(getApplicationContext(), OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.LIGHT));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.custom_bg);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
//            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
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
