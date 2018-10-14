package com.bwie.wang.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.wang.mvp.view.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by wangbingjun on 2018/10/13.
 */

public class FragmentHome_Page extends Fragment {
    private View view;
    private Button but_saoyisao,ed_but;
    private Bitmap bitmap;
    private ImageView imageView;
    private EditText ed_text;
    private static final int REQUEST_CODE = 1000;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate( R.layout.home_pager,container,false );
        }
        ViewGroup parent= (ViewGroup) view.getParent();
        if (parent != null){
            parent.removeView(view);
        }
        but_saoyisao = view.findViewById(R.id.saoyisao);
        ed_text = view.findViewById(R.id.ed_text);
        imageView = view.findViewById(R.id.imger);
        ed_but = view.findViewById(R.id.ed_but);
        //扫描二维码的
        but_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
              Toast.makeText(getActivity(),"aaaaaaaaa",Toast.LENGTH_SHORT).show();
            }
        });

        ed_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed_text.getText().toString();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(getActivity(),"必须输入,不能是空",Toast.LENGTH_SHORT).show();
                    return;
                }
                ed_text.setText("");

                bitmap = CodeUtils.createImage(s, 400, 400, null);
                imageView.setImageBitmap(bitmap);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
