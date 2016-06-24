package com.loonggg.materialdesigndemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loonggg.materialdesigndemo.adapter.PullMoreRecyclerAdapter;
import com.loonggg.materialdesigndemo.bean.CardInfo;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends BaseActivity {
    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshWidget;
    private PullMoreRecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    List<CardInfo> list = new ArrayList<CardInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        rv = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        swipeRefreshWidget.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R
                .color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        //创建并设置Adapter
        adapter = new PullMoreRecyclerAdapter(getDatas());
        rv.setAdapter(adapter);

        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshWidget.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        getDatas();
                        adapter.notifyDataSetChanged();
                        swipeRefreshWidget.setEnabled(true);
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter
                        .getItemCount()) {
                    adapter.setMoreStatus(PullMoreRecyclerAdapter.LOADING_MORE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getDatas();
                            adapter.setMoreStatus(PullMoreRecyclerAdapter.PULLUP_LOAD_MORE);
                            adapter.notifyDataSetChanged();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private List<CardInfo> getDatas() {
        for (int i = 0; i < 10; i++) {
            CardInfo ci = new CardInfo();
            ci.setContent("美女说：非著名程序员公众号是东半球最好的技术分享公众号");
            ci.setTitle("非著名程序员" + i);
            list.add(ci);
        }
        return list;
    }

}
