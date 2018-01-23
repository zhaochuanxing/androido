package com.xing.ando.androido;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xing.ando.androido.services.JobScheduleService;

public class MasterActivity extends AppCompatActivity {

    private Button mStartJobBtn;
    private JobScheduler mJobScheduler;
    private Button mStopJobBtn;
    public static final String TAG =MasterActivity.class.getSimpleName() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        mStartJobBtn = (Button)findViewById(R.id.button);
        mStopJobBtn = (Button)findViewById(R.id.button2);
        mJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);

        mStartJobBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JobInfo.Builder builder = new JobInfo.Builder(1,new ComponentName(getPackageName(),JobScheduleService.class.getName()));
                // to run periodically every three seconds.
                builder.setPeriodic(3000);

                if(mJobScheduler.schedule(builder.build())== JobScheduler.RESULT_FAILURE){
                    Log.i(TAG,"fail");
                }
            }
        });

        mStopJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJobScheduler.cancelAll();
            }
        });
    }

    private void initJob(){

    }
}
