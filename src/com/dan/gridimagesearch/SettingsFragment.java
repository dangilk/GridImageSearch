package com.dan.gridimagesearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsFragment extends Fragment implements OnClickListener{
	
	public Spinner spSize;
	public Spinner spColor;
	public Spinner spType;
	public EditText etSite;
	public Button btSave;
	public Settings settings = new Settings();
	public Listener listener;
	
	public interface Listener{
		public void settingsUpdated(Settings s);
	}
	
	public void setListener(Listener l){
		listener = l;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        spSize = (Spinner)v.findViewById(R.id.spSize);
        spColor = (Spinner)v.findViewById(R.id.spColor);
        spType = (Spinner)v.findViewById(R.id.spType);
        etSite = (EditText)v.findViewById(R.id.etSite);
        btSave = (Button)v.findViewById(R.id.btSettings);
        btSave.setOnClickListener(this);
        connectSpinners();
        return v;
    }

	
	private void connectSpinners(){
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.image_colors, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spColor.setAdapter(adapter);
		
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.image_sizes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSize.setAdapter(adapter);
		
		adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.image_types, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spType.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if(id == R.id.btSettings){
			String color = getActivity().getResources().getStringArray(R.array.colors)[spColor.getSelectedItemPosition()];
			String size = getActivity().getResources().getStringArray(R.array.sizes)[spSize.getSelectedItemPosition()];
			String type = getActivity().getResources().getStringArray(R.array.types)[spType.getSelectedItemPosition()];
			settings.setColor(color);
			settings.setSize(size);
			settings.setType(type);
			settings.setSite(etSite.getText().toString());
			listener.settingsUpdated(settings);
		}
	}
}
