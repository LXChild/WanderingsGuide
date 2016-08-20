//package com.cnsoft.lxchild.backup;
//
//import android.annotation.TargetApi;
//import android.app.Service;
//import android.content.Context;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.speech.tts.TextToSpeech;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.cnsoft.lxchild.main.R;
//import com.cnsoft.lxchild.notification.MyNotification;
//
//import java.util.Locale;
//
///**
// * Created by LXChild on 2015/4/8.
// */
//public class AboutFragment extends Fragment implements TextToSpeech.OnInitListener {
//
//    private String TAG = AboutFragment.class.getSimpleName();
//    private Context cxt;
//    private TextToSpeech tts;
//
//    private Vibrator vibrator;
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
//    @Override
//    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
//        Button btn_speak = (Button) rootView.findViewById(R.id.btn_speak);
//        final EditText et_speak_content = (EditText) rootView.findViewById(R.id.et_speak_content);
//        this.cxt = container.getContext();
//        vibrator = (Vibrator) cxt.getSystemService(Service.VIBRATOR_SERVICE);
//
//        tts = new TextToSpeech(container.getContext(), this);
//        btn_speak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tts.isSpeaking()) {
//                    tts.stop();
//                    return;
//                }
//                String text = et_speak_content.getText().toString();
//                if (text.length() > 0) {
//                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//                    MyNotification.showNotification(container.getContext(), "么么哒", "恭喜你成功发起了通知");
//                    // runVibrator();
//                } else {
//                    Toast.makeText(container.getContext(), "请输入要说的文本！", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return rootView;
//    }
//
//    private void runVibrator() {
//        vibrator.vibrate(new long[]{0, 500, 1500}, 0);
//    }
//
//    @Override
//    public void onInit(int status) {
//        if (status == TextToSpeech.SUCCESS) {
//            //制定当前朗读的语言为英文
//            int result = tts.setLanguage(Locale.US);
//            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                Toast.makeText(cxt, "Language is not available.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        vibrator.cancel();
//        super.onDetach();
//    }
//}
