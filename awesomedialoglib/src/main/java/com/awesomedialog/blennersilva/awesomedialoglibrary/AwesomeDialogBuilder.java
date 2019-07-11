package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by blennersilva on 23/08/17.
 */

@SuppressWarnings({"unchecked", "WeakerAccess"})
public abstract class AwesomeDialogBuilder<T extends AwesomeDialogBuilder> {

    private Toast toast;
    private Dialog dialog;
    private View view;
    private ImageView dialogIcon;
    private TextView tvTitle;
    private TextView tvMessage;
    private EditText editText;
    private RelativeLayout coloredCircle;
    private Context context;

    public AwesomeDialogBuilder(Context context) {
        createDialog(new AlertDialog.Builder(context));
        setContext(context);
    }

    public AwesomeDialogBuilder(Context context, boolean toaster) {
        createToast(context);
        setContext(context);
    }

    public static Drawable drawableColorChange(Context context, int icon, int color) {
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

    private void createToast(Context context) {
        view = LayoutInflater.from(context).inflate(getLayout(), null);
        toast = new Toast(context);
        toast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        intitializeVariables();
    }

    private void intitializeVariables() {
        dialogIcon = findView(R.id.dialog_icon);
        tvTitle = findView(R.id.dialog_title);
        tvMessage = findView(R.id.dialog_message);
        coloredCircle = findView(R.id.colored_circle);
        editText = findView(R.id.dialog_Pin);
        setTitleTextSize(23);
        setMessageTextSize(23);
    }

    public void createDialog(AlertDialog.Builder dialogBuilder) {
        view = LayoutInflater.from(dialogBuilder.getContext()).inflate(getLayout(), null);
        dialog = dialogBuilder.setView(view).create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        intitializeVariables();
    }

    protected abstract int getLayout();

    public T setTitle(@StringRes int title) {
        return setTitle(string(title));
    }

    public T setTitle(CharSequence title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        return (T) this;
    }

    public T setPin(CharSequence pin) {
        if (editText != null) {
            editText.setText(pin);
        }

        return (T) this;
    }

    public T setTitleTextSize(int size) {
        if (tvTitle != null) {
            tvTitle.setTextSize(size);
        }

        return (T) this;
    }

    public T setMessage(@StringRes int message) {
        return setMessage(string(message));
    }

    public T setMessage(CharSequence message) {
        if (tvMessage != null) {
            tvMessage.setText(message);
        }

        return (T) this;
    }

    public T setMessageTextSize(int size) {
        if (tvMessage != null) {
            tvMessage.setTextSize(size);
        }
        return (T) this;
    }

    public T setColoredCircle(int color) {
        if (coloredCircle != null) {
            coloredCircle.getBackground().setColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN);
        }

        return (T) this;
    }

    public T setDialogIconAndColor(int icon, int iconColor) {
        if (dialogIcon != null) {
            Animation alertIcon = AnimationUtils.loadAnimation(getContext(), R.anim.rubber_band);
            dialogIcon.startAnimation(alertIcon);
            dialogIcon.setImageDrawable(drawableColorChange(getContext(), icon, iconColor));
        }

        return (T) this;
    }

    public T setDialogIconOnly(int icon) {
        if (dialogIcon != null) {
            Animation alertIcon = AnimationUtils.loadAnimation(getContext(), R.anim.rubber_band);
            dialogIcon.startAnimation(alertIcon);
            dialogIcon.setImageResource(icon);
        }

        return (T) this;
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

    public Toast showToast() {
        try {
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    toast.show();
                }
            } else {
                toast.show();
            }
        } catch (Exception e) {
            Log.e("[AwSDialog:showAlert]", "Erro ao exibir alert");
        }

        return toast;
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

    public T setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return (T) this;
    }

    protected String string(@StringRes int res) {
        return view.getContext().getString(res);
    }

    protected <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) view.findViewById(id);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
