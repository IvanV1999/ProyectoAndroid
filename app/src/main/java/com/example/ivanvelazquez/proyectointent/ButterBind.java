package com.example.ivanvelazquez.proyectointent;

        import android.os.Bundle;

        import androidx.appcompat.app.AppCompatActivity;

        import butterknife.ButterKnife;

public abstract class ButterBind extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);

    }
    protected abstract int getContentView();

}
