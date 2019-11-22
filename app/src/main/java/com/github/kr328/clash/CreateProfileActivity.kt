package com.github.kr328.clash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_new_profile.*

class CreateProfileActivity : AppCompatActivity() {
    companion object {
        val NEW_PROFILE_SOURCE = listOf(
            AdapterData(R.drawable.ic_new_profile_file,
                R.string.clash_new_profile_file_title,
                R.string.clash_new_profile_file_summary),
            AdapterData(R.drawable.ic_new_profile_url,
                R.string.clash_new_profile_url_title,
                R.string.clash_new_profile_url_summary)
        )
    }

    class Adapter(private val context: Context) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return (convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_new_profile, parent, false)).apply {
                val current = NEW_PROFILE_SOURCE[position]

                findViewById<ImageView>(R.id.adapter_new_profile_icon).setImageResource(current.icon)
                findViewById<TextView>(R.id.adapter_new_profile_title).text = context.getString(current.title)
                findViewById<TextView>(R.id.adapter_new_profile_summary).text = context.getString(current.summary)
            }
        }

        override fun getItem(position: Int): Any {
            return NEW_PROFILE_SOURCE
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return NEW_PROFILE_SOURCE.size
        }
    }

    data class AdapterData(val icon: Int, val title: Int, val summary: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_profile)

        setSupportActionBar(activity_new_profile_toolbar)

        with(activity_new_profile_list) {
            adapter = Adapter(this@CreateProfileActivity)
            setOnItemClickListener {_, _, index, _ ->
                when ( index ) {
                    0 ->
                        startActivity(Intent(this@CreateProfileActivity, ImportFileActivity::class.java))
                }
            }
        }
    }
}