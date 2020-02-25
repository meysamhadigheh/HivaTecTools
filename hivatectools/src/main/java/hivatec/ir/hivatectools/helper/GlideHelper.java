package hivatec.ir.hivatectools.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.FileOutputStream;

/**
 * Created by ashkan on 8/9/18.
 */

public class GlideHelper {


	public static void downloadImage(Context context, String url, String fileName, DownloadCallback callback){

		Glide.with(context)
				.asBitmap()
				.load(url)
				.into(new SimpleTarget<Bitmap>() {
					@Override
					public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {

						new AsyncTask(){
							@Override
							protected Object doInBackground(Object[] objects) {

								try {

									FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
									resource.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
									outputStream.flush();
									outputStream.close();

									return Uri.parse(context.getFileStreamPath(fileName).getPath());
								}catch (Exception e){
									e.printStackTrace();
									return null;
								}
							}

							@Override
							protected void onPostExecute(Object o) {
								super.onPostExecute(o);

								callback.downloaded(resource, (Uri) o);
							}
						}.execute();

					}
				});
	}

	public interface DownloadCallback {

		void downloaded(Bitmap bitmap, Uri fileUri);
	}
}
