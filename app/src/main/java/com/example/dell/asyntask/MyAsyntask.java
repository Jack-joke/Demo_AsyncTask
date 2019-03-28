package com.example.dell.asyntask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyntask extends AsyncTask<Void, Integer, Void> {
    //khai báo Activity để lưu trữ địa chỉ của MainActivity

    Activity contextCha;
    //constructor này được truyền vào là MainActivity
    public MyAsyntask(Activity ctx)
    {
        contextCha = ctx;
    }
    //hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }
    //sau đó tới hàm doInBackground
    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            //nghỉ 100 milisecond (0.1s)thì tiến hành update UI
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }
    /**
     * ta cập nhập giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer[] values) {
        super.onProgressUpdate(values);
        //thông qua contextCha để lấy được control trong MainActivity
        ProgressBar paCha=(ProgressBar)contextCha.findViewById(R.id.progressBar1);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int giatri = values[0];
        //tăng giá trị của Progressbar lên
        paCha.setProgress(giatri);
        //đồng thời hiện thị giá trị là % lên TextView
        TextView txtmsg = (TextView)contextCha.findViewById(R.id.textView1);
        txtmsg.setText("Loading " + giatri + "%");
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update Successfully!", Toast.LENGTH_LONG).show();
    }

}
