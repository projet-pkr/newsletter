package com.mbds.newsletter.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.databinding.FragmentDetailsArticleBinding
import com.mbds.newsletter.model.Article

class AticleDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsArticleBinding
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getSerializable("Article_Details") as Article
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsArticleBinding.inflate(inflater,container,false)

        binding.articleTitle.text = article?.title
        binding.articleSource.text = article?.source?.name
        binding.articleDescription.text = article?.description
        binding.articleDetail.text = article?.content
        binding.redirectionArticle.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(article?.url)
            intent.setPackage("com.android.chrome")
            startActivity(intent)
        }

        Glide
            .with(binding.root)
            .load(article?.urlToImage)
            .fitCenter()
            .placeholder(R.drawable.placeholder)
            .into(binding.articleImage)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArticlesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(article: Article) =
            AticleDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("Article_Details", article)
                }
            }
    }
}