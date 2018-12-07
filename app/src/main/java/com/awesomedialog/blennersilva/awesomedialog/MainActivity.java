package com.awesomedialog.blennersilva.awesomedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeRecyclerViewDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<Boolean> booleanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(true);
        booleanList.add(true);
        booleanList.add(false);

        Button btnError = findViewById(R.id.btnError);
        btnError.setOnClickListener(view -> showErrorDialog());

        Button btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(view -> showInfoDialog());

        Button btnProgress = findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(view -> showProgressDialog());

        Button btnWarning = findViewById(R.id.btnWarning);
        btnWarning.setOnClickListener(view -> showWarningDialog());

        Button btnNotice = findViewById(R.id.btnNotice);
        btnNotice.setOnClickListener(view -> showNoticeDialog());

        Button btnSuccess = findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(view -> showSuccessDialog());
    }

    private void showErrorDialog() {
        new AwesomeErrorDialog(this).setButtonText(getString(R.string.dialog_ok_button)).show().getWindow().setLayout(400, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showInfoDialog() {
        final int[] selected = new int[1];
        new AwesomeRecyclerViewDialog(this, animalNames, (position) -> {
            selected[0] = position;
            Log.d("check", String.valueOf(position));
        }).setPositiveButtonClick(() -> {
            Toast.makeText(this, String.valueOf(selected[0]), Toast.LENGTH_LONG).show();
        }).setNegativeButtonClick(() -> {
        }).setTitle("checking").setPositiveButtonText(getString(R.string.dialog_ok_button))
                .setNegativeButtonText(getString(R.string.dialog_cancle_button)).show();


    }

    private void showProgressDialog() {
        new AwesomeProgressDialog(this).show().getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showWarningDialog() {
        new AwesomeWarningDialog(this).setButtonText(getString(R.string.dialog_ok_button)).show().getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showNoticeDialog() {
        new AwesomeNoticeDialog(this).setButtonText(getString(R.string.dialog_ok_button)).show().getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showSuccessDialog() {
        new AwesomeSuccessDialog(this).setPositiveButtonText(getString(R.string.dialog_ok_button)).show().getWindow().setLayout(900, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
