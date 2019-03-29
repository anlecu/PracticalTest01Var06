package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class Practicaltest01Var02PlayActivity extends AppCompatActivity {

    private Button generateButton;
    private Button checkButton;
    private Button backButton;

    private EditText generateEditText;
    private EditText scoreEditText;

    private int number;

    private PracticalTest01Var06BroadcastReceiver practicalTest01Var06BroadcastReceiver;
    private IntentFilter intentFilter;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicaltest01_var02_play);

        generateButton = findViewById(R.id.generate_button);
        checkButton = findViewById(R.id.check_button);
        backButton = findViewById(R.id.back_button);

        generateEditText = findViewById(R.id.generate_number_edit_text);
        scoreEditText = findViewById(R.id.score_edit_text);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rnd = random.nextInt(5);
                generateEditText.setText(String.valueOf(rnd));
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(generateEditText.getText().toString()) == number) {
                    scoreEditText.setText(String.valueOf(Integer.parseInt(scoreEditText.getText().toString()) + 1));
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        Intent intentFromParent = getIntent();

        number = Integer.parseInt(intentFromParent.getStringExtra("number"));

        if (savedInstanceState != null) {
            generateEditText.setText(savedInstanceState.getString("generateEditText"));
            scoreEditText.setText(savedInstanceState.getString("scoreEditText"));
        }

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var06", "ro.pub.cs.systems.eim.practicaltest01var06.PracticalTest01Var06Service"));
        startService(intent);

        practicalTest01Var06BroadcastReceiver = new PracticalTest01Var06BroadcastReceiver(generateEditText);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_NUMBER);
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("generateEditText", generateEditText.getText().toString());
        savedInstanceState.putString("scoreEditText", scoreEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onRestoreInstanceState(savedInstanceState);
        generateEditText.setText(savedInstanceState.getString("generateEditText"));
        scoreEditText.setText(savedInstanceState.getString("scoreEditText"));
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var06", "ro.pub.cs.systems.eim.practicaltest01var06.PracticalTest01Var06Service"));
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        unregisterReceiver(practicalTest01Var06BroadcastReceiver);
        Log.d("brodcast", "onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("brodcast", "onResume()");
        // TODO: exercise 8c - register the broadcast receiver with the corresponding intent filter
        registerReceiver(practicalTest01Var06BroadcastReceiver, intentFilter);
    }
}
