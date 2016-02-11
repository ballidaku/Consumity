package app.consumity.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import app.consumity.Async_Thread.Super_AsyncTask;
import app.consumity.Login_RegisterActivity;
import app.consumity.R;
import eu.janmuller.android.simplecropimage.CropImage;

/**
 Created by ameba on 24/12/15. */
public class GlobalConstant
{
    public static Toast    t;
    public static Snackbar s;

    public static String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static String base_url = "http://112.196.34.42:9095/";

    public static String FB_APP_ID = "1726169904280604";
    public static String SENDER_ID = "715226807766";

    // consumity server key : AIzaSyBKbaOR6NEYioIeN8zuZChS3IDRL8U6lMc

//    public static String CONSUMITY = "consumity";

    public Typeface getAppTypeface(Context con)
    {
        return Typeface.createFromAsset(con.getAssets(), "century_gothic.ttf");
    }

    public enum KeyValues_Names
    {
        Login,CustomerId, ApplicationID, DeviceSerialNo, DeviceType, EmailID, FirstName, UserName, Latitude, Longitude, Password,oldpassword, Flag, UserData,PhotoPath
    }

    public void spaceNotAllowed_at_starting(final EditText edittext)
    {
        edittext.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                check(edittext);
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }

    public void check(EditText edittext)
    {
        if ((edittext.getText().toString().length() == 1 && edittext.getText().toString().equals(" ")) || (edittext.getText().toString().length() == 1 && edittext.getText().toString().equals(".")))
        {
            edittext.setText("");
        }

    }

    public static void show_snackbar(View view, String message)
    {
        if (s != null)
        {
            s.dismiss();
        }
        s = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null);

