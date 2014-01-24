package com.dan.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchFragment extends Fragment implements OnClickListener{
	
	public interface Listener{
		public void imageClicked(ImageResult image);
	}
	
	private EditText etQuery;
	private GridView gvResults;
	private ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>(); 
	private ImageResultArrayAdapter imageAdapter;
	private Listener listener;
	public Settings settings = new Settings();
	public int start = 0;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        v.findViewById(R.id.btSearch).setOnClickListener(this);
        etQuery = (EditText)v.findViewById(R.id.etQuery);
        gvResults = (GridView)v.findViewById(R.id.gvResults);
        imageAdapter = new ImageResultArrayAdapter(getActivity(),imageResults);
        gvResults.setAdapter(imageAdapter);
        gvResults.setOnItemClickListener(thumbClickListener);
        gvResults.setOnScrollListener(new EndlessScrollListener(){
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				start = totalItemsCount;
				search();
			}
        	
        });
        return v;
    }
	
	public void setListener(Listener l){
		this.listener = l;
	}
	
	OnItemClickListener thumbClickListener = new OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> adapter, View parent, int position,
				long rowId) {
			listener.imageClicked(imageResults.get(position));
		}
		
	};

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.btSearch){
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(etQuery.getWindowToken(), 0);
			start = 0;
			imageResults.clear();
			search();
		}
		
	}
	
	public void search(){
		String query = etQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"+"start="+start+"&v=1.0&q="+Uri.encode(query)+settings.urlFrag(),jsonHttpResponseHandler);
	}
	
	JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler(){
		@Override
		public void onSuccess(JSONObject response){
			JSONArray imageJsonResults = null;
			try{
				imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
				
				imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
			}catch (JSONException e){
				e.printStackTrace();
			}
		}
	};
}
