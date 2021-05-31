package sg.edu.rp.c346.id20013676.mylocalbanks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textDBS;
    TextView textOCBC;
    TextView textUOB;

    String wordClicked = "";
    boolean uobFav = false;
    boolean dbsFav = false;
    boolean ocbcFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDBS = findViewById(R.id.textViewDBS);
        textOCBC = findViewById(R.id.textViewOCBC);
        textUOB = findViewById(R.id.textViewUOB);

        registerForContextMenu(textDBS);
        registerForContextMenu(textOCBC);
        registerForContextMenu(textUOB);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_context, menu);

        if (v == textDBS) {
            wordClicked = "DBS";
        }
        else if (v == textOCBC) {
            wordClicked = "OCBC";
        }
        else if (v == textUOB) {
            wordClicked = "UOB";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.EnglishSelection) {
            textUOB.setText("UOB");
            textOCBC.setText("OCBC");
            textDBS.setText("DBS");
            return true;
        } else if (id == R.id.ChineseSelection) {
            textUOB.setText("星展银行");
            textOCBC.setText("华侨银行");
            textDBS.setText("大华银行");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (wordClicked.equalsIgnoreCase("UOB")) {
            if(id == R.id.FavSelection) {
                uobFav = setFav(uobFav, textUOB);
                return true;
            }
            else {
                return launchIntent(id, getString(R.string.uob_website), getString(R.string.uob_phone));
            }
        }
        else if (wordClicked.equalsIgnoreCase("OCBC")) {
            if(id == R.id.FavSelection) {
                ocbcFav = setFav(ocbcFav, textOCBC);
                return true;
            }
            else {
                return launchIntent(id, getString(R.string.ocbc_website), getString(R.string.ocbc_phone));
            }

        }
        else if (wordClicked.equalsIgnoreCase("DBS")) {
            if(id == R.id.FavSelection) {
                dbsFav = setFav(dbsFav, textDBS);
                return true;
            }
            else {
                return launchIntent(id, getString(R.string.dbs_website), getString(R.string.dbs_phone));
            }
        }

        return super.onContextItemSelected(item); //pass menu item to the superclass implementation
    }

    public boolean launchIntent(int id, String website, String phone) {
        if(id == R.id.WebsiteSelection) {
            Intent intent = new Intent(Intent. ACTION_VIEW,
                    Uri.parse(website));
            startActivity(intent);
            return true;
        }
        else if(id == R.id.ContactSelection) {
            Intent intentCall = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phone));
            startActivity(intentCall);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setFav(boolean fav, TextView bank) {
        if(!fav) {
            bank.setTextColor(Color.RED);
            return true;
        }
        else {
            bank.setTextColor(Color.DKGRAY);
            return false;
        }
    }



}