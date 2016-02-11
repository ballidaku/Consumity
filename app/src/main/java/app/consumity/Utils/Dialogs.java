package app.consumity.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.consumity.R;

/**
 * Created by ameba on 1/25/16.
 */
public class Dialogs
{
    public Dialog dialog;
    public int    selected_radio_index = -1;
    GlobalConstant constant = new GlobalConstant();

    public void SHOW_ALERT_DAILOG_TWO(final Context context, View.OnClickListener ok, View.OnClickListener cancel, String message, String text_1, String text_2)
    {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_radio_button_saletype);
        constant.set_bold_font(context, dialog.findViewById(R.id.lay_font));
        constant.set_actual_bold_font(context, dialog.findViewById(R.id.dialogtwoText));
        constant.set_actual_bold_font(context, dialog.findViewById(R.id.txtv_alertcancel));
        constant.set_actual_bold_font(context, dialog.findViewById(R.id.txtv_alertok));

        final RadioGroup rad_gp_sale_type = (RadioGroup) dialog.findViewById(R.id.rad_gp_sale_type);

        rad_gp_sale_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean     isChecked          = checkedRadioButton.isChecked();

                if (isChecked)
                {
                    View radioButton = checkedRadioButton;
                    int idx = rad_gp_sale_type.indexOfChild(radioButton);
                    selected_radio_index = idx;
                }
            }
        });
        ((TextView) dialog.findViewById(R.id.dialogtwoText)).setText(message);


        TextView txtv_first = (TextView) dialog.findViewById(R.id.txtv_alertcancel);
        txtv_first.setText(text_1);
        txtv_first.setOnClickListener(cancel);

        TextView txtv_second = (TextView) dialog.findViewById(R.id.txtv_alertok);
        txtv_second.setText(text_2);
        txtv_second.setOnClickListener(ok);

        dialog.show();

    }


    public EditText show_forgot_dialog(final Context con, View.OnClickListener oc)
    {
        dialog = new Dialog(con);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_forgot_password);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);

        Button send = (Button) dialog.findViewById(R.id.send);
        final EditText forgot_password_email;
        // (forgot_password_email = (EditText) dialog.findViewById(R.id.forgot_password_email)).addTextChangedListener(new Text(forgot_password_email));
        forgot_password_email = (EditText) dialog.findViewById(R.id.forgot_password_email);

        send.setOnClickListener(oc);

        return forgot_password_email;
    }


    public void SHOW_ALERT_DAILOG(final Context context, View.OnClickListener ok, View.OnClickListener cancel, String message, String text_1, String text_2)
    {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_alert_two_btn);

        ((TextView) dialog.findViewById(R.id.dialogtwoText)).setText(message);

        TextView txtv_first = (TextView) dialog.findViewById(R.id.txtv_alertcancel);
        txtv_first.setText(text_1);
        txtv_first.setOnClickListener(cancel);

        TextView txtv_second = (TextView) dialog.findViewById(R.id.txtv_alertok);
        txtv_second.setText(text_2);
        txtv_second.setOnClickListener(ok);

        dialog.show();

    }

    public void selectImage(final Context con)
    {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder  builder = new AlertDialog.Builder(con);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                if (options[item].equals("Take Photo"))
                {
                    Context context = con;
                    PackageManager packageManager = context.getPackageManager();

                    if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                    {
                        Log.i("camera", "This device has camera!");
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        captureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(constant.getTempFile()));
                        ((Activity) con).startActivityForResult(captureIntent, 1);
                    }
                    else
                    {
                        Toast.makeText(con, "This device has no camera", Toast.LENGTH_LONG).show();
                    }

                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    ((Activity) con).startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel"))
                {

                    dialog.dismiss();

                }

            }

        });
        builder.show();
    }

}
