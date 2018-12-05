package com.awesomedialog.blennersilva.awesomedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeListDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnError = (Button) findViewById(R.id.btnError);
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showErrorDialog();
            }
        });

        Button btnInfo = (Button) findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoDialog();
            }
        });

        Button btnProgress = (Button) findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
            }
        });

        Button btnWarning = (Button) findViewById(R.id.btnWarning);
        btnWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWarningDialog();
            }
        });

        Button btnNotice = (Button) findViewById(R.id.btnNotice);
        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoticeDialog();
            }
        });

        Button btnSuccess = (Button) findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSuccessDialog();
            }
        });
    }

    private void showErrorDialog() {
        new AwesomeErrorDialog(this).setButtonText(getString(R.string.dialog_ok_button)).show().getWindow().setLayout(400, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showInfoDialog() {
        new AwesomeListDialog(this, Arrays.asList("1", "2", "3"), (position) -> {
            Log.d("check", String.valueOf(position));
        }).setPositiveButtonClick(() -> {
        }).setPositiveButtonClick(() -> {
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
