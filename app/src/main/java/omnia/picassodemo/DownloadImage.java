package omnia.picassodemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by Lenovo-pc on 22/06/2017.
 */

public class DownloadImage extends Fragment implements Runnable{
    View v;
    EditText url;
    String urlStr;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.downloadimage,container,false);
        url=(EditText)v.findViewById(R.id.url);
        Button download=(Button)v.findViewById(R.id.download);
        imageView=(ImageView)v.findViewById(R.id.imageView);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread=new Thread(DownloadImage.this);
                thread.start();
            }
        });
        return v;
    }
    //https://www.freepicspot.com/uploads/images/all_baby_photos_517.jpg
    //http://images.all-free-download.com/images/graphiclarge/curious_baby_193909.jpg

    @Override
    public void run() {
        urlStr=url.getText().toString();
        if(!urlStr.isEmpty()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Picasso.with(getActivity())
                            .load(urlStr)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error)
                            .resize(200,200)
                            .rotate(130)
                            .into(imageView)
                            ;
                }
            });

        }
    }

}
