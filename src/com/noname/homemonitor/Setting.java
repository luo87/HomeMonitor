package com.noname.homemonitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

public class Setting extends PreferenceActivity {
	PreferenceManager manager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);
		SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this);
		int freq = pre.getInt("freq", 3);
		try{
			EditTextPreference freq_Pre = (EditTextPreference)findPreference("freq");
			if (freq_Pre != null){
				freq_Pre.setDefaultValue(freq);
			}
		}catch(Exception e){
			Log.e("on", e.getMessage());
		}
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// TODO Auto-generated method stub
		String pKey = preference.getKey();
		if (pKey == "freq"){
		}else {
			
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

}
