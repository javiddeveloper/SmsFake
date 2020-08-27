//package com.sattar.j.smsfake.view;
//
//import android.app.Activity;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.icu.util.Calendar;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.preference.PreferenceManager;
//import android.provider.ContactsContract;
//import android.provider.Telephony;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.navigation.NavigationView;
//
//import androidx.core.view.GravityCompat;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.widget.Toolbar;
//
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
////import com.github.florent37.materialtextfield.MaterialTextField;
//import com.google.android.material.textfield.TextInputEditText;
//import com.infideap.drawerbehavior.AdvanceDrawerLayout;
//import com.jaredrummler.materialspinner.MaterialSpinner;
//import com.marcoscg.dialogsheet.DialogSheet;
//import com.sattar.j.smsfake.CallenderAndTime;
//import com.sattar.j.smsfake.R;
//import com.sattar.j.smsfake.tools.customViews.CustomTextView;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {
//    private Context context;
//    private Button addContact, reset;
//    private TextInputEditText phone, bodyInput;
//    private FloatingActionButton send;
//    private String sender, body, result = "0";
//    private CustomTextView timeTxt, dateTxt;
//    private MaterialSpinner spinner;
//    private SweetAlertDialog pDialog;
//    private ContentValues values;
//    private CallenderAndTime cTime = new CallenderAndTime();
//    private Calendar cal;
//    private static final int RESULT_PICK_CONTACT = 100;
//    private boolean backed = false;
//    private boolean firstOpen = false;
//    private boolean checkPermission = false;
//    private String defaultSmsApp;
//    private AdvanceDrawerLayout drawer;
//    private ActionBarDrawerToggle toggle;
//    private Toolbar toolbar;
//    private NavigationView navigationView;
//    private String myPackageName;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_advance_3d_1);
//        init();
//        spinner.setItems("ارسال میکنم", "دریافت میکنم", "از ایرانسل", "از همراه اول", "از رایتل");
//        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                result = String.valueOf(position);
//                if (position == 0) {
//                    numberIsGone(false);
//                } else if (position == 1) {
//                    numberIsGone(false);
//
//                } else if (position == 2) {
//                    numberIsGone(true);
//
//                } else if (position == 3) {
//                    numberIsGone(true);
//
//                } else if (position == 4) {
//                    numberIsGone(true);
//                }
//            }
//        });
//        timeTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timeDialog();
//            }
//        });
//        addContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
//            }
//        });
//
//        dateTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dateDialog();
//            }
//        });
//        send.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                setDefaultSmsApp();
//                if (checkPermission) {
//                    sender = phone.getText().toString();
//                    body = bodyInput.getText().toString();
//                    sendSms(result, body, sender);
//                }
//
//            }
//        });
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                phone.setText("");
//                bodyInput.setText("");
//            }
//        });
//    }
//
//    @Override
//    public void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (reqCode) {
//                case RESULT_PICK_CONTACT:
//                    contactPicked(data);
//                    break;
//            }
//        } else {
//            Log.e("MainActivity", "Failed to pick contact");
//        }
//        if (reqCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                checkPermission = true;
//                Toast.makeText(context, "مجددا دکمه ارسال را فشار دهید", Toast.LENGTH_SHORT).show();
//
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(context, "دسترسی لغو شد لطفا مجددا سعی نمایید.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void contactPicked(Intent data) {
//        Cursor cursor = null;
//        try {
//            String phoneNo = null;
//            String name = null;
//            Uri uri = data.getData();
//            cursor = getContentResolver().query(uri, null, null, null, null);
//            cursor.moveToFirst();
//            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//            phoneNo = cursor.getString(phoneIndex);
//            name = cursor.getString(nameIndex);
//            phone.setText(phoneNo);
//            Toast.makeText(context, name + " : ارسال برای  ", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void setDefaultSmsApp() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !checkPermission) {
//            myPackageName = getPackageName();
//            if (!Telephony.Sms.getDefaultSmsPackage(context).equals(myPackageName)) {
//                Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, context.getPackageName());
//                startActivityForResult(intent, 1);
//
//            } else {
//                checkPermission = true;
//            }
//        }
//    }
//
//
//    private void sendSms(String result, String message, String phoneNumber) {
//        values = new ContentValues();
//        switch (result) {
//            case "0":
//                if (!checkNull())
//                    break;
//                sendSmsMethod(phoneNumber, cTime.getCalTime(), message);
//                break;
//            case "1":
//                if (!checkNull())
//                    break;
//                getSms(phoneNumber, cTime.getCalTime(), message);
//                break;
//            case "2":
//                getSms(".IRANCELL.", cTime.getCalTime(), message);
//                break;
//
//            case "3":
//
//                getSms("HAMRAHEAVAL", cTime.getCalTime(), message);
//                break;
//            case "4":
//
//                getSms("RighTel", cTime.getCalTime(), message);
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    private void getSms(String phoneNumber, long now, String message) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            values.put(Telephony.Sms.ADDRESS, phoneNumber);
//            values.put(Telephony.Sms.DATE, now);
//            values.put(Telephony.Sms.BODY, message);
//            values.put(Telephony.Sms.READ, 0);
//            context.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, values);
//            defualtAppSms();
//            msg();
//
//        } else {
//            values.put(Telephony.Sms.ADDRESS, phoneNumber);
//            values.put(Telephony.Sms.DATE, now);
//            values.put(Telephony.Sms.BODY, message);
//            values.put(Telephony.Sms.READ, 0);
//            context.getContentResolver().insert(Uri.parse("content://sms_icon/inbox"), values);
//            defualtAppSms();
//            msg();
//        }
//    }
//
//    private boolean sendSmsMethod(String phoneNumber, long calTime, String message) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            values.put(Telephony.Sms.ADDRESS, phoneNumber);
//            values.put(Telephony.Sms.DATE, calTime);
//            values.put(Telephony.Sms.BODY, message);
//            context.getContentResolver().insert(Telephony.Sms.Sent.CONTENT_URI, values);
//            defualtAppSms();
//            msg();
//
//        } else {
//            values.put(Telephony.Sms.ADDRESS, phoneNumber);
//            values.put(Telephony.Sms.DATE, calTime);
//            values.put(Telephony.Sms.BODY, message);
//            context.getContentResolver().insert(Uri.parse("content://sms_icon/sent"), values);
//            defualtAppSms();
//            msg();
//        }
//
//
//        return true;
//    }
//
//    private void defualtAppSms() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsApp);
//            startActivity(intent);
//            checkPermission = false;
//        } else {
//            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsApp);
//            startActivity(intent);
//            checkPermission = false;
//
//        }
//    }
//
//    private void msg() {
//        if (!firstOpen) {
//            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
//                    .setTitleText("عملیات موفق!!")
//                    .setContentText("برای مشاهده نتیجه وارد پیامهای خود شوید.")
//                    .setConfirmText("تایید")
//                    .show();
////            firstOpen=true;
//        }
//    }
//
//    private void numberIsGone(boolean b) {
//        if (b) {
//            phone.setVisibility(View.GONE);
//            addContact.setVisibility(View.GONE);
//        } else {
//            phone.setVisibility(View.VISIBLE);
//            addContact.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private boolean checkNull() {
//        if (phone.getText().toString().equals("")) {
//            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("اخطار")
//                    .setContentText("شماره تماس خالی است")
//                    .setConfirmText("تایید")
//                    .show();
//            return false;
//        } else return true;
//    }
//
//    private void init() {
//        context = this;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            defaultSmsApp = Telephony.Sms.getDefaultSmsPackage(context);
//        }
//        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        phone = findViewById(R.id.phone);
//        toolbar = findViewById(R.id.toolbar);
//
//        drawer = findViewById(R.id.drawer_layout);
//        toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        drawer.setViewScale(Gravity.START, 0.85f);
//        drawer.setRadius(Gravity.START, 35);
//        drawer.setViewElevation(Gravity.START, 8);
//        toggle.syncState();
//        send = findViewById(R.id.send);
//        addContact = findViewById(R.id.addContact);
//        reset = findViewById(R.id.reset);
//        timeTxt = findViewById(R.id.timeTxt);
//        dateTxt = findViewById(R.id.dateTxt);
//        dateTxt.setText(cTime.getYear() + " / " + cTime.getMonth() + " / " + cTime.getDay());
////        senderInput = findViewById(R.id.sender);
//        bodyInput = findViewById(R.id.message);
//        spinner = findViewById(R.id.spinner);
//        pDialog.setCancelable(false);
//        showTime();
//        showMsg();
//    }
//
//
//    private void showMsg() {
//
//        boolean val =
//                PreferenceManager.getDefaultSharedPreferences(this)
//                        .getBoolean("firstShow", true);
//        if (val) {
//            PreferenceManager.getDefaultSharedPreferences(this).edit()
//                    .putBoolean("firstShow", false).apply();
//            SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
//            dialog.setTitleText("اخطار");
//            dialog.setContentText("کاربر گرامی تولید کننده هیچ گونه مسئولیتی در قبال سوء استفاده از این برنامه را ندارد");
//            dialog.setConfirmText("تایید");
//            dialog.setCancelable(false);
//            dialog.show();
//        }
//
//    }
//
//    private void showTime() {
//        final int hour = cTime.getHour();
//        final int min = cTime.getMin();
//        timeTxt.setText(hour + " : " + min);
//    }
//
//    private void timeDialog() {
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        View timeP = inflater.inflate(R.layout.time_picker, null);
//        final TimePicker tp = timeP.findViewById(R.id.timePicker);
//        final SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
//        dialog.setTitleText("ساعت ارسال");
//        dialog.setCustomView(timeP);
//        dialog.setCancelText("لغو");
//        dialog.setConfirmButton("تایید", new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    cal = Calendar.getInstance();
//                    cTime.setHour(tp.getHour());
//                    cTime.setMin(tp.getMinute());
//                    cTime.setSec(0);
//                    cal.set(cTime.getYear(), cTime.getMonth(), cTime.getDay(), cTime.getHour(), cTime.getMin());
//                } else {
//                    java.util.Calendar cal = java.util.Calendar.getInstance();
//                    cTime.setHour(tp.getCurrentHour());
//                    cTime.setMin(tp.getCurrentMinute());
//                    cTime.setSec(0);
//                    cal.set(cTime.getYear(), cTime.getMonth(), cTime.getDay(), cTime.getHour(), cTime.getMin());
//                }
//
//                timeTxt.setText(tp.getCurrentHour() + " : " + tp.getCurrentMinute());
//                dialog.dismiss();
//            }
//        })
//                .show();
//    }
//
//    private void dateDialog() {
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        View dateP = inflater.inflate(R.layout.date_picker, null);
//        final DatePicker dp = dateP.findViewById(R.id.datePicker);
//        final SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
//        dialog.setTitleText("تاریخ ارسال");
//        dialog.setCustomView(dateP);
//        dialog.setCancelText("لغو");
//        dialog.setNeutralText("امروز");
//        dialog.setConfirmButton("تایید", new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    cal = Calendar.getInstance();
//                    cTime.setYear(dp.getYear());
//                    cTime.setMonth(dp.getMonth());
//                    cTime.setDay(dp.getDayOfMonth());
//                    cal.set(cTime.getYear(), cTime.getMonth(), cTime.getDay());
//                    cTime.setNow(cal.getTimeInMillis());
//                } else {
//                    java.util.Calendar cal = java.util.Calendar.getInstance();
//                    cTime.setYear(dp.getYear());
//                    cTime.setMonth(dp.getMonth());
//                    cTime.setDay(dp.getDayOfMonth());
//                    cal.set(cTime.getYear(), cTime.getMonth(), cTime.getDay());
//                    cTime.setNow(cal.getTimeInMillis());
//                }
//
//                dateTxt.setText(dp.getYear() + " / " + dp.getMonth() + " / " + dp.getDayOfMonth());
//                dialog.dismiss();
//            }
//        })
//                .show();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        if (backed)
//            super.onBackPressed();
//        else {
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            }
//            backed = true;
//            Toast.makeText(context, "مجدد دکمه برگشت را فشار دهید", Toast.LENGTH_SHORT).show();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    backed = false;
//                }
//            }, 3000);
//        }
//    }
//
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_star:
//                alertAbout(2, R.string.rateTitle, R.string.rateMsg, R.string.inMykey,
//                        R.string.cancelDialog);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            case R.id.nav_email:
//                alertAbout(1, R.string.sendEmail, R.string.messageEmailSend, R.string.sendEmail,
//                        R.string.cancelDialog);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            case R.id.nav_prouducts:
//                alertAbout(3, R.string.appsTitle, R.string.appsMsg, R.string.appsShow,
//                        R.string.cancelDialog);
//                drawer.closeDrawer(GravityCompat.START);
//                break;
//            default:
//                return true;
//        }
//        return true;
//    }
//
//    void alertAbout(final int prosecc, int title, int message, int activeBtn, int positiveBtn) {
////
//        try {
//            final DialogSheet dialogSheet = new DialogSheet(this,true);
//            dialogSheet.setTitle(title);
//            dialogSheet.setMessage(message);
//            dialogSheet.setRoundedCorners(true);
//            dialogSheet.setCancelable(true);
//            dialogSheet.setPositiveButton(activeBtn, new DialogSheet.OnPositiveClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (prosecc == 1) {
//                        try {
//                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                                    "mailto", getString(R.string.gmail), null));
//                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.EmailSubject));
//                            startActivity(Intent.createChooser(intent, getString(R.string.gmail)));
//                        } catch (Exception e) {
//                            Toast.makeText(context, "برنامه ای برای ارسال ایمیل یافت نشد", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else if (prosecc == 2) {
//                        try {
//                            Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.setData(Uri.parse(getString(R.string.NazarMyket)));
//                            startActivity(intent);
//                        } catch (Exception e) {
//                            Toast.makeText(context, "برنامه مایکت بر روی تلفن هوشمند شما یافت نشد", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else {
//                        try {
//                            Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.setData(Uri.parse(getString(R.string.MyPage)));
//                            startActivity(intent);
//                        } catch (Exception e) {
//                            Toast.makeText(context, "برنامه مرورگر بر روی تلفن هوشمند شما یافت نشد", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                }
//            });
//            dialogSheet.setNegativeButton(positiveBtn, new DialogSheet.OnNegativeClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialogSheet.dismiss();
//                }
//            });
//
//            dialogSheet.setBackgroundColor(Color.WHITE); // Your custom background color
//            dialogSheet.setButtonsColorRes(R.color.colorPrimary);  // Default color is accent
//            dialogSheet.show();
//        } catch (Exception e) {
//
//
//        }
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        return super.onOptionsItemSelected(item);
//    }
//}