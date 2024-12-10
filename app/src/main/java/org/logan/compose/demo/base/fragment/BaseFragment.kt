package org.logan.compose.demo.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment


/**
 * desc: 基类Fragment <br/>
 * time: 2024/12/10 14:30 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
abstract class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                MyFragmentView()
            }
        }
    }

    @Composable
    abstract fun MyFragmentView()

}