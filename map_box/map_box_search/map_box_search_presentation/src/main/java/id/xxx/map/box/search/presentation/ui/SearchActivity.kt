package id.xxx.map.box.search.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.commit
import id.xxx.base.presentation.binding.activity.BaseActivity
import id.xxx.base.presentation.binding.delegate.viewBinding
import id.xxx.base.presentation.extension.setHomeButton
import id.xxx.map.box.search.presentation.R
import id.xxx.map.box.search.presentation.databinding.ActivitySearchBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
    }

    private val viewModel by viewModels<SearchViewModel>()

    override val binding by viewBinding(ActivitySearchBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar.setHomeButton(isHomeButtonEnabled = true, isDisplayHomeAsUpEnabled = true)

//        lifecycleScope.launch {
//            binding.searchView.asStatFlow()
//                .debounce { if (it != null && it.length <= 1) 0 else 1000 }
//                .distinctUntilChanged()
//                .collect { viewModel.query(it) }
//        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.query(newText)
                return true
            }
        })

        supportFragmentManager.commit {
            add(R.id.nav_host_search, SearchFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onBackPressed().run { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun SearchView.asStatFlow(): StateFlow<String?> {

        val query = MutableStateFlow<String?>(null)

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                query.value = newText
                return true
            }
        })
        return query
    }
}