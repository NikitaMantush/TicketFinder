package com.nikitamantush.ticketfinder.presentation.ui.tickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitamantush.ticketfinder.App
import com.nikitamantush.ticketfinder.databinding.FragmentTicketsBinding
import com.nikitamantush.ticketfinder.presentation.mapper.toTicketUIModel
import com.nikitamantush.ticketfinder.presentation.model.TicketUIModel
import com.nikitamantush.ticketfinder.presentation.ui.country_selected.CountrySelectedFragment
import com.nikitamantush.ticketfinder.presentation.ui.tickets.adapter.TicketsListAdapter
import com.nikitamantush.ticketfinder.presentation.util.openFragmentWithBottomMenu
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import javax.inject.Inject

class TicketsFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: TicketsViewModelFactory

    private var binding: FragmentTicketsBinding? = null

    private val viewModel: TicketsViewModel by viewModels {
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
        binding = FragmentTicketsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))

        binding?.run {
            currentRouteTextView.text = arguments?.getString("route")

            currentRouteInfoTextView.text = "${dateFormat.format(currentDate)}, 1 пассажир"

            backImageView.setOnClickListener {
                parentFragmentManager.openFragmentWithBottomMenu(CountrySelectedFragment())
            }
        }
        viewModel.tickets.observe(viewLifecycleOwner) { tickets ->
            if (!tickets.isNullOrEmpty()) {
                setList(
                    tickets.map { it.toTicketUIModel() },
                    binding?.allTicketsRecyclerView
                )
            }
        }
        viewModel.loadTicketList()
    }

    private fun setList(list: List<TicketUIModel>, recyclerView: RecyclerView?) {
        recyclerView?.run {
            if (adapter == null) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = TicketsListAdapter()
            }
            (adapter as? TicketsListAdapter)?.submitList(list)
        }
    }
}