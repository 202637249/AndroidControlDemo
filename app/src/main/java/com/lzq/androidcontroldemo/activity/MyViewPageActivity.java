package com.lzq.androidcontroldemo.activity;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzq.androidcontroldemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyViewPageActivity extends AppCompatActivity {


    private ViewPager vp_main;
    private TextView tv_main_msg;
    private LinearLayout ll_main_points;

    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e, R.drawable.f};
    private String[] imageMessages = {"1", "2", "3!", "4", "5", "6"};
    private List<ImageView> imageViews = new ArrayList<ImageView>();
    private List<ImageView> pointIVs = new ArrayList<ImageView>();
    private MyAdapter adapter;
    private int currentPos = 0;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    //将当前page设置为下一个page
                    int currentItem = vp_main.getCurrentItem();
                    vp_main.setCurrentItem(currentItem + 1, true);
                    //发送延迟3s消息再次滑动到下一页
                    handler.sendEmptyMessageDelayed(1, 3000);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_page);
        initView();
    }

    public void initView() {
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        tv_main_msg = (TextView) findViewById(R.id.tv_main_msg);
        ll_main_points = (LinearLayout) findViewById(R.id.ll_main_points);

        //创建用于显示点图标的布局参数对象
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;
        params.rightMargin = 5;


        for (int i = 0; i < images.length; i++) {
            //创建将要显示到ViewPager中的ImageView对象
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            //将下标保存到imageView的tag
            imageView.setTag(i);
            //设置imageView点击监听
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "position="+v.getTag(), 0).show();
                }
            });
            //保存到list中
            imageViews.add(imageView);

            //创建用于显示指示点图片的ImageView对象
            ImageView pointIV = new ImageView(this);
            //设置多状态背景图片
            pointIV.setBackgroundResource(R.drawable.point_selector);
            //让第一个初始显示为当前点图片, 其它显示为一般的点图片
            if (i == 0) {
                pointIV.setEnabled(true);
            } else {
                pointIV.setEnabled(false);
            }
            //用集合保存
            pointIVs.add(pointIV);
            //添加到RadioGroup中显示
            ll_main_points.addView(pointIV, params);
        }

        //创建adapter对象
        adapter = new MyAdapter();
        //设置adapter显示ViewPager
        vp_main.setAdapter(adapter);
        //计算ViewPager中当前Page的下标
        int currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % images.length;
        //初始显示到当前page
        vp_main.setCurrentItem(currentItem);
        //设置当前page对应的文本
        tv_main_msg.setText(imageMessages[0]);
        //设置ViewPager页面改变的监听
        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 当当前page有变化时回调
             * 	1. 更新圆点图标
             *  2. 更新当前文本
             */
            @Override
            public void onPageSelected(int position) {
                Log.e("TAG", "onPageSelected() " + position);

                //1. 更新圆点图标
                pointIVs.get(currentPos).setEnabled(false);
                position = position % images.length;
                pointIVs.get(position).setEnabled(true);
                currentPos = position;

                //2. 更新当前文本
                tv_main_msg.setText(imageMessages[position]);
            }

            /**
             * 当page滚动时回调
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // Log.e("TAG", "onPageScrolled() "+position);
            }

            /**
             * page的滚动状态改变时调用
             * @see ViewPager#SCROLL_STATE_IDLE : 空闲状态(滑动后停止下来)
             * @see ViewPager#SCROLL_STATE_DRAGGING : 正在拖拽中(只调用1次)
             * @see ViewPager#SCROLL_STATE_SETTLING : 自动沉降，
             * 				相当于松手后，pager恢复到一个完整pager的过程
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.e("TAG", "onPageScrollStateChanged() " + state);
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    Log.e("TAG", "----SCROLL_STATE_DRAGGING");
                    handler.removeMessages(1);
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.e("TAG", "----SCROLL_STATE_IDLE");
                    handler.sendEmptyMessageDelayed(1, 3000);
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    Log.e("TAG", "----SCROLL_STATE_SETTLING");
                    handler.removeMessages(1);
                }
            }
        });
        // 发送消息实现自动切换
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    /*
     * PageView的适配器类
     */
    class MyAdapter extends PagerAdapter {

        /**
         * 返回int的最大值, 使PageView能显示MAX_VALUE个item
         */
        @Override
        public int getCount() {
            //Log.e("TAG", "getCount()");
            return Integer.MAX_VALUE;
        }

        /**
         * 判断view对象是否是instantiateItem()返回的视图对象
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //Log.e("TAG", "isViewFromObject() object="+object);
            return view == object;
        }

        /**
         * 根据position返回对应Page需要显示的视图对象
         * <p/>
         * ViewGroup container : ViewPager对象
         * int position : 下标
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //Log.e("TAG", "instantiateItem() position="+position);
            position = position % images.length;
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 根据position移除对应的page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //Log.e("TAG", "instantiateItem() position="+position);
            container.removeView((View) object);
        }
    }
}
