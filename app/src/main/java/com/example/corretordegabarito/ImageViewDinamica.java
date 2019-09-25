package com.example.corretordegabarito;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class ImageViewDinamica extends AppCompatImageView {

    public ImageViewDinamica(final Context context, final AttributeSet attributeSet)
    {
        super(context,attributeSet);
    }

    @Override
    public void onMeasure(final int larguraMeasureSpec,final int alturaMeasureSpec)
    {
        final Drawable drawable = this.getDrawable();
        if (drawable != null)
        {
            final int largura = MeasureSpec.getSize(larguraMeasureSpec);
            final int altura = (int) Math.ceil(largura * (float) drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth());
            this.setMeasuredDimension(largura,altura);
        }
        else
        {
            super.onMeasure(larguraMeasureSpec,alturaMeasureSpec);
        }
    }
}
