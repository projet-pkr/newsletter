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
import com.mbds.newsletter.data.Data
import com.mbds.newsletter.services.SourceServiceImpl
import com.mbds.newsletter.databinding.FragmentCategoriesBinding
import com.mbds.newsletter.model.Category


class SubCategoriesFragment : Fragment() {

    lateinit var binding : FragmentCategoriesBinding
    private  lateinit var sourceService : SourceServiceImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        val categories : List<Category> = Data.getCategories()
        val categoryAdapter = CategoryAdapter(categories){
            itemClicked(it)
        }
        recyclerView.layoutManager = GridLayoutManager(view.context, 1)

        recyclerView.adapter = categoryAdapter
    }
    private fun itemClicked(category: Category) {
        // context?.toast(category.name)
        Toast.makeText(
            context,
            category.name,
            Toast.LENGTH_LONG
        ).show()
        (activity as? MainActivity)?.changeFragment(
           ArticlesFragment.newInstance(category = category.type)
        )
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SubCategoriesFragment().apply {
                this.sourceService = SourceServiceImpl()
            }
    }
}