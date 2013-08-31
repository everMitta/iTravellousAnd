package com.iTravellous;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.example.gpstracking.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SavePlacesActivity extends Activity {
	
	Button btnShowLocation;
	Button btnSave;	
	
	TextView lat;
	TextView lon;
	
	TextView name;
	TextView desc;
	
	// GPSTracker class
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

    	lat = (TextView) findViewById(R.id.Latitud);
    	lon = (TextView) findViewById(R.id.Longitud);
    	
		name = (TextView)findViewById(R.id.Nombre);
		desc = (TextView)findViewById(R.id.Descripcion);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(SavePlacesActivity.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	// \n is for new line
		        	//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	

		        	lat.setText(""+latitude);	              
		        	lon.setText(""+longitude);
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			}
		});
 
        btnSave = (Button) findViewById(R.id.btnSave);
        
        // show location button click event
        btnSave.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {		
    			// create class object

    			  String str = "[Name=<"+name.getText()+">Desc=<"+desc.getText()+">Lat=<"+lat.getText()+">Lon=<"+lon.getText()+">]";
    		        try{
    		            FileOutputStream fos = openFileOutput("iTravellous.txt", MODE_WORLD_READABLE);
    		            
    		            OutputStreamWriter osw = new OutputStreamWriter(fos);
    		             
    		            // Escribimos el String en el archivo
    		            osw.write(str);
    		            osw.flush();
    		            osw.close();
    		             
    		            // Mostramos que se ha guardado
    		            Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();

    		        }catch (IOException ex){
    		        	Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_SHORT).show();
    		            ex.printStackTrace();
    		        }
    		             			
    		}
    	});
        

    }
    
 }