package cn.edu.bistu.cs.se.store19;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final  EditText t;
        Button btnWrite = (Button) findViewById(R.id.btnWrite);
        Button btnRead = (Button) findViewById(R.id.btnRead);
        t=(EditText) findViewById(R.id.test) ;
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out = null;
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/" + "myFileName");
                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content =t.getText()+"";

                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            out.close();

                        } finally {
                            if (out != null) ;
                            out.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/" + "myFileName");
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            in.close();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }
}
