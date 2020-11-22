package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CountryAdapter
import com.mbds.newsletter.changeFragment
import com.mbds.newsletter.services.SourceServiceImpl
import com.mbds.newsletter.model.CountryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [CountriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesFragment : Fragment() {

    private  lateinit var sourceService : SourceServiceImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            getData(view);
        }
    }

    private suspend fun getData(view: View){
        withContext(Dispatchers.IO){
            //val result = repository.list()
            val result = sourceService.getCountries().toSet().toMutableList()
            //from list of source to list of country
            val resultDto = result.map {
                CountryDto.newInstance(it.country)
            }
            bindData(resultDto, view)
        }
    }

    private suspend fun bindData(result : List<CountryDto>?, view: View){
        withContext(Dispatchers.Main){
            //display data in the recycler
            /* val textView = view?.findViewById<TextView>(R.id.text1)
               textView?.text = result?.get(0)?.description*/
            val recyclerView: RecyclerView = view.findViewById(R.id.country_recycler_view)
            val listOfSources = result ?: emptyList()
            val sourceAdapter = CountryAdapter(listOfSources.toMutableList()){
                itemClicked(it)
            }
            val gridLayoutManager = GridLayoutManager(view.context, 1)
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.adapter = sourceAdapter
        }
    }

    private fun itemClicked(country : CountryDto){
        Toast.makeText(
            context,
            country.countryName,
            Toast.LENGTH_LONG
        ).show()
        (activity as? MainActivity)?.changeFragment(
            ArticlesFragment.newInstance(countryId = country.countryCode)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CountriesFragment().apply { this.sourceService = SourceServiceImpl() }
    }
}