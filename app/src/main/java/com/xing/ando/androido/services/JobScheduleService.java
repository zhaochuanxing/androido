package com.xing.ando.androido.services;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.MalformedJsonException;
import android.widget.Toast;

/**
 * Created by zhao on 18-1-18.
 */

public class JobScheduleService extends JobService {

    private static final int MSG_CODE = 1;
    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "job is running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters)msg.obj,false);
            return false;
        }

    });

    @Override
    public boolean onStartJob(JobParameters params) {
        mJobHandler.sendMessage(Message.obtain(mJobHandler,MSG_CODE,params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHandler.removeMessages(MSG_CODE);
        return false;
    }

}
