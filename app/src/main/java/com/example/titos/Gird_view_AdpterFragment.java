/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.titos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.titos.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/**
 * Created by TITOS on 3/23/2016.
 */
public class Gird_view_AdpterFragment extends BaseAdapter {
    Context activity;
    ArrayList<String> mPoster =new ArrayList<String>();
    int resource;


    public Gird_view_AdpterFragment(Context context,int resource, ArrayList<String> strings) {
        activity =context;
        mPoster=  strings;
        this.resource=resource;
        //  Toast.makeText(context, String.valueOf(mPoster.size()),Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return mPoster.size() ;
    }

    @Override
    public Object getItem(int position) {
        return mPoster.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //  Toast.makeText(activity, String.valueOf(position),Toast.LENGTH_SHORT).show();
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.image_movie_view, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.movie_view);
            convertView.setTag(holder);



        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(activity).load(mPoster.get(position)).into(holder.imageView);
        return convertView;


    }

    private void startActivity(Intent mIntent) {
        Intent mintent = new Intent(getActivity(),DetailActivityFragment.class);
        mIntent.putExtra(Intent.EXTRA_TEXT,true);
        startActivity(mintent);

    }

    public Context getActivity() {
        return activity;
    }

    class ViewHolder {
        ImageView imageView;
    }

}