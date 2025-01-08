package com.v1.application.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.v1.application.R
import com.v1.application.databinding.FragmentMainDetailBinding
import com.v1.application.model.ResponseMainList

import com.v1.application.viewmodel.MainListViewModel

class MainDetailFragment: Fragment() {
    companion object {
        val TAG: String = MainDetailFragment::class.java.simpleName
    }

    private var _binding: FragmentMainDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.run {
            viewModel.getSelectItem().value?.let { item ->
                item.avatarUrl?.let { url ->
                    binding.data = item
                    binding.callback = object : MainListCallback {
                        override fun onClick(item: ResponseMainList.MainListItem) {
                        }

                        override fun onClickZzim(item: ResponseMainList.MainListItem) {
                            item.zzim = !item.zzim
                            btnZzim.setImageResource(if (item.zzim) R.drawable.ic_favor_on else R.drawable.ic_favor_off)
                            viewModel.getMainListLiveData().value.let {
                                val index: Int? = it?.itemModelList?.indexOf(item)
                                index?.let { it1 -> it.itemModelList.set(it1, item) }
                            }
                        }

                        override fun onClickLink(url: String) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                        }
                    }

                }
            }
        }
    }


    private fun initLData() {

    }
}