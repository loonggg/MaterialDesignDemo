package com.loonggg.materialdesigndemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loonggg.materialdesigndemo.adapter.RecyclerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {
    private RecyclerView rv;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar的设置，稍后讲这个控件
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        //创建默认的线性LayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv.setHasFixedSize(true);
        //创建并设置Adapter
        adapter = new RecyclerAdapter(getDatas());
        rv.setAdapter(adapter);
        //悬浮按钮控件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Snackbar snackbar = Snackbar.make(view, "关注非著名程序员公众号了吗？", Snackbar
                        .LENGTH_LONG);
                snackbar.show();
                snackbar.setAction("关注", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
            }
        });


        //设置DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设置NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.header_layout);

        ImageView headIv = (ImageView) navHeaderView.findViewById(R.id.head_iv);
        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击我的头像", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                final Snackbar snackbar = Snackbar.make(rv, "你知道非著名程序员这个公众号吗？", Snackbar
                        .LENGTH_LONG);
                // 设置动作按钮颜色
                snackbar.setActionTextColor(getResources().getColor(R.color.add_bg_color));
                // 获取 snackbar 视图
                View snackbarView = snackbar.getView();

                //设置修改snackbar文本颜色
                int snackbarTextId = android.support.design.R.id.snackbar_text;
                TextView tv = (TextView) snackbarView.findViewById(snackbarTextId);
                tv.setTextColor(getResources().getColor(R.color.add_bg_color));
                //设置snackbar背景色
                snackbarView.setBackgroundColor(Color.GRAY);
                snackbar.show();
                snackbar.setAction("知道", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
            }
        });
    }

    private String[] getDatas() {
        String[] strs = new String[20];
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                strs[i] = "微信订阅号：非著名程序员";
            } else {
                strs[i] = "公众号：smart_android";
            }
        }
        return strs;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, ToolbarActivity.class));
        } else if (id == R.id.nav_favorite) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_followers) {
            Toast.makeText(this, "群组", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_feedback) {
            Toast.makeText(this, "意见反馈", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
