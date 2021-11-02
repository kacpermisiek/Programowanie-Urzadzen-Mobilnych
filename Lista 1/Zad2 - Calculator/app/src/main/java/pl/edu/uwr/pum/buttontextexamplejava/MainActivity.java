package pl.edu.uwr.pum.buttontextexamplejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    //private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        //button1 = findViewById(R.id.button1);
        //button2 = findViewById(R.id.button2);
    }

    public void onClickButton(View view) {
        textView.append(((Button) view).getText());
    }
}