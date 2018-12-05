package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.ListViewListener;

import java.util.List;

public class AwesomeListDialog {

    private Dialog dialog;
    private View dialogView;
    private ImageView dialogIcon;
    private TextView tvTitle;
    private ListView listView;
    private RelativeLayout coloredCircle;
    private Context context;
    private Button positiveButton;
    private Button negativeButton;
    private RelativeLayout dialogBody;

    public AwesomeListDialog(Context context, List<String> list, ListViewListener listViewListener) {
        this.context = context;
        createDialog(context, list, listViewListener);
    }

    public void createDialog(Context context, List<String> list, final ListViewListener listViewListener) {
        dialog = new Dialog(context);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        dialogIcon = findView(R.id.dialog_icon);
        tvTitle = findView(R.id.dialog_title);
        listView = findView(R.id.dialog_message);
        coloredCircle = findView(R.id.colored_circle);
        positiveButton = findView(R.id.btDialogYes);
        negativeButton = findView(R.id.btDialogNo);
        dialogBody = findView(R.id.dialog_body);
        listView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < listView.getChildCount(); i++) {
                if (position == i) {
                    listView.getChildAt(position).setBackground(new ColorDrawable(Color.GRAY));
                } else {
                    listView.getChildAt(i).setBackground(new ColorDrawable(Color.TRANSPARENT));
                }
            }
            listViewListener.onClick(position);
        });
        dialog.setContentView(dialogView);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setColoredCircle(R.color.dialogInfoBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white);
        setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor);
        setNegativeButtonbackgroundColor(R.color.dialogErrorBackgroundColor);
        setCancelable(true);
        setNegativeButtonTextSize(23);
        setPositiveButtonTextSize(23);
        setTitleTextSize(23);
    }

    protected <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) dialogView.findViewById(id);
    }

    public AwesomeListDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public AwesomeListDialog setNegativeButtonText(String text) {
        if (negativeButton != null) {
            negativeButton.setText(text);
            negativeButton.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public AwesomeListDialog setPositiveButtonText(String text) {
        if (positiveButton != null) {
            positiveButton.setText(text);
            positiveButton.setVisibility(View.VISIBLE);
        }

        return this;
    }

    public AwesomeListDialog setPositiveButtonClick(@Nullable final Closure selectedYes) {
        positiveButton.setOnClickListener(view -> {
            if (selectedYes != null) {
                selectedYes.exec();
            }

        });

        return this;
    }

    public AwesomeListDialog setNegativeButtonClick(@Nullable final Closure selectedNo) {
        negativeButton.setOnClickListener(view -> {
            if (selectedNo != null) {
                selectedNo.exec();
            }
        });

        return this;
    }

    public AwesomeListDialog setDialogIconAndColor(int icon, int iconColor) {
        if (dialogIcon != null) {
            Animation alertIcon = AnimationUtils.loadAnimation(getContext(), R.anim.rubber_band);
            dialogIcon.startAnimation(alertIcon);

            dialogIcon.setImageDrawable(drawableColorChange(getContext(), icon, iconColor));
        }
        return this;
    }

    public AwesomeListDialog setPositiveButtonTextSize(int size) {
        if (positiveButton != null) {
            positiveButton.setTextSize(size);
        }

        return this;
    }

    public AwesomeListDialog setNegativeButtonTextSize(int size) {
        if (negativeButton != null) {
            negativeButton.setTextSize(size);
        }

        return this;
    }

    public Drawable drawableColorChange(Context context, int icon, int color) {
        Drawable drawable;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getDrawable(icon);
        } else {
            drawable = context.getResources().getDrawable(icon);
        }

        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN);
        }

        return drawable;
    }

    public AwesomeListDialog setNegativeButtonbackgroundColor(int buttonBackground) {
        if (negativeButton != null) {
            negativeButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomeListDialog setPositiveButtonbackgroundColor(int buttonBackground) {
        if (positiveButton != null) {
            positiveButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public View getDialogView() {
        return dialogView;
    }

    public ImageView getDialogIcon() {
        return dialogIcon;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ListView getListView() {
        return listView;
    }

    public RelativeLayout getColoredCircle() {
        return coloredCircle;
    }

    public AwesomeListDialog setColoredCircle(int color) {
        if (coloredCircle != null) {
            coloredCircle.getBackground().setColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomeListDialog setTitleTextSize(int size) {
        if (tvTitle != null) {
            tvTitle.setTextSize(size);
        }

        return this;
    }

    public AwesomeListDialog setTitle(CharSequence title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        return this;
    }

    public Dialog show() {
        try {
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    dialog.show();
                }
            } else {
                dialog.show();
            }
        } catch (Exception e) {
            Log.e("[AwSDialog:showAlert]", "Erro ao exibir alert");
        }

        return dialog;
    }

    public Dialog hide() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            Log.d("[AwSDialog : dismiss]", " Erro ao remover diálogo (%s)");
        }
        dialog.dismiss();
        return dialog;
    }

    public Context getContext() {
        return context;
    }

    public Button getPositiveButton() {
        return positiveButton;
    }

    public Button getNegativeButton() {
        return negativeButton;
    }


    public RelativeLayout getDialogBody() {
        return dialogBody;
    }
}
