package com.v1.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.v1.application.databinding.ActivityMainBinding
import com.v1.application.view.main.MainDetailFragment
import com.v1.application.view.main.MainListFragment
import com.v1.application.viewmodel.MainListViewModel


class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.simpleName

    private lateinit var _binding: ActivityMainBinding
    val binding get() = _binding

    private val viewModel: MainListViewModel by lazy {
        ViewModelProvider(this).get(MainListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    private fun initData(){
        viewModel.getSelectItem().observe(this, Observer {item ->
            showMainDetailFragment(true)
        })
        viewModel.requestMainListData()
    }

    private fun initView(){
        showMainListFragment(true)
    }

    private fun showMainListFragment(isShow:Boolean) {
        binding.let {
            if(isShow){
                supportFragmentManager.beginTransaction().apply {
                    replace(it.container.id, MainListFragment(), MainListFragment.TAG)
                    commit()
                }
            }else{
                supportFragmentManager.findFragmentByTag(MainListFragment.TAG)?.let { fragment ->
                    supportFragmentManager.beginTransaction().apply {
                        remove(fragment)
                        commit()
                    }
                }
            }
        }
    }

    private fun showMainDetailFragment(isShow:Boolean) {
        binding.let {
            if(isShow){
                supportFragmentManager.beginTransaction().apply {
                    replace(it.container.id, MainDetailFragment(), MainDetailFragment.TAG)
                    commit()
                }
            }else{
                supportFragmentManager.findFragmentByTag(MainDetailFragment.TAG)?.let { fragment ->
                    supportFragmentManager.beginTransaction().apply {
                        remove(fragment)
                        commit()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        binding.let {
            val fragment = supportFragmentManager.findFragmentById(it.container.id)
            fragment?.let {
                if (fragment is MainDetailFragment) {
                    showMainDetailFragment(false)
                    showMainListFragment(true)
                }else{
                    super.onBackPressed()
                }
            }
        }
    }

}