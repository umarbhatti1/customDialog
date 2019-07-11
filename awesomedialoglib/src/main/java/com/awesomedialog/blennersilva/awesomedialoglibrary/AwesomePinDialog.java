package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

public class AwesomePinDialog extends AwesomeDialogBuilder<AwesomePinDialog> {

    private Button positiveButton;
    private Button negativeButton;
    private RelativeLayout dialogBody;

    {
        positiveButton = findView(R.id.btDialogYes);
        negativeButton = findView(R.id.btDialogNo);
        dialogBody = findView(R.id.dialog_body);
    }

    public AwesomePinDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogNoticeBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white);
        setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor);
        setNegativeButtonbackgroundColor(R.color.dialogNoticeBackgroundColor);
        setCancelable(true);
        setNegativeButtonTextSize(23);
        setPositiveButtonTextSize(23);

    }

    public AwesomePinDialog setDialogBodyBackgroundColor(int color) {
        if (dialogBody != null) {
            dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomePinDialog setPositiveButtonClick(@Nullable final Closure selectedYes) {
        positiveButton.setOnClickListener(view -> {
            if (selectedYes != null) {
                selectedYes.exec();
            }
            hide();
        });

        return this;
    }

    public AwesomePinDialog setNegativeButtonClick(@Nullable final Closure selectedNo) {
        negativeButton.setOnClickListener(view -> {
            if (selectedNo != null) {
                selectedNo.exec();
            }
            hide();
        });

        return this;
    }


    public AwesomePinDialog setPositiveButtonbackgroundColor(int buttonBackground) {
        if (positiveButton != null) {
            positiveButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public AwesomePinDialog setPositiveButtonTextColor(int textColor) {
        if (positiveButton != null) {
            positiveButton.setTextColor(ContextCompat.getColor(getContext(), textColor));
        }
        return this;
    }

    public AwesomePinDialog setPositiveButtonText(String text) {
        if (positiveButton != null) {
            positiveButton.setText(text);
            positiveButton.setVisibility(View.VISIBLE);
        }

        return this;
    }

    public AwesomePinDialog setPositiveButtonTextSize(int size) {
        if (positiveButton != null) {
            positiveButton.setTextSize(size);
        }

        return this;
    }

    public AwesomePinDialog setNegativeButtonbackgroundColor(int buttonBackground) {
        if (negativeButton != null) {
            negativeButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomePinDialog setNegativeButtonText(String text) {
        if (negativeButton != null) {
            negativeButton.setText(text);
            negativeButton.setVisibility(View.VISIBLE);
        }

        return this;
    }

    public AwesomePinDialog setNegativeButtonTextSize(int size) {
        if (negativeButton != null) {
            negativeButton.setTextSize(size);
        }

        return this;
    }

    public AwesomePinDialog setNegativeButtonTextColor(int textColor) {
        if (negativeButton != null) {
            negativeButton.setTextColor(ContextCompat.getColor(getContext(), textColor));
            negativeButton.setVisibility(View.VISIBLE);
        }

        return this;
    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_password;
    }
}
