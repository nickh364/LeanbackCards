package com.hitherejoe.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hitherejoe.leanbackcards.R;

public class IconCardView extends BaseCardView {

    private RelativeLayout mLayout;
    private ImageView mIcon;
    private TextView mTitle;
    private TextView mValue;

    public IconCardView(Context context, int styleResId) {
        super(new ContextThemeWrapper(context, styleResId), null, 0);
        buildptionCardView(styleResId);

    }

    public IconCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(getStyledContext(context, attrs, defStyleAttr), attrs, defStyleAttr);
        buildptionCardView(getImageCardViewStyle(context, attrs, defStyleAttr));
    }

    private void buildptionCardView(int styleResId) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        setCardType(CARD_TYPE_MAIN_ONLY);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_icon_card, this);
        TypedArray cardAttrs =
                getContext().obtainStyledAttributes(R.styleable.lbImageCardView);

        mLayout = (RelativeLayout) findViewById(R.id.layout_option_card);
        mIcon = (ImageView) findViewById(R.id.image_option);
        mTitle = (TextView) findViewById(R.id.text_option_title);
        mValue = (TextView) findViewById(R.id.text_option_value);

        cardAttrs.recycle();
    }

    public void setMainImageDimensions(int width, int height) {
        ViewGroup.LayoutParams lp = mLayout.getLayoutParams();
        lp.width = width;
        lp.height = height;
        mLayout.setLayoutParams(lp);
    }

    public void setOptionIcon(Drawable drawable) {
        mIcon.setImageDrawable(drawable);
    }

    public void setOptionTitleText(String titleText) {
        mTitle.setText(titleText);
    }

    public void setOptionValueText(String valueText) {
        mValue.setText(valueText);
    }

    private static Context getStyledContext(Context context, AttributeSet attrs, int defStyleAttr) {
        int style = getImageCardViewStyle(context, attrs, defStyleAttr);
        return new ContextThemeWrapper(context, style);
    }

    private static int getImageCardViewStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        // Read style attribute defined in XML layout.
        int style = null == attrs ? 0 : attrs.getStyleAttribute();
        if (0 == style) {
            // Not found? Read global ImageCardView style from Theme attribute.
            TypedArray styledAttrs =
                    context.obtainStyledAttributes(R.styleable.LeanbackTheme);
            style = styledAttrs.getResourceId(R.styleable.LeanbackTheme_imageCardViewStyle, 0);
            styledAttrs.recycle();
        }
        return style;
    }

    public IconCardView(Context context) {
        this(context, null);
    }

    public IconCardView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.imageCardViewStyle);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }

}