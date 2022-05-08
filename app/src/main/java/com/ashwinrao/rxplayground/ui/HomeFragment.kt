package com.ashwinrao.rxplayground.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashwinrao.rxplayground.R
import com.ashwinrao.rxplayground.RxPlayground
import com.ashwinrao.rxplayground.databinding.FragmentHomeBinding
import com.ashwinrao.rxplayground.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHomeBinding
    private val disposables = CompositeDisposable()

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as RxPlayground).component.inject(this)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initPostList()
        return binding.root
    }

    private fun initPostList() {
        binding.postList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.postList.adapter = TypicodePostAdapter(onPostClicked = { postId: Int? ->
            // todo: navigate to detail fragment
            postId?.let {
                Toast.makeText(context, "Clicked on $postId", Toast.LENGTH_SHORT).show()
            }
        })

        manageSubscription()
    }

    private fun manageSubscription() {
        val disposable = viewModel.fetchPosts().subscribe({ posts ->
            (binding.postList.adapter as TypicodePostAdapter).submitList(posts)
            Log.i(TAG, "Passing ${posts.size} posts to list adapter")
        }, { error ->
            Snackbar.make(binding.root, error.message.toString(), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.label_refresh)) { manageSubscription() }
                .show()
            Log.e(TAG, "Failed to load posts because ${error.message}")
        })
        disposables.add(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
    }
}

