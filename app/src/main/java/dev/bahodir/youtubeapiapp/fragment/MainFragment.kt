package dev.bahodir.youtubeapiapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.bahodir.youtubeapiapp.R
import dev.bahodir.youtubeapiapp.adapter.MainVPAdapter
import dev.bahodir.youtubeapiapp.databinding.BottomDialogBinding
import dev.bahodir.youtubeapiapp.databinding.FragmentMainBinding
import dev.bahodir.youtubeapiapp.fragment.main.HomeFragment
import dev.bahodir.youtubeapiapp.fragment.main.LibraryFragment
import dev.bahodir.youtubeapiapp.fragment.main.ShortsFragment
import dev.bahodir.youtubeapiapp.fragment.main.SubscriptionsFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainVPAdapter: MainVPAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        requireActivity().supportFragmentManager.commit {
            add<HomeFragment>(R.id.view)
        }

        binding.home.setOnClickListener {
            binding.home.setBackgroundResource(R.drawable.ic_home_black)
            binding.shorts.setBackgroundResource(R.drawable.ic_shorts_white)
            binding.add.setBackgroundResource(R.drawable.ic_add_white)
            binding.subscription.setBackgroundResource(R.drawable.ic_sub_white)
            binding.library.setBackgroundResource(R.drawable.ic_lib_white)

            requireActivity().supportFragmentManager.commit {
                add<HomeFragment>(R.id.view)
            }
        }
        binding.shorts.setOnClickListener {
            binding.home.setBackgroundResource(R.drawable.ic_home_white)
            binding.shorts.setBackgroundResource(R.drawable.ic_shorts_on)
            binding.add.setBackgroundResource(R.drawable.ic_add_white)
            binding.subscription.setBackgroundResource(R.drawable.ic_sub_white)
            binding.library.setBackgroundResource(R.drawable.ic_lib_white)

            requireActivity().supportFragmentManager.commit {
                add<ShortsFragment>(R.id.view)
            }
            binding.constraint.visibility = View.GONE
            binding.navView.visibility = View.GONE
        }
        binding.add.setOnClickListener {
            binding.home.setBackgroundResource(R.drawable.ic_home_white)
            binding.shorts.setBackgroundResource(R.drawable.ic_shorts_white)
            binding.add.setBackgroundResource(R.drawable.ic_add_black)
            binding.subscription.setBackgroundResource(R.drawable.ic_sub_white)
            binding.library.setBackgroundResource(R.drawable.ic_lib_white)

            var bind  = BottomDialogBinding.inflate(layoutInflater)
            var bottom = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
            bottom.setContentView(bind.root)

            bind.clear.setOnClickListener {
                bottom.dismiss()
            }

            bottom.show()
        }
        binding.subscription.setOnClickListener {
            binding.home.setBackgroundResource(R.drawable.ic_home_white)
            binding.shorts.setBackgroundResource(R.drawable.ic_shorts_white)
            binding.add.setBackgroundResource(R.drawable.ic_add_white)
            binding.subscription.setBackgroundResource(R.drawable.ic_sub_black)
            binding.library.setBackgroundResource(R.drawable.ic_lib_white)

            requireActivity().supportFragmentManager.commit {
                add<SubscriptionsFragment>(R.id.view)
            }
        }
        binding.library.setOnClickListener {
            binding.home.setBackgroundResource(R.drawable.ic_home_white)
            binding.shorts.setBackgroundResource(R.drawable.ic_shorts_white)
            binding.add.setBackgroundResource(R.drawable.ic_add_white)
            binding.subscription.setBackgroundResource(R.drawable.ic_sub_white)
            binding.library.setBackgroundResource(R.drawable.ic_lib_black)

            requireActivity().supportFragmentManager.commit {
                add<LibraryFragment>(R.id.view)
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
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}