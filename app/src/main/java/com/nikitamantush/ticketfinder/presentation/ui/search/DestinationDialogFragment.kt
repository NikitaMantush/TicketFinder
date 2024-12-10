package com.nikitamantush.ticketfinder.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nikitamantush.ticketfinder.R
import com.nikitamantush.ticketfinder.databinding.FragmentDialogDestinationBinding
import com.nikitamantush.ticketfinder.presentation.ui.country_selected.CountrySelectedFragment
import com.nikitamantush.ticketfinder.presentation.util.openFragmentWithBottomMenu

class DestinationDialogFragment : BottomSheetDialogFragment() {

    private var binding: FragmentDialogDestinationBinding? = null

    companion object {

        private const val ARG_FROM_TEXT = "etFromTextView"

        fun newInstance(fromText: String): DestinationDialogFragment {
            return DestinationDialogFragment().apply {
                arguments = bundleOf(ARG_FROM_TEXT to fromText)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogDestinationBinding.inflate(inflater)
        return binding?.root

    }

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            val bottomSheet =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val layoutParams = it.layoutParams
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                layoutParams.height =
                    resources.getDimensionPixelSize(R.dimen.dialog_height)
                it.layoutParams = layoutParams
            }

            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.etFromEditText?.setText(arguments?.getString(ARG_FROM_TEXT))

        binding?.crossImageVIew?.setOnClickListener {
            binding?.etToEditText?.text?.clear()
        }


        binding?.etToEditText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {

                val bundle = Bundle().apply {
                    putString("etToEditText", binding?.etToEditText?.text.toString())
                    putString("etFromEditText", binding?.etFromEditText?.text.toString())
                }

                parentFragmentManager.openFragmentWithBottomMenu(CountrySelectedFragment().apply {
                    arguments = bundle
                })
                dismissAllowingStateLoss()

                true
            } else {
                false
            }
        }

    }
}
