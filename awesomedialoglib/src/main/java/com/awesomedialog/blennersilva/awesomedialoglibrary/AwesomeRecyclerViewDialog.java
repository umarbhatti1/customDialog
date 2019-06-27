package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.CustomRecycleViewAdapter;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.ListViewListener;

import java.util.List;

public class AwesomeRecyclerViewDialog implements CustomRecycleViewAdapter.ItemClickListener {

    private Dialog dialog;
    private View dialogView;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private Context context;
    private Button positiveButton;
    private Button negativeButton;
    private RelativeLayout dialogBody;
    private CustomRecycleViewAdapter adapter;
    private ListViewListener listViewListener;

    public AwesomeRecyclerViewDialog(Context context, List<String> list, ListViewListener listViewListener) {
        this.context = context;
        createDialog(context, list, listViewListener);
    }

    public void createDialog(Context context, List<String> list, final ListViewListener listViewListener) {
        this.listViewListener = listViewListener;
        initializeVariables(context);
        createRecycler(context, list);
        dialog.setContentView(dialogView);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setColorAndIcon();
    }

    private void createRecycler(Context context, List<String> list) {
        adapter = new CustomRecycleViewAdapter(context, list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.setClickListener(this);
    }


    private void setColorAndIcon() {
        setPositiveButtonbackgroundColor(R.color.dialogListBackgroundColor);
        setNegativeButtonbackgroundColor(R.color.dialogNoticeBackgroundColor);
        setCancelable(true);
        setNegativeButtonTextSize(23);
        setPositiveButtonTextSize(23);
        setTitleTextSize(23);
    }

    private void initializeVariables(Context context) {
        dialog = new Dialog(context);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        tvTitle = findView(R.id.dialog_title);
        recyclerView = findView(R.id.dialog_message);
        positiveButton = findView(R.id.btDialogYes);
        negativeButton = findView(R.id.btDialogNo);
        dialogBody = findView(R.id.dialog_body);
    }

    protected <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) dialogView.findViewById(id);
    }

    public AwesomeRecyclerViewDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public AwesomeRecyclerViewDialog setNegativeButtonText(String text) {
        if (negativeButton != null) {
            negativeButton.setText(text);
            negativeButton.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public AwesomeRecyclerViewDialog setPositiveButtonText(String text) {
        if (positiveButton != null) {
            positiveButton.setText(text);
            positiveButton.setVisibility(View.VISIBLE);
        }

        return this;
    }

    public AwesomeRecyclerViewDialog setPositiveButtonClick(@Nullable final Closure selectedYes) {
        positiveButton.setOnClickListener(view -> {
            if (selectedYes != null) {
                selectedYes.exec();
            }
        });

        return this;
    }

    public AwesomeRecyclerViewDialog setNegativeButtonClick(@Nullable final Closure selectedNo) {
        negativeButton.setOnClickListener(view -> {
            if (selectedNo != null) {
                selectedNo.exec();
            }
        });
        return this;
    }


    public AwesomeRecyclerViewDialog setPositiveButtonTextSize(int size) {
        if (positiveButton != null) {
            positiveButton.setTextSize(size);
        }
        return this;
    }

    public AwesomeRecyclerViewDialog setNegativeButtonTextSize(int size) {
        if (negativeButton != null) {
            negativeButton.setTextSize(size);
        }
        return this;
    }

    public AwesomeRecyclerViewDialog setNegativeButtonbackgroundColor(int buttonBackground) {
        if (negativeButton != null) {
            negativeButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), buttonBackground), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomeRecyclerViewDialog setPositiveButtonbackgroundColor(int buttonBackground) {
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


    public TextView getTvTitle() {
        return tvTitle;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    public AwesomeRecyclerViewDialog setTitleTextSize(int size) {
        if (tvTitle != null) {
            tvTitle.setTextSize(size);
        }

        return this;
    }

    public AwesomeRecyclerViewDialog setTitle(CharSequence title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        return this;
    }

    public Dialog show() {
        try {
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN;
                    dialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
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

    @Override
    public void onItemClick(View view, int position) {
        listViewListener.onClick(position);
    }
}
