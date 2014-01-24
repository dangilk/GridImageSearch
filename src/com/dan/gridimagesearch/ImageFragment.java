package com.dan.gridimagesearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.image.SmartImageView;

public class ImageFragment extends Fragment{
	
	private SmartImageView iv;
	private ImageResult currentImage;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        iv = (SmartImageView)v.findViewById(R.id.ivResult);
        iv.setImageUrl(currentImage.getFullUrl());
        return v;
    }
	
	public void setCurrentImage(ImageResult i){
		currentImage = i;
	}
}
