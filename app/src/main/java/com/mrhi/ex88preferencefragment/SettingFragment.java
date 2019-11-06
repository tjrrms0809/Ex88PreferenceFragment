package com.mrhi.ex88preferencefragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Set;

public class SettingFragment extends PreferenceFragment {

    SharedPreferences pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //별도의 화면 레이아웃파일(layout폴더)을
        //사용하지 않고 설정 xml문서을 통해 화면이 자동생성
        //res폴더 안에 xml폴더 안에 .xml문서을 만들고
        //<PreferenceScreen>클래스를 통해 화면 설계 시작..
        addPreferencesFromResource(R.xml.setting);

        //SharedPreference객체를 참조하여 설정상태에 대한 제어 가능..
        pref= PreferenceManager.getDefaultSharedPreferences(getActivity());

        // key 값이 "message"인 설정의 저장값 읽어오기
        boolean isMessage= pref.getBoolean("message", false);//두번째 파라미터 : default value.
        Toast.makeText(getActivity(), "소리알림 : "+ isMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        //설정값 변경리스너..등록
        pref.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();

        pref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    //설정값 변경리스너 객체 멤버변수
    SharedPreferences.OnSharedPreferenceChangeListener listener= new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            if(key.equals("message")){
                boolean b= pref.getBoolean("message", false);
                Toast.makeText(getActivity(), "소리알림 : "+ b, Toast.LENGTH_SHORT).show();

            }else if(key.equals("vibrate")){

            }else if(key.equals("nickName")){
                EditTextPreference ep= (EditTextPreference) findPreference(key);
                ep.setSummary(pref.getString(key, ""));
            }else if(key.equals("favor")){
                Set<String> datas= pref.getStringSet(key, null);

            }

        }
    };
}
