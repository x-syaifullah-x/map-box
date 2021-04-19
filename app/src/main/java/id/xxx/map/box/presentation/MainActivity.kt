package id.xxx.map.box.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import id.xxx.base.presentation.binding.activity.BaseActivity
import id.xxx.base.presentation.binding.delegate.viewBinding
import id.xxx.map.box.databinding.ActivityMainBinding
import id.xxx.map.box.search.domain.model.PlacesModel
import id.xxx.map.box.search.presentation.ui.SearchActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val REQUEST_CODE = 2121
    }

    override val binding by viewBinding { ActivityMainBinding.inflate(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)


        binding.btnSearch.setOnClickListener {
            startActivityForResult(Intent(this, SearchActivity::class.java), REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val message = data?.getParcelableExtra<PlacesModel>(SearchActivity.DATA_EXTRA)
            Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}