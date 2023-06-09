package com.v1.application.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.v1.application.databinding.FragmentMainListBinding
import com.v1.application.model.ResponseMainList
import com.v1.application.viewmodel.MainListViewModel

class MainListFragment: Fragment() {
    companion object {
        val TAG: String = MainListFragment::class.java.simpleName
    }
    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainListViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainListViewModel::class.java)
    }
    private lateinit var mAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
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
            mainListView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mAdapter = MainListAdapter(object : MainListCallback {
                    override fun onClick(item: ResponseMainList.MainListItem) {
                        viewModel.updateSelectItem(item)
                    }

                    override fun onClickZzim(item: ResponseMainList.MainListItem) {
                        item.zzim = !item.zzim
                        mAdapter.notifyItemChanged(mAdapter.getItemPosition(item))
                    }

                    override fun onClickLink(url: String) {
                    }
                })
                adapter = mAdapter
            }
        }
    }

    private fun initLData() {
        viewModel.getMainListLiveData().observe(viewLifecycleOwner, Observer { response ->
            response?.itemModelList?.run {
                response?.title?.let { mAdapter.setTitle(it) }
                mAdapter.setItemList(this)
                mAdapter.notifyItemRangeChanged(0, this.size)
            }
        })
    }
}