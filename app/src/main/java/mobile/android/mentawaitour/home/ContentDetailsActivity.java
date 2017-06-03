package mobile.android.mentawaitour.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.doku.sdkocov2.DirectSDK;
import com.doku.sdkocov2.interfaces.iPaymentCallback;
import com.doku.sdkocov2.model.LayoutItems;
import com.doku.sdkocov2.model.PaymentItems;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;
import mobile.android.mentawaitour.R;
import mobile.android.mentawaitour.models.Content;
import mobile.android.mentawaitour.other.ImageAdapter;

public class ContentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.content_text) TextView contentTextView;
    @BindView(R.id.title_text) TextView titleTextView;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.pay_button) Button payButton;
    @BindView(R.id.indicator) CircleIndicator circleIndicator;

    private Content content;
    private final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 223;
    private final String AMOUNT = "10000.00", MALL_ID = "4566", SHARED_KEY = "6bO15H7vizQP", CURRENCY = "360",
            PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjSxjhj+MEqy2f5qZDIdoponw/lFI/zKb5di2iSbuub64MCdkfQS4tFhuChJdr+kqhnrdNrr4KhITmVC1J9BvWmDScEQQRXoexGtk4c0Ulc90BVqL32ZHjUhhfwFEfxg4f0353PTf7Cc7VLmV6WFhMzUbgLtepaEbXzpN+aH+F8nFSiNhO4ifZPzOV5yB7ZxDQ9ME5m4j/5ky65OgZ3d0QIu5EufWNFOJjSHZ0w9CvllZz2Pc8v9niA41lzueeTssCmkuputL9HoDIl41aK8lQwvN0H2HVmWKGXGPvWHQUbttA9J3FiMstCz4kgMHUbF/CHmz8iV9UOzgTMv1HmCHwwIDAQAB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);
        ButterKnife.bind(this);

        content = Content.getByKey(getIntent().getStringExtra("key"));
        contentTextView.setText(content.getDescription());
        titleTextView.setText(content.getName());

        viewPager.setAdapter(new ImageAdapter(this, content.getPhotos()));
        circleIndicator.setViewPager(viewPager);

        if (content.getKey().equals("surfing_retribution")) {
            payButton.setVisibility(View.VISIBLE);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            payButtonOnClick();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Payment cant be start, Please allow permission for phone in apps device settings.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @OnClick(R.id.pay_button)
    public void payButtonOnClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String deviceID = telephonyManager.getDeviceId();
        String transactionID = "1234567";

        DirectSDK directSDK = new DirectSDK();

        PaymentItems paymentItems = new PaymentItems();
        paymentItems.setDataAmount(AMOUNT);
        paymentItems.setDataBasket("[{\"name\":\"retribusi\",\"amount\":\"10000.00\",\"quantity\":\"1\",\"subtotal\":\"10000.00\"}]");
        paymentItems.setDataCurrency(CURRENCY);
        paymentItems.setDataWords(getDataWords(transactionID, deviceID));
        paymentItems.setDataMerchantChain("NA");
        paymentItems.setDataSessionID("qwertyasd");
        paymentItems.setDataTransactionID(transactionID);
        paymentItems.setDataMerchantCode(MALL_ID);
        paymentItems.setDataImei(deviceID);
        paymentItems.setMobilePhone("");
        paymentItems.isProduction(false); //set ‘true’ for production and ‘false’ for development
        paymentItems.setPublicKey(PUBLIC_KEY); //PublicKey can be obtained from the DOKU Back Office

        LayoutItems layoutItems = new LayoutItems();
        layoutItems.setFontPath("fonts/MuseoSansRounded-300.otf");
        layoutItems.setToolbarColor("#5487bd");
        layoutItems.setToolbarTextColor("#FFFFFF");
        layoutItems.setFontColor("#121212");
        layoutItems.setBackgroundColor("#eaeaea");
        layoutItems.setLabelTextColor("#9a9a9a");
        layoutItems.setButtonBackground(getResources().getDrawable(R.drawable.rounded_button));
        layoutItems.setButtonTextColor("#FFFFFF");

        directSDK.setLayout(layoutItems);
        directSDK.setCart_details(paymentItems);
        directSDK.setPaymentChannel(15);
        directSDK.getResponse(new iPaymentCallback() {
            @Override
            public void onSuccess(final String text) {
                try {
                    JSONObject respongetTokenSDK = new JSONObject(text);
                    if (respongetTokenSDK.getString("res_response_code").equalsIgnoreCase("0000")) {
                        //do your background AsyncTask service to merchant server handler here
                        Toast.makeText(ContentDetailsActivity.this, "Payment Surfing Retribution for "
                                + respongetTokenSDK.getString("res_name") + "("
                                + respongetTokenSDK.getString("res_data_email") + ") "
                                + " has been completed", Toast.LENGTH_LONG).show();
                        Log.e("####", "" + respongetTokenSDK);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(final String text) {
                Log.e("ERROR", text);
            }
            @Override
            public void onException(Exception eSDK) {
                eSDK.printStackTrace();
            }
        }, getApplicationContext());
    }

    @OnClick(R.id.title_text)
    public void viewPagerOnClick() {
        Intent intent = new Intent(this, BannerActivity.class);
        intent.putExtra("key", content.getKey());
        startActivity(intent);
    }

    private String getDataWords(String transactionID, String deviceID) {
        String data = AMOUNT + MALL_ID + SHARED_KEY + transactionID + CURRENCY + deviceID;
        Log.e("###", "" + data);
        Log.e("correct", "10000.0045666bO15H7vizQP1234567360358240056038342");
        try {
            Log.e("###", "" + SHA1(data));
            Log.e("correct", "" + SHA1("10000.0045666bO15H7vizQP1234567360358240056038342"));
            return SHA1(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    private String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes("iso-8859-1");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}
