package com.nikitamantush.ticketfinder.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitamantush.ticketfinder.App
import com.nikitamantush.ticketfinder.data.repository.SharedPreferencesRepository
import com.nikitamantush.ticketfinder.databinding.FragmentExploreBinding
import com.nikitamantush.ticketfinder.presentation.mapper.toOfferUIModel
import com.nikitamantush.ticketfinder.presentation.model.OfferUIModel
import com.nikitamantush.ticketfinder.presentation.ui.home.adapter.EventListAdapter
import com.nikitamantush.ticketfinder.presentation.ui.search.DestinationDialogFragment
import javax.inject.Inject

class ExploreFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    @Inject
    lateinit var viewModelProvider: ExploreViewModelFactory

    private var binding: FragmentExploreBinding? = null

    private val viewModel: ExploreViewModel by viewModels {
        viewModelProvider
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesRepository.getDepartureFrom()?.let {
            if (it.isNotBlank()) {
                binding?.etFromTextView?.setText(it)
            }
        }


        binding?.etToTextView?.setOnClickListener {
            sharedPreferencesRepository.setDepartureFrom(binding?.etFromTextView?.text.toString())
            val bottomSheetFragment =
                DestinationDialogFragment.newInstance(binding?.etFromTextView?.text.toString())
            bottomSheetFragment.show(parentFragmentManager, null)
        }

        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            if (offers.isNotEmpty()) {
                setList(offers.map { it.toOfferUIModel() }, binding?.recyclerView)
            } else {
                Toast.makeText(requireContext(), "No data", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loadOfferList()

    }

    private fun setList(list: List<OfferUIModel>, recyclerView: RecyclerView?) {
        recyclerView?.run {
            if (adapter == null) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = EventListAdapter()
            }
            (adapter as? EventListAdapter)?.submitList(list)
        }
    }

}