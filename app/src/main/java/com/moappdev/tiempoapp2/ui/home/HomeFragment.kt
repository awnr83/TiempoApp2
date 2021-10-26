package com.moappdev.tiempoapp2.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moappdev.tiempoapp2.R
import com.moappdev.tiempoapp2.database.TiempoDatabase
import com.moappdev.tiempoapp2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel: HomeViewModel
    private lateinit var mAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentHomeBinding.inflate(inflater)

        val aplication= requireNotNull(this.activity).application
        val db= TiempoDatabase.getInstance(aplication)

        val viemodelFactory= HomeVMFactory(db!!)
        mViewModel= ViewModelProvider(this,viemodelFactory).get(HomeViewModel::class.java)
        mBinding.viewmodel=mViewModel
        mBinding.lifecycleOwner=this

        mAdapter= HomeAdapter()
        mBinding.rvCiudades.adapter=mAdapter
        mViewModel.ciudades.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })


        return mBinding.root
    }

    class HomeVMFactory(private val db:TiempoDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(HomeViewModel::class.java))
                return HomeViewModel(db) as T
            throw IllegalArgumentException("Error al crear el ViewModel")
        }
    }
}