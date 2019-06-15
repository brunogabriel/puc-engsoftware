package io.github.brunogabriel.doggieapp.main.petlist

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.shared.extensions.pxToDp
import io.github.brunogabriel.doggieapp.shared.models.Pet
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
        showPets(listOf(
            Pet(1L, 1L, "Cachorro", "Bob", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),
            Pet(1L, 1L, "Cachorro", "Billy", "Medium", emptyList(), emptyList(), emptyList(),
                listOf("http://portalmelhoresamigos.com.br/wp-content/uploads/2015/11/poodle_cachorro.png")),

            Pet(1L, 1L, "Cavalo", "Pé de Pano", null, emptyList(), emptyList(), emptyList(),
                listOf("https://hopereins.org/wp-content/uploads/2018/06/Hope-Reins-Horse-Stories-Deetz-800x600.jpg"))))
    }

    override fun showPets(pets: List<Pet>) {
        recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PetListAdapter(pets)
            addItemDecoration(EqualGapItemDecoration(2, 16.pxToDp()))
        }
    }

    override fun showEmptyResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}