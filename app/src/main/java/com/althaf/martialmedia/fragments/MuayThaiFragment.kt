package com.althaf.martialmedia.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaf.martialmedia.NewsViewModel
import com.althaf.martialmedia.adapter.NewsAdapter
import com.althaf.martialmedia.databinding.FragmentStrikingBinding

class MuayThaiFragment : Fragment() {
    private var _binding: FragmentStrikingBinding? = null
    private val binding get() = _binding as FragmentStrikingBinding
    private var _viewModel: NewsViewModel? = null
    private val viewModel get() = _viewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStrikingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.getStrikingNews()
        viewModel.muayThaiNews.observe(viewLifecycleOwner) {
            val data = it.articles
            Log.i("StrikingFragment", "onResponse: $it ")
            binding.rvStriking.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(data)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}