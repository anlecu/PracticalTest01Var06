package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

public class PracticalTest01Var06BroadcastReceiver extends BroadcastReceiver {
    private EditText generateEditText;

    public PracticalTest01Var06BroadcastReceiver(EditText generateEditText) {
        Log.d("broadcast", "constructor fara param");
        this.generateEditText = generateEditText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String data = null;
        if (Constants.ACTION_NUMBER.equals(action)) {
            data = String.valueOf(intent.getIntExtra(Constants.DATA, -1));
            generateEditText.setText(data);
            Log.d("broadcast", data == null ? "null" : data);
        }
    }
}
