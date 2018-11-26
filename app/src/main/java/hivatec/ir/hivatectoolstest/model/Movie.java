package hivatec.ir.hivatectoolstest.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 6/28/18.
 */

public class Movie implements ItemBinder {


    public String name;
    String stars;
    String imgUrl;
    Notice notice;

    public Movie(){

    }

    public Movie(String name, String stars, String imgUrl) {
        this.name = name;
        this.stars = stars;
        this.imgUrl = imgUrl;

        notice = new Notice();
        notice.setId(11);
        notice.setName("this is a good movie");
    }




    @Override
    public int getResourceId() {
        return R.layout.item_movie;
    }

    @Override
    public void bindToHolder(ItemHolder binder, Object listener) {

        binder.<TextView>find(R.id.movie_name).setText(name);
        binder.<TextView>find(R.id.movie_stars).setText(stars);

        Glide.with(binder.context).load(imgUrl).into(binder.<ImageView>find(R.id.movie_image));

    }
}