        TextView tv = (TextView) s.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        s.show();
    }

    public static void get_Hash_key(Context con)
    {
        try
        {

            PackageInfo info = con.getPackageManager().getPackageInfo(con.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        }
        catch (PackageManager.NameNotFoundException e)
        {
            Log.e("name not found", e.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            Log.e("no such an algorithm", e.toString());
        }
    }

    public static void execute(Super_AsyncTask asyncTask)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            asyncTask.execute();
        }

    }

    //*************************************************************Photo Upload Edit Functionality**************************************************


    public File getTempFile()
    {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {

            File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return file;
        }
        else
        {

            return null;
        }
    }


    public void performCrop(String filePath, Context con)
    {

        Intent intent = new Intent(con, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, filePath);
        intent.putExtra(CropImage.SCALE, false);
        intent.putExtra(CropImage.CIRCLE_CROP, true);
        intent.putExtra(CropImage.ASPECT_X, 1);
        intent.putExtra(CropImage.ASPECT_Y, 1);
        ((Activity) con).startActivityForResult(intent, 3);

    }


    public void copyStream(InputStream input, OutputStream output) throws IOException
    {
        byte[] buffer = new byte[1024];
        int    bytesRead;
        while ((bytesRead = input.read(buffer)) != -1)
        {
            output.write(buffer, 0, bytesRead);
        }
    }

    public static Bitmap decodeFile(String filePath) throws IOException
    {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);
        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;
        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale     = 1;
        while (true)
        {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap                photoPreview = BitmapFactory.decodeFile(filePath, o2);
        ByteArrayOutputStream baos         = new ByteArrayOutputStream();
        photoPreview.compress(Bitmap.CompressFormat.PNG, 80, baos);

        ExifInterface ei          = new ExifInterface(filePath);
        int           orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Matrix        matrix      = new Matrix();
        switch (orientation)
        {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                photoPreview = Bitmap.createBitmap(photoPreview, 0, 0, photoPreview.getWidth(), photoPreview.getHeight(), matrix, true);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                photoPreview = Bitmap.createBitmap(photoPreview, 0, 0, photoPreview.getWidth(), photoPreview.getHeight(), matrix, true);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                photoPreview = Bitmap.createBitmap(photoPreview, 0, 0, photoPreview.getWidth(), photoPreview.getHeight(), matrix, true);
                break;
            default:
                photoPreview = Bitmap.createBitmap(photoPreview, 0, 0, photoPreview.getWidth(), photoPreview.getHeight(), matrix, true);
                break;
        }
        return photoPreview;

    }


    public String Get_pic(Context con, String filePath, ImageView profile) throws IOException
    {
        String Photo_Path;

        Bitmap pic = decodeFile(filePath);
        try
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            pic.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Photo_Path = encoded;
        }
        catch (Exception ex)
        {
            Log.e("Exception 1", ex.toString());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            pic.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Photo_Path = encoded;

        }
        catch (OutOfMemoryError ex)
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            pic.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Photo_Path = encoded;
        }
       /* File tempFile = getTempFile();
        if (tempFile.exists())
        {
            tempFile.delete();
        }*/

        // profile.setImageBitmap(pic);

        Log.e("Photo_Path", "" + filePath);
        setRoundLocalImage(con, profile, filePath);
        return Photo_Path;
    }

    public void setRoundLocalImage(Context con, ImageView imageView, String url)
    {

        Picasso.with(con).load(new File(url)).placeholder(R.mipmap.loading_image).skipMemoryCache().error(R.drawable.no_image).transform(new CircleTransform()).into(imageView);

    }

    //**************************************************************************************************************************************************

    public String get_Base64_from_url(URL url)
    {
        try
        {
            // URL img_value = new URL("" + profile.getProfilePictureUri(200,200));

            //Log.e("Profile Pic",""+profile.getProfilePictureUri(200, 200));

            Bitmap mIcon1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mIcon1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        catch (MalformedURLException e)
        {
            Log.e("Exception 1", e.toString());
            return "";
        }
        catch (IOException e)
        {
            Log.e("Exception 2", e.toString());
            return "";
        }
    }

    public boolean check_GPS(Context con)
    {
        LocationManager service = (LocationManager) con.getSystemService(con.LOCATION_SERVICE);
        boolean         enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return enabled;

    }

    public String get_Mac_Address(Context con)
    {
        WifiManager wifiManager = (WifiManager) con.getSystemService(Context.WIFI_SERVICE);
        WifiInfo    wInfo       = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    public static void setListViewHeightBasedOnItems(GridView target_Listview, int limit) // LIMIT 0 FOR SHOWING ALLL CONTENTS
    {
        if (limit == 0)
        {
            ListAdapter listAdapter = target_Listview.getAdapter();
            if (listAdapter != null)
            {
                int numberOfItems = listAdapter.getCount();
                int totalItemsHeight = 0;

                for (int itemPos = 0; itemPos < numberOfItems; itemPos++)
                {
                    View item = listAdapter.getView(itemPos, null, target_Listview);
                    item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    totalItemsHeight += item.getMeasuredHeight();
                }

                //                int totalDividersHeight = target_Listview.div() * (numberOfItems - 1);

                ViewGroup.LayoutParams params = target_Listview.getLayoutParams();

                params.height = totalItemsHeight / 2 + 20/* + totalDividersHeight*/;
                target_Listview.setLayoutParams(params);
                target_Listview.requestLayout();
            }
            else
            {
            }
        }
        else
        {
            ListAdapter listAdapter = target_Listview.getAdapter();
            if (listAdapter != null)
            {
                int numberOfItems = listAdapter.getCount();
                int totalItemsHeight = 0;

                for (int itemPos = 0; itemPos < numberOfItems; itemPos++)
                {
                    if (itemPos < limit)
                    {
                        View item = listAdapter.getView(itemPos, null, target_Listview);
                        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                        totalItemsHeight += item.getMeasuredHeight();
                    }
                }
                //                int totalDividersHeight = target_Listview.get() * (numberOfItems - 1);
                ViewGroup.LayoutParams params = target_Listview.getLayoutParams();
                params.height = totalItemsHeight / 2 + 20;
                target_Listview.setLayoutParams(params);
                target_Listview.requestLayout();
            }
        }
    }

    public static void setListViewHeightBasedOnItems_List(ListView target_Listview, int limit) // LIMIT 0 FOR SHOWING ALLL CONTENTS
    {
        if (limit == 0)
        {
            ListAdapter listAdapter = target_Listview.getAdapter();
            if (listAdapter != null)
            {
                int numberOfItems = listAdapter.getCount();
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++)
                {
                    View item = listAdapter.getView(itemPos, null, target_Listview);
                    item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    totalItemsHeight += item.getMeasuredHeight();
                }

                int totalDividersHeight = target_Listview.getDividerHeight() * (numberOfItems - 1);

                ViewGroup.LayoutParams params = target_Listview.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight;
                target_Listview.setLayoutParams(params);
                target_Listview.requestLayout();
            }
            else
            {

            }
        }
        else
        {
            ListAdapter listAdapter = target_Listview.getAdapter();
            if (listAdapter != null)
            {
                int numberOfItems = listAdapter.getCount();
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++)
                {
                    if (itemPos < limit)
                    {
                        View item = listAdapter.getView(itemPos, null, target_Listview);
                        item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                        totalItemsHeight += item.getMeasuredHeight();
                    }
                }
                int totalDividersHeight = target_Listview.getDividerHeight() * (numberOfItems - 1);
                ViewGroup.LayoutParams params = target_Listview.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight;
                target_Listview.setLayoutParams(params);
                target_Listview.requestLayout();
            }
        }
    }

    public class MyLinearLayoutManager extends LinearLayoutManager
    {

        public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout)
        {
            super(context, orientation, reverseLayout);
        }

        private int[] mMeasuredDimension = new int[2];

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec)
        {
            final int widthMode  = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);
            final int widthSize  = View.MeasureSpec.getSize(widthSpec);
            final int heightSize = View.MeasureSpec.getSize(heightSpec);
            int       width      = 0;
            int       height     = 0;
            for (int i = 0; i < getItemCount(); i++)
            {
                measureScrapChild(recycler, i, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);

                if (getOrientation() == HORIZONTAL)
                {
                    width = width + mMeasuredDimension[0];
                    if (i == 0)
                    {
                        height = mMeasuredDimension[1];
                    }
                }
                else
                {
                    height = height + mMeasuredDimension[1];
                    if (i == 0)
                    {
                        width = mMeasuredDimension[0];
                    }
                }
            }
            switch (widthMode)
            {
                case View.MeasureSpec.EXACTLY:
                    width = widthSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            switch (heightMode)
            {
                case View.MeasureSpec.EXACTLY:
                    height = heightSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            setMeasuredDimension(width, height);
        }

        private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension)
        {
            View view = recycler.getViewForPosition(position);
            if (view != null)
            {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight(), p.width);
                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height);
                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        }
    }

    public class MyStaggeredLayoutManager extends StaggeredGridLayoutManager
    {

        int spanCount;

        public MyStaggeredLayoutManager(int spanCount, int orientation)
        {
            super(spanCount, orientation);
            this.spanCount = spanCount;
        }

        private int[] mMeasuredDimension = new int[2];

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec)
        {
            final int widthMode  = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);
            final int widthSize  = View.MeasureSpec.getSize(widthSpec);
            final int heightSize = View.MeasureSpec.getSize(heightSpec);
            int       width      = 0;
            int       height     = 0;
            for (int i = 0; i < getItemCount(); i++)
            {
                measureScrapChild(recycler, i, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), mMeasuredDimension);

                if (getOrientation() == HORIZONTAL)
                {
                    width = width + mMeasuredDimension[0];
                    if (i == 0)
                    {
                        height = mMeasuredDimension[1];
                    }
                }
                else
                {
                    height = height + mMeasuredDimension[1];
                    if (i == 0)
                    {
                        width = mMeasuredDimension[0];
                    }
                }
            }

            switch (widthMode)
            {
                case View.MeasureSpec.EXACTLY:
                    width = widthSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            switch (heightMode)
            {
                case View.MeasureSpec.EXACTLY:
                    height = heightSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            if (getOrientation() == HORIZONTAL)
            {
                setMeasuredDimension(width / spanCount, height);
            }
            else
            {
                setMeasuredDimension(width, height / spanCount);
            }

        }

        private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension)
        {
            View view = recycler.getViewForPosition(position);
            if (view != null)
            {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight(), p.width);
                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height);
                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        }

    }

    public void set_actual_bold_font(Context con, View v)
    {
        Typeface bold = Typeface.createFromAsset(con.getAssets(), "century_gothic_bold.ttf");

        applyFonts(v, bold);
    }

    public void set_bold_font(Context con, View v)
    {
        Typeface bold = Typeface.createFromAsset(con.getAssets(), "century_gothic.ttf");

        applyFonts(v, bold);
    }

    void applyFonts(final View v, Typeface fontToSet)
    {
        try
        {
            if (v instanceof ViewGroup)
            {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++)
                {
                    View child = vg.getChildAt(i);
                    applyFonts(child, fontToSet);
                }
            }
            else if (v instanceof TextView)
            {
                ((TextView) v).setTypeface(fontToSet);
            }
            else if (v instanceof EditText)
            {
                ((EditText) v).setTypeface(fontToSet);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // ignore
        }
    }

    public void hide_keyboard(Context con)
    {
        try
        {
            InputMethodManager inputMethodManager = (InputMethodManager) con.getSystemService(con.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isAcceptingText())
            {
                inputMethodManager.hideSoftInputFromWindow(((Activity) con).getCurrentFocus().getWindowToken(), 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void show_Toast(String text, Context con)
    {
        if (t != null)
        {
            t.cancel();
        }
        t = Toast.makeText(con, text, Toast.LENGTH_SHORT);

        t.show();
    }

    public static boolean isOnline(Context con)
    {
        ConnectivityManager cm      = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         netInfo = cm.getActiveNetworkInfo();

        boolean b = netInfo != null && netInfo.isConnectedOrConnecting();
        if (b == false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /******************************************************************************************************************************************************
                                                            Set and Get Data From Shared Preferences
     ********************************************************************************************************************************************************/


    public  void saveToPreference(Context con, String data, String prefernce_name)
    {
        try
        {
            SharedPreferences prefs = get_SharedPreferences(con);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(prefernce_name, data);
            editor.commit();
        }
        catch (Exception ex)
        {
            Log.e("Exception is", ex.toString());
        }
    }

    public  String getFromPreference(Context con, String prefernce_name)
    {
        SharedPreferences prefs = get_SharedPreferences(con);

        return prefs.getString(prefernce_name, "");


    }

    public String getParticularValueFromPreference(Context con, String prefernce_name, String key_name)
    {
        SharedPreferences prefs = get_SharedPreferences(con);

        String value = "";

        JSONObject obj = null;
        try
        {
            if (!prefs.getString(prefernce_name, "").equals(""))
            {
                obj = new JSONObject(prefs.getString(prefernce_name, ""));

                value = obj.getString(key_name);
            }
        }
        catch (JSONException e)
        {
            Log.e("Shared Pref", "Ex " + e.toString());
        }

        return value;
    }

    public SharedPreferences get_SharedPreferences(Context con)
    {

        return con.getSharedPreferences("CONSUMITY", Context.MODE_PRIVATE);

    }
    //******************************************************To delete user data from sharedpreference***********************************************

    public void delete_all_personal_data(Context con)
    {
        try
        {
            get_SharedPreferences(con).edit().clear().commit();
        }
        catch (Exception ex)
        {
            Log.e("Exception is", ex.toString());
        }
    }


    //******************************************************To clear on off notification functionality***********************************************

    public void clear_notification(Context con, String which)
    {
        try
        {
            SharedPreferences prefs = con.getSharedPreferences("Notification", con.MODE_WORLD_READABLE);
            prefs.edit().remove(which).commit();


        }
        catch (Exception ex)
        {
            Log.e("Exception is", ex.toString());
        }
    }


    //*************************************************************************************************************************************************
    //*************************************************************************************************************************************************


    public void setImage(Context con, ImageView imageView, String url)
    {

        if (url.isEmpty() || url.equals("null"))
        {
            Picasso.with(con).load("null").placeholder(R.mipmap.loading_image) // optional
                      .error(R.drawable.no_image).into(imageView);
        }
        else
        {
            Picasso.with(con).load(url).placeholder(R.mipmap.loading_image) // optional
                      .error(R.drawable.no_image).into(imageView);
        }

    }


    public void setRoundImage(Context con, ImageView imageView, String url)
    {



        if (url.isEmpty() || url.equals("null"))
        {
            Picasso.with(con).load("null").placeholder(R.mipmap.ic_profile_placeholder) // optional
                      .transform(new CircleTransform()).into(imageView);
        }
        else
        {
            Picasso.with(con).load(url).placeholder(R.mipmap.ic_profile_placeholder) // optional
                      .transform(new CircleTransform()).into(imageView);
        }


    }

    public class CircleTransform implements Transformation
    {
        @Override
        public Bitmap transform(Bitmap source)
        {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source)
            {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas       canvas = new Canvas(bitmap);
            Paint        paint  = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            Log.e("rrrrrrrrrrr", "" + r);
            canvas.drawCircle(r, r, r - 2, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key()
        {
            return "circle";
        }
    }


    //**********************************************************Logout Locally******************************************************************

    public void logout_locally(Context con)
    {


        //****************************To check MainActivity is runninh=g or not **************************************************
//        Boolean isMainActivityRunning=get_Notification_Preference(con).getBoolean("isMainActivityRunning",false);

        //****************************To remove ON OFF functionality of notifications**************************
        clear_notification(con, "notification_on_off");

        //****************************To remove all personal data from communication sharedpreference***************
        delete_all_personal_data(con);

        //****************************Remove all data from Notification sharedpreference********************************************
//        delete_all_notification(con);

        //****************************To remove status bar notifications***************************************
//        remove_notification(con);

        //****************************To remove App icon notifications*****************************************
//        ShortcutBadger.applyCount(con, 0);




//        Log.e("isMainActivityRunning",""+isMainActivityRunning);

//        if(isMainActivityRunning)
//        {

            Intent i = new Intent(con, Login_RegisterActivity.class);
            i.addCategory(Intent.CATEGORY_HOME);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("login", "login");
            con.startActivity(i);

//            ((Activity) con).finish();
//        }
    }



}
