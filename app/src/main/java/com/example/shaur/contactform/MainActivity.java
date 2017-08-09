package com.example.shaur.contactform;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import static android.R.attr.name;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.webkit.WebSettings.PluginState.ON;
import static com.example.shaur.contactform.R.id.body;
import static com.example.shaur.contactform.R.id.subject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView vi = (ImageView) findViewById(R.id.emoji);

        RatingBar rating = (RatingBar) findViewById(R.id.rating);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                if(v==5.0)
                {
                   vi.setImageResource(R.drawable.smile);
                }
                else if(v==4.0)
                {
                    vi.setImageResource(R.drawable.smiling);
                }
                else if(v==3.0)
                {
                    vi.setImageResource(R.drawable.ok);
                }
                else if(v==2.0)
                {
                    vi.setImageResource(R.drawable.sad);
                }
                else
                {
                    vi.setImageResource(R.drawable.cry);
                }
            }
        });

        //Send button Listener
         Button submit = (Button) findViewById(R.id.sendbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String message= "null";
                String Sname = "null",Ssubject="null",body= "null";

                //for name
                EditText name = (EditText) findViewById(R.id.Name);
                Sname = name.getText().toString();

                //for subject
                EditText subject = (EditText) findViewById(R.id.subject);
                Ssubject = subject.getText().toString();

                //for body
                EditText bdy = (EditText) findViewById(R.id.body);
                body = bdy.getText().toString();

                //for star rating
                RatingBar rbar=(RatingBar) findViewById(R.id.rating);
                float  rating = rbar.getRating();


                message="#Name ->"+Sname+"\n"+"#Subject -> "+Ssubject+"\n"+"#Message ->\n"+body+"\n#Rating Given by "+Sname+" "+rating+" Stars";


                Log.v("Email",message);


                String[] address = {"shauryauppal00111@gmail.com","bsaraansh@gmail.com"};

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL,address);
                    intent.putExtra(Intent.EXTRA_SUBJECT, Ssubject);
                    intent.putExtra(Intent.EXTRA_TEXT,message);

                if(!Sname.equals("") && !body.equals("")) {
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        Toast.makeText(MainActivity.this, "Sending...", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
                else{
                    if(Sname.equals("") && body.equals(""))
                        Toast.makeText(MainActivity.this, "Fill Blank Fields", Toast.LENGTH_SHORT).show();
                    else if(Sname.equals(""))
                        Toast.makeText(MainActivity.this, "Name Required", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Message Required", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
