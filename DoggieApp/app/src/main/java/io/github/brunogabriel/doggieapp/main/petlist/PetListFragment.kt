package io.github.brunogabriel.doggieapp.main.petlist

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.petdetails.PetDetailsActivity
import io.github.brunogabriel.doggieapp.shared.extensions.pxToDp
import io.github.brunogabriel.doggieapp.shared.models.Pet
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence
import io.github.brunogabriel.doggieapp.shared.view.EqualGapItemDecoration
import kotlinx.android.synthetic.main.fragment_pet_list.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class PetListFragment : Fragment(), PetListContract.View {

    override lateinit var presenter: PetListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_pet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PetListPresenter(
            this,
            UserAuthenticationPersistence(context!!).loadUserAuthenticated()!!
        ).apply {
            initialize()
        }
        try_again_button.setOnClickListener {
            presenter.initialize()
        }
    }

    override fun showPets(pets: List<Pet>) {
        recycler_view.apply {
            visibility = View.VISIBLE
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PetListAdapter(pets) {
                startActivity(Intent(context, PetDetailsActivity::class.java).putExtra(PetDetailsActivity.PET_DETAILS, it))
            }
            addItemDecoration(EqualGapItemDecoration(2, 16.pxToDp()))
        }
    }

    override fun showEmptyResult() {
        empty_view.visibility = View.VISIBLE
    }

    override fun showTryAgain() {
        try_again_view.visibility = View.VISIBLE
    }

    override fun showLoading() {
        try_again_view.visibility = View.GONE
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading_view.visibility = View.GONE
    }
}