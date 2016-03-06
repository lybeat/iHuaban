package com.lybeat.huaban.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;

import com.lybeat.huaban.R;

/**
 * Author: mingyu.sun
 * Date: 2016/3/4
 */
public class RefreshLayout extends SwipeRefreshLayout {

    private int touchSlop;
    private boolean isLoading;
    private int downY;
    private int upY;
    private OnLoadMoreListener loadMoreListener;
    private View footerView;
    private View child;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        footerView = LayoutInflater.from(context).inflate(R.layout.refresh_footer, null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setScrollListener();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                upY = (int) ev.getRawY();
                if (canLoad()) {
                    loadMore();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean canLoad() {
        return (upY - downY) > touchSlop && !isLoading() && !isRefreshing() && isScrolledBottom();
    }

    private void loadMore() {

    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private boolean isScrolledBottom() {
        if (child instanceof AbsListView) {
            if (((AbsListView) child).getAdapter() != null) {
                return ((AbsListView) child).getLastVisiblePosition()
                        == ((AbsListView) child).getAdapter().getCount() - 1;
            }
        } else if (child instanceof RecyclerView) {
            if (((RecyclerView) child).getAdapter() != null) {
                RecyclerView.LayoutManager manager = ((RecyclerView) child).getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    int position = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                    if (position == manager.getChildCount() - 1 && manager.findViewByPosition(position).getBottom() == getHeight()) {
                        return true;
                    }
                } else if (manager instanceof StaggeredGridLayoutManager) {
                    return false;
                }
            }
        }

        return false;
    }

    private void setScrollListener() {
//        Log.i("MainActivity", "childCount: " + getChildCount());
        View view0 = getChildAt(0);
        View view1 = getChildAt(1);
//        Log.i("MainActivity", "view0: " + view0.getClass());
//        Log.i("MainActivity", "view1: " + view1.getClass());
        if (getChildCount() > 0) {
            child = getChildAt(0);
            if (child instanceof AbsListView) {
                ((AbsListView) child).setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if (((AbsListView) child).getLastVisiblePosition()
                                == ((AbsListView) child).getAdapter().getCount() - 1
                                && !isLoading() && !isRefreshing()) {
                            Log.i("MainActivity", "ScrolledBottom");
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        if (canLoad()) {
                            loadMore();
                        }
                    }
                });
            } else if (child instanceof RecyclerView) {
                ((RecyclerView) child).addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        RecyclerView.LayoutManager manager = ((RecyclerView) child).getLayoutManager();
                        if (manager instanceof LinearLayoutManager) {
                            int position = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                            if (position == manager.getItemCount() - 1
                                    && manager.findViewByPosition(position).getBottom() <= getHeight()) {
//                                Log.i("MainActivity", "newState: " + newState);
                                if (!isLoading) {
                                    isLoading = true;
                                    loadMoreListener.onLoadMore();
                                }
                            }
                        } else if (manager instanceof StaggeredGridLayoutManager) {
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
            }
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
