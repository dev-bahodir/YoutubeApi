 package dev.bahodir.youtubeapiapp.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dev.bahodir.youtubeapiapp.R
import dev.bahodir.youtubeapiapp.adapter.MainVPAdapter
import dev.bahodir.youtubeapiapp.databinding.FragmentShortsBinding
import dev.bahodir.youtubeapiapp.function.hide
import dev.bahodir.youtubeapiapp.function.show
import dev.bahodir.youtubeapiapp.model.Item
import dev.bahodir.youtubeapiapp.utils.NetworkHelper
import dev.bahodir.youtubeapiapp.utils.YoutubeResource
import dev.bahodir.youtubeapiapp.viewmodel.ViewModelFactory
import dev.bahodir.youtubeapiapp.viewmodel.YoutubeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

 // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShortsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShortsFragment : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding: FragmentShortsBinding? = null
    private val binding get() = _binding!!

    private lateinit var youtubeViewModel: YoutubeViewModel
    private lateinit var networkHelper: NetworkHelper
    private lateinit var job: Job
    private val TAG = "MainFragment"
    private lateinit var mainVPAdapter: MainVPAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShortsBinding.inflate(inflater, container, false)
        job = Job()
        networkHelper = NetworkHelper(requireContext())
        youtubeViewModel =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[YoutubeViewModel::class.java]


        launch {
            youtubeViewModel
                .getStateVideos()
                .collect {
                    when (it) {
                        is YoutubeResource.Loading -> {
                            Log.d(TAG, "onCreateView: Loading")
                        }
                        is YoutubeResource.Error -> {
                            Log.d(TAG, "onCreateView: Error")
                        }
                        is YoutubeResource.Success -> {
                            Log.d(TAG, "onCreateView: ${it.youtubeData}")

                            mainVPAdapter = MainVPAdapter(requireActivity(), it.youtubeData.items)
                            binding.view.adapter = mainVPAdapter
                            
                        }
                    }
                }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShortsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShortsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}