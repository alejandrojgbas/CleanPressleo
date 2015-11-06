package cleanpress.cleanpress;


import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;


public class FbLogin extends ActionBarActivity{

    TextView fbname,fbid,fbmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fblogin);

        fbname = (TextView) findViewById(R.id.fbname);
        fbid = (TextView) findViewById(R.id.fbname);
        fbname = (TextView) findViewById(R.id.fbname);

    }
}
