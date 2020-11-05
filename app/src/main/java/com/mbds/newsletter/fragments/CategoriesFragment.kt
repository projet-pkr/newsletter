package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CategoryAdapter
import com.mbds.newsletter.changeFragment
import com.mbds.newsletter.databinding.FragmentCategoriesBinding
import com.mbds.newsletter.model.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentCategoriesBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        //return inflater.inflate(R.layout.fragment_categories, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        val categories : List<Category> = listOf(
            Category("Editeur","https://picsum.photos/seed/picsum/500/300"),
            Category("Categories","https://picsum.photos/seed/picsum/500/300"),
            Category("Pays","https://picsum.photos/seed/picsum/500/300")
        )
        val categoryAdapter = CategoryAdapter(categories){
            itemClicked(it)
        }
        recyclerView.layoutManager = GridLayoutManager(view.context, 1)

        recyclerView.adapter = categoryAdapter
    }
    private fun itemClicked(category: Category ) {
        // context?.toast(category.name)
        Toast.makeText(
            context,
            category.name,
            Toast.LENGTH_LONG
        ).show()
        /*(activity as? MainActivity)?.changeFragment(
            //sub-categories like editor

        )*/

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}