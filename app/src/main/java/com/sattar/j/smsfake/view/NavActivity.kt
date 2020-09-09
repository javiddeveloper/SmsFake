package com.sattar.j.smsfake.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.marcoscg.dialogsheet.DialogSheet
import com.marcoscg.dialogsheet.DialogSheet.OnNegativeClickListener
import com.marcoscg.dialogsheet.DialogSheet.OnPositiveClickListener
import com.sattar.j.smsfake.R
import com.sattar.j.smsfake.databinding.ActivityNavBinding

class NavActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navControl: NavController
    private lateinit var mBinding: ActivityNavBinding
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_nav)
        navControl = Navigation.findNavController(this, R.id.fragment)
//        NavigationUI.setupActionBarWithNavController(this, navControl)
//        supportActionBar?.hide()

//        navigationView = NavigationView(applicationContext,null,0)
//        navigationView.setNavigationItemSelectedListener(this)
//        toggle = ActionBarDrawerToggle(
//                this
//                , mBinding.drawerLayout
//                , null
//                , R.string.navigation_drawer_open
//                , R.string.navigation_drawer_close)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_star -> {
                alertAbout(2, R.string.rateTitle, R.string.rateMsg, R.string.inMykey,
                        R.string.cancelDialog)
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_email -> {
                alertAbout(1, R.string.sendEmail, R.string.messageEmailSend, R.string.sendEmail,
                        R.string.cancelDialog)
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_prouducts -> {
                alertAbout(3, R.string.appsTitle, R.string.appsMsg, R.string.appsShow,
                        R.string.cancelDialog)
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> return true
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
    private fun alertAbout(prosecc: Int, title: Int, message: Int, activeBtn: Int, positiveBtn: Int) {

        try {
            val dialogSheet = DialogSheet(this, true)
            dialogSheet.setTitle(title)
            dialogSheet.setMessage(message)
            dialogSheet.setRoundedCorners(true)
            dialogSheet.setCancelable(true)
            dialogSheet.setPositiveButton(activeBtn, object : OnPositiveClickListener {
                override fun onClick(v: View?) {
                    if (prosecc == 1) {
                        try {
                            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", getString(R.string.gmail), null))
                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.EmailSubject))
                            startActivity(Intent.createChooser(intent, getString(R.string.gmail)))
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, "برنامه ای برای ارسال ایمیل یافت نشد", Toast.LENGTH_SHORT).show()
                        }
                    } else if (prosecc == 2) {
                        try {
                            val intent = Intent()
                            intent.action = Intent.ACTION_VIEW
                            intent.data = Uri.parse(getString(R.string.NazarMyket))
                            startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, "برنامه مایکت بر روی تلفن هوشمند شما یافت نشد", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        try {
                            val intent = Intent()
                            intent.action = Intent.ACTION_VIEW
                            intent.data = Uri.parse(getString(R.string.MyPage))
                            startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, "برنامه مرورگر بر روی تلفن هوشمند شما یافت نشد", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            dialogSheet.setNegativeButton(positiveBtn, object : OnNegativeClickListener {
                override fun onClick(v: View?) {
                    dialogSheet.dismiss()
                }
            })
            dialogSheet.setBackgroundColor(Color.WHITE) // Your custom background color
            dialogSheet.setButtonsColorRes(R.color.colorPrimary) // Default color is accent
            dialogSheet.show()
        } catch (e: Exception) {
        }
    }

}