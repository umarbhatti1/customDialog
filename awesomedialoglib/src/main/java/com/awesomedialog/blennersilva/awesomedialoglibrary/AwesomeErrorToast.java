package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

public class AwesomeErrorToast extends AwesomeDialogBuilder<AwesomeErrorToast> {

    private Button btDialogOk;
    private RelativeLayout dialogBody;

    {
        btDialogOk = findView(R.id.btDialogOk);
        dialogBody = findView(R.id.dialog_body);
    }

    public AwesomeErrorToast(Context context) {
        super(context, true);
        setColoredCircle(R.color.dialogErrorBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white);
        setButtonBackgroundColor(R.color.dialogErrorBackgroundColor);
        setButtonTextSize(23);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) dialogBody.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = 300;
        dialogBody.setLayoutParams(params);
    }

    public AwesomeErrorToast setDialogBodyBackgroundColor(int color) {
        if (dialogBody != null) {
            dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomeErrorToast setErrorButtonClick(@Nullable final Closure selecteOk) {
        btDialogOk.setOnClickListener(view -> {
            if (selecteOk != null) {
                selecteOk.exec();
            }
        });
        return this;
    }

    public AwesomeErrorToast setButtonBackgroundColor(int buttonBackground) {
        if (btDialogOk != null) {
            btDialogOk.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeErrorToast setButtonTextColor(int textColor) {
        if (btDialogOk != null) {
            btDialogOk.setTextColor(ContextCompat.getColor(getContext(), textColor));
        }
        return this;
    }

    public Button getBtDialogOk() {
        return btDialogOk;
    }

    public RelativeLayout getDialogBody() {
        return dialogBody;
    }

    public AwesomeErrorToast setButtonText(String text) {
        if (btDialogOk != null) {
            btDialogOk.setText(text);

            btDialogOk.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public AwesomeErrorToast setButtonTextSize(int size) {
        if (btDialogOk != null) {
            btDialogOk.setTextSize(size);
        }
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_error;
    }
}
