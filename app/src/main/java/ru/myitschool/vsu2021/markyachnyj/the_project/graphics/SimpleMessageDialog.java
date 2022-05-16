package ru.myitschool.vsu2021.markyachnyj.the_project.graphics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class SimpleMessageDialog extends AlertDialog {
    public SimpleMessageDialog(@NonNull Context context, String message) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_simple_message,(FrameLayout)findViewById(R.id.alert_dialog_simple_mesage_dialog_holder));
        setView(view);
        ((TextView)view.findViewById(R.id.alert_dialog_simple_message_message_tv)).setText(message);
        ((TextView)view.findViewById(R.id.alert_dialog_simple_message_ok_btn)).setOnClickListener(v -> dismiss());
        getWindow().setBackgroundDrawableResource(R.color.transparent);
    }
}
