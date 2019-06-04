package com.example.samplemovieapp.utils.view;

import android.databinding.BindingAdapter;
import android.graphics.drawable.PictureDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.samplemovieapp.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by sandeepsaini on 30,May,2019
 */
public class ImageUtils {

    private static final String SVG_IMAGE = ".svg";

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView image, String imageUrl) {

        if (image == null) {
            return;
        }

        if (TextUtils.isEmpty(imageUrl)) {
            image.setImageResource(R.drawable.ic_image_place_holder);
            return;
        }

        /**
         * Replace Image Library of your choice like Picasso, ImageLoading Library, Fresco etc...
         */

        if (imageUrl.contains(SVG_IMAGE)) {
            //This blocks helps in loading svg images
            RequestBuilder<PictureDrawable> requestBuilder = Glide.with(image.getContext())
                    .as(PictureDrawable.class)
                    .placeholder(R.drawable.ic_image_place_holder)
                    .error(R.drawable.ic_image_place_holder)
                    .transition(withCrossFade());
//                    .listener(new SvgSoftwareLayerSetter());

            requestBuilder.load(imageUrl).into(image);
        } else {
            Glide.with(image.getContext()).load(imageUrl).override(1080, 600).apply(
                    new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(R.drawable.ic_image_place_holder)).into(image);
        }
    }


}
