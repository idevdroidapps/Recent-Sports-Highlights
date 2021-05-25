package com.sports.today.presentation.clickListeners

import com.sports.today.domain.entities.Highlight
import com.sports.today.presentation.viewmodels.SharedViewModel

class HighlightClickListener(
    private val sharedViewModel: SharedViewModel
) : (Highlight) -> Unit {

    override fun invoke(highlight: Highlight) {
        sharedViewModel.selectedHighlight = highlight
        sharedViewModel.showDetails(true)
    }

}