package com.shatokhin.navigphotoofcats.presentation.fragments

import android.content.Context
import android.net.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.shatokhin.navigphotoofcats.databinding.FragmentVoteImagesCatsBinding
import com.shatokhin.navigphotoofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.navigphotoofcats.presentation.viewmodels.ViewModelMainFactory
import com.shatokhin.navigphotoofcats.utilites.TAG_FOR_LOGCAT


class VoteImagesCatsFragment : Fragment() {
    private val viewModelMain: ViewModelMain by activityViewModels { ViewModelMainFactory() }

    private var _binding: FragmentVoteImagesCatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(requireContext())
        Log.d(TAG_FOR_LOGCAT, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_FOR_LOGCAT, "onCreateView")

        _binding = FragmentVoteImagesCatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG_FOR_LOGCAT, "onViewCreated")

        viewModelMain.listCats.observe(viewLifecycleOwner) { newRandomCat ->
            setImage(newRandomCat.urlImage)
        }

        binding.btnVoteUp.setOnClickListener {
            viewModelMain.voteUpCurrentCat()
        }

        binding.btnVoteDown.setOnClickListener {
            viewModelMain.voteDownCurrentCat()
        }

        registerNetworkCallback()

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_FOR_LOGCAT, "onPause")
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build();

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                viewModelMain.internetConnectionResumed()
            }
        }

        val connectivityManager: ConnectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setImage(urlImage: String) {
        val imageUri = Uri.parse(urlImage)

        val imageRequest = ImageRequestBuilder
            .newBuilderWithSource(imageUri)
            .setProgressiveRenderingEnabled(true)
            .build()

        binding.ivImageCat.setImageRequest(imageRequest)
    }

}