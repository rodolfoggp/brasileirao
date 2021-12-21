package com.brasileirao.presentation

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.brasileirao.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        with(supportFragmentManager) {
            if (backStackEntryCount > 0) {
                popBackStackImmediate()
            } else {
                super.onBackPressed()
            }
        }
    }
}