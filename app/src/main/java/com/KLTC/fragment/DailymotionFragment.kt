package com.KLTC.fragment

import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.KLTC.R
import com.dailymotion.android.player.sdk.PlayerWebView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DailymotionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailymotionFragment(context:Context) : Fragment() {

    var listener: FragmentActivity? = null
     private var playerWebView:PlayerWebView = PlayerWebView(context,null, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.daily_motion_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //video id
        val videoTag = this.arguments?.getString("message")
        playerWebView = requireView().findViewById(R.id.dm_player_web_view)
        val params = mapOf(
            "video" to videoTag,
            "companionAds" to "false")
        playerWebView.load(params);


    }




    override fun onDetach() {
        super.onDetach()
        playerWebView.release()


    }


}


