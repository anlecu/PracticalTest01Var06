package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var03ChooseNumber extends AppCompatActivity {

    private Button playbutton;
    private EditText numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);


        playbutton = findViewById(R.id.play_button);
        numberEditText = findViewById(R.id.number_edit_text);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberText = numberEditText.getText().toString();
                if (!numberText.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), Practicaltest01Var02PlayActivity.class);
                    intent.putExtra("number", numberEditText.getText().toString());
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}
