package com.bwie.wang.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bwie.wang.mvp.view.R;
import com.bwie.wang.mvp.view.activity.LoginActivity;

/**
 * Created by wangbingjun on 2018/10/13.
 */

public class FragmentMine extends Fragment{
    private View view;
    private Button login_but;//登陆时遇到的跳转按钮
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate( R.layout.mine,container,false );
        }
        ViewGroup parent= (ViewGroup) view.getParent();
        if (parent != null){
            parent.removeView(view);
        }
        login_but = view.findViewById(R.id.login_but);

//        跳到登陆页面
        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }
}
