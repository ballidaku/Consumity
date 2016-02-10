package app.abhilasha.consumity.com.consumity.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ameba on 1/20/16.
 */
public class ImageViewSmoothScroll extends ImageView
{


    boolean mBlockLayout;
    private boolean aBoolean;


    public ImageViewSmoothScroll(Context context)
    {
        super(context);
    }

    public ImageViewSmoothScroll(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImageViewSmoothScroll(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void requestLayout()
    {

        if (!mBlockLayout)
        {

            super.requestLayout();

        }

    }


    @Override
    public void setImageResource(int resId)
    {

        mBlockLayout = true;

        super.setImageResource(resId);

        mBlockLayout = false;

    }

    @Override
    public void setImageURI(Uri uri)
    {

        mBlockLayout = true;

        super.setImageURI(uri);

        mBlockLayout = false;

    }

    @Override
    public void setImageDrawable(Drawable drawable)
    {
        mBlockLayout = true;

        super.setImageDrawable(drawable);

        mBlockLayout = false;

    }

    @Override
    public void setImageBitmap(Bitmap bm)
    {
        mBlockLayout = true;

        super.setImageBitmap(bm);

        mBlockLayout = false;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        final Drawable d = this.getDrawable();

        if (d != null)
        {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            final int width = MeasureSpec.getSize(widthMeasureSpec);
            final int height = (int) Math.ceil(width * (float) d.getIntrinsicHeight() / d.getIntrinsicWidth());
            this.setMeasuredDimension(width, height);
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


}
