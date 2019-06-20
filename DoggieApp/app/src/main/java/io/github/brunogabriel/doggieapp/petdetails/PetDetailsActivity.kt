package io.github.brunogabriel.doggieapp.petdetails

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.shared.models.Service
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence
import kotlinx.android.synthetic.main.activity_pet_details.*
import kotlinx.android.synthetic.main.content_pet_details.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class PetDetailsActivity : AppCompatActivity(), PetDetailsContract.View {

    companion object {
        const val PET_DETAILS = "PET_DETAILS"
    }

    override lateinit var presenter: PetDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details)
        setupView()

        presenter = PetDetailsPresenter(this, intent.getParcelableExtra(PET_DETAILS),
            UserAuthenticationPersistence(this).loadUserAuthenticated()!!).apply {
            initialize()
        }
    }

    override fun showPetImages(images: List<String>) {
        view_pager.adapter = ImageViewPageAdapter(images)
    }

    override fun showPetDetails(name: String, type: String, gait: String?) {
        pet_name.text = name
        pet_type_text.text = type
        gait?.let {
            pet_gait.visibility = View.VISIBLE
            pet_gait_text.visibility = View.VISIBLE
            pet_gait_text.text = gait
        }

    }

    override fun showPetAllergies(allergies: List<String>) {
        pet_allergies.visibility = View.VISIBLE
        pet_allergies_text.apply {
            visibility = View.VISIBLE
            text = allergies.joinToString("\n")
        }
    }

    override fun showPetHabits(habits: List<String>) {
        pet_habits.visibility = View.VISIBLE
        pet_habits_text.apply {
            visibility = View.VISIBLE
            text = habits.joinToString("\n")
        }
    }

    override fun showPetPreferences(preferences: List<String>) {
        pet_preferences.visibility = View.VISIBLE
        pet_preferences_text.apply {
            visibility = View.VISIBLE
            text = preferences.joinToString("\n")
        }
    }

    private fun setupView() {
        toolbar.apply {
            setSupportActionBar(this)
            navigationIcon?.setColorFilter(ContextCompat.getColor(this@PetDetailsActivity, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    private inner class ImageViewPageAdapter(private val images: List<String>) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object` as ImageView
        }
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context).inflate(R.layout.holder_image_view_item, container, false) as ImageView
            Picasso.get().load(images[position]).into(view)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as ImageView)
        }
        override fun getCount() = images.size
    }

    override fun showEmptyResult() {
        empty_view.visibility = View.VISIBLE
    }

    override fun showServices(services: List<Service>) {
        services_index_text.visibility = View.VISIBLE
        recycler_view.apply {
            visibility = View.VISIBLE
            val layoutManager = LinearLayoutManager(this@PetDetailsActivity)
            setLayoutManager(layoutManager)
            isNestedScrollingEnabled = false
            adapter = PetServicesAdapter(services)
        }
    }

    override fun showTryAgain() {
        try_again_view.apply {
            visibility = View.VISIBLE
            try_again_button.setOnClickListener { presenter.onSelectedTryAgain() }
        }
    }

    override fun showLoading() {
        try_again_view.visibility = View.GONE
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading_view.visibility = View.GONE
    }
}