package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.My;
import com.example.user.Order;
import com.example.user.R;
import com.example.user.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar setting
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.page_home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home:
                        return true;

                    case R.id.page_search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.page_order:
                        startActivity(new Intent(getApplicationContext(), Order.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.page_my:
                        startActivity(new Intent(getApplicationContext(), My.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c) {
            context = c; // context 변수를 다른 메소드에서 사용하기 위함
        }

        @Override
        public int getCount() { // 그리드뷰에 보여질 이미지의 개수를 반환하도록 수정
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // 이미지 파일을 배열로 초기화
        Integer[] posterID = {R.drawable.bunsik, R.drawable.korea, R.drawable.china, R.drawable.japan, R.drawable.italy, R.drawable.cafe
                , R.drawable.chicken, R.drawable.pizza, R.drawable.jokbo, R.drawable.yasik};

        // 카테고리를 문자 배열로 초기화
        String[] posterTitle = {"분식", "한식", "중식", "일식", "양식", "카페", "치킨", "피자", "족발/보쌈", "야식"};

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { // 각 그리드뷰의 칸마다 이미지뷰를 생성해서 보여주게 함
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // 그리드뷰 칸의 중앙에 배치
            imageView.setPadding(5, 5, 5, 5);

            imageView.setImageResource(posterID[position]); // R.drawable.mov0x 이미지를 로드하여 출력
            //TODO 이미지를 클릭했을 때, 원래 이미지 크기가 보이도록 함
            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), posterTitle[pos] + "click", Toast.LENGTH_LONG).show();
                }
            });
            return imageView;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                return true;
            case R.id.account:
                Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_LONG).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}