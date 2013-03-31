package com.example.layouttest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.Layout;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText et1;
	private LinearLayout ll1;
	private LinearLayout ll2;
	private Button add;
	private Button remove;
	
	private int etCount;
	private int idArray[] = {R.id.et1, R.id.et2, R.id.et3, R.id.et4, R.id.et5};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et1 = (EditText)findViewById(R.id.et1);
        et1.setText("" + et1.getId());
        ll1 = (LinearLayout)findViewById(R.id.linearLayout1);
        ll2 = (LinearLayout)findViewById(R.id.linearLayout2);
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(this);
        remove = (Button)findViewById(R.id.remove);
        remove.setOnClickListener(this);
        
        etCount = 1;
        
        autoLoad(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.add:
			addFrame();
			break;
		case R.id.remove:
			removeFrame();
			break;
		}
		
	}

	private void removeFrame() {
		if(etCount == 0){
			Toast t = Toast.makeText(this, "Cannot remove last Text Box", Toast.LENGTH_LONG);
			t.show();
		}else{
			ll2.removeView(ll2.findViewById(idArray[etCount]));
			Log.v("add", "" + idArray[etCount]);
			etCount--;
		}
		Log.v("count", "" + etCount);
	}

	private void addFrame() {
		
		if(etCount == 4){
			Toast t = Toast.makeText(this, "Cannot add more than 5 Text Boxes", Toast.LENGTH_LONG);
			t.show();
		}else{
			EditText et = new EditText(this);
			et.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			etCount++;
			et.setId(idArray[etCount]);
			Log.v("add", "" + idArray[etCount]);
			et.setText("" + et.getId());
			ll2.addView(et);
		}
		Log.v("count", "" + etCount);
	}
	
	private void autoLoad(int count){
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout parent = ll2;
		
		for(int i = 1; i < count; i++){
			View custom = inflater.inflate(R.layout.cells, null);
			EditText et = (EditText) custom.findViewById(R.id.editText);
			Log.v("id", "" + et.getId());
			etCount++;
			et.setId(idArray[etCount]);
			et.setText("" + et.getId());
			Log.v("id 2", "" + et.getId());
			parent.addView(custom);
			Log.v("count", "" + etCount);
		}
	
	}

    
}
