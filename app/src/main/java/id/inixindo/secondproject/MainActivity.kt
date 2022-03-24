package id.inixindo.secondproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter

    // icon dan title
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.icon_home, "Home"),
        NavigationItemModel(R.drawable.icon_gallery, "Gallery"),
        NavigationItemModel(R.drawable.icon_settings, "Settings"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(activity_main_toolbar)

        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

        // memanggil event handling recyclerview
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                // memanggil fragments
                when (position) {
                    0 -> {
                        val homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        val galleryFragment = GalleryFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, galleryFragment).commit()
                    }
                    2 -> {
                        val settingsFragment = SettingsFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, settingsFragment).commit()
                    }
                }

                if (position != 1 && position != 2) {
                    updateAdapter(position)
                }

                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 500)
            }
        }))

        updateAdapter(0)
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()

        // menampilkan icon hamburger
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawerLayout, activity_main_toolbar,
            R.string.openDrawer, R.string.closeDrawer
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //navigation_header_img.setImageResource(R.drawable.logo)
        //navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_search) {
            Toast.makeText(this, "Search is selected", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.option_settings) {
            Toast.makeText(this, "Settings is selected", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.option_status) {
            Toast.makeText(this, "Status is selected", Toast.LENGTH_SHORT).show()
        } else if (item.itemId == R.id.option_database) {
            // membuka activity untuk mengelola database
            startActivity(Intent(this, DatabaseActivity::class.java))
        } else {
            Toast.makeText(this, "None is selected", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}