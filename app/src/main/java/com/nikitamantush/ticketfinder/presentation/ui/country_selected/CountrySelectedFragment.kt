package com.nikitamantush.ticketfinder.presentation.ui.country_selected

import android.app.DatePickerDialog
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
import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.databinding.FragmentCountrySelectedBinding
import com.nikitamantush.ticketfinder.presentation.mapper.toTicketOfferUIModel
import com.nikitamantush.ticketfinder.presentation.model.DetailTripUIModel
import com.nikitamantush.ticketfinder.presentation.model.TicketOfferUIModel
import com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.detail_trip.DetailTripAdapter
import com.nikitamantush.ticketfinder.presentation.ui.country_selected.adapter.straight_rails.StraightRailsAdapter
import com.nikitamantush.ticketfinder.presentation.ui.home.ExploreFragment
import com.nikitamantush.ticketfinder.presentation.ui.tickets.TicketsFragment
import com.nikitamantush.ticketfinder.presentation.util.openFragmentWithBottomMenu
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CountrySelectedFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: CountrySelectedViewModelFactory

    private var binding: FragmentCountrySelectedBinding? = null

    private val viewModel: CountrySelectedViewModel by viewModels {
        viewModelProvider
    }

    private val itemList = mutableListOf(
        DetailTripUIModel("обратно", R.drawable.ic_plus, isEnabled = true),
        DetailTripUIModel(getCurrentFormattedDate(), null, isEnabled = true),
        DetailTripUIModel("1,эконом", R.drawable.ic_human, isEnabled = false),
        DetailTripUIModel("фильтры", R.drawable.ic_filter, isEnabled = false)
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountrySelectedBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            etToEditText.setText(arguments?.getString("etToEditText"))
            etFromEditText.setText(arguments?.getString("etFromEditText"))

            reverseImageView.setOnClickListener {
                val toText = etToEditText.text.toString()
                val fromText = etFromEditText.text.toString()
                etToEditText.setText(fromText)
                etFromEditText.setText(toText)
            }
            backImageView.setOnClickListener {
                parentFragmentManager.openFragmentWithBottomMenu(ExploreFragment())
            }
            crossImageVIew.setOnClickListener {
                etToEditText.text.clear()
            }

            setDetailList(itemList, recyclerView)
            viewAllTicketsButton.setOnClickListener {
                val route = "${etFromEditText.text} - ${etToEditText.text}"
                val bundle = Bundle().apply { putString("route", route) }
                parentFragmentManager.openFragmentWithBottomMenu(TicketsFragment().apply {
                    arguments = bundle
                })
            }
        }

        viewModel.ticketOffer.observe(viewLifecycleOwner) { offers ->
            if (offers != null && offers.isNotEmpty()) {
                setList(
                    offers.map { it.toTicketOfferUIModel() },
                    binding?.straightRailsRecyclerView
                )
            }
        }

        viewModel.loadTicketOfferList()
    }

    private fun setDetailList(list: List<DetailTripUIModel>, recyclerView: RecyclerView?) {
        recyclerView?.run {
            if (adapter == null) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = DetailTripAdapter { position -> onItemClicked(position) }
            }
            (adapter as? DetailTripAdapter)?.submitList(list)
        }
    }

    private fun setList(list: List<TicketOfferUIModel>, recyclerView: RecyclerView?) {
        recyclerView?.run {
            if (adapter == null) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = StraightRailsAdapter()
            }
            (adapter as? StraightRailsAdapter)?.submitList(list)
        }
    }

    private fun onItemClicked(position: Int) {
        when (position) {
            0, 1 -> showDatePicker { selectedDate ->
                itemList[position] = itemList[position].copy(text = selectedDate)
                binding?.recyclerView?.adapter?.notifyItemChanged(position)
            }
        }
    }

    private fun getCurrentFormattedDate(): String {
        return getFormattedDate(Calendar.getInstance())
    }

    private fun getFormattedDate(calendar: Calendar): String {
        val sdf = SimpleDateFormat("dd MMM, EE", Locale("ru"))
        return sdf.format(calendar.time)
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(selectedYear, selectedMonth, selectedDay)
            val sdf = SimpleDateFormat("dd MMM, EE", Locale("ru"))
            val formattedDate = sdf.format(selectedCalendar.time)
            onDateSelected(formattedDate)
        }, year, month, day).show()
    }
}
