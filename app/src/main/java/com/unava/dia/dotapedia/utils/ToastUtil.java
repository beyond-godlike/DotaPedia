package com.unava.dia.dotapedia.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.ui.activity.MainActivity;

/**
 * Created by Dia on 25.10.2018.
 */

public class ToastUtil {
    public static void showToast(MainActivity activity, Context context, String message) {
        if (context != null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast, (ViewGroup)activity.findViewById(R.id.toast_layout));
            TextView tv = layout.findViewById(R.id.textViewToast);
            tv.setText(message);

            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
    }

    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Context context, int strResId) {
        String str = getStringRes(context, strResId);
        if (str != null) {
            Toast.makeText(context.getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    }

    private static String getStringRes(Context context, int strResId) {
        if (context != null) {
            return context.getApplicationContext().getResources().getString(strResId);
        }
        return null;
    }
}
