package br.com.andreguedes.alodjinha.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Banner
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var bannerAdapter : HomeBannerPagerAdapter
    private lateinit var timer : Timer
    var currentBanner = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()

        initUI()
        addListeners()
    }

    override fun onPause() {
        super.onPause()

        timer.cancel()
        presenter.unsubscribe()
    }

    override fun initUI() {
        presenter = HomePresenter(this)
        presenter.subscribe()

        setupBanners()
    }

    override fun addListeners() {
        pager_banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentBanner = position
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {}
        })
    }

    private fun setupBanners() {
        bannerAdapter = HomeBannerPagerAdapter(context)
        pager_banner.adapter = bannerAdapter
        banner_indicator.setViewPager(pager_banner)
        bannerAdapter.registerDataSetObserver(banner_indicator.dataSetObserver)

        setupIndicator()
    }

    override fun setBanners(banners: List<Banner>) {
        progress_banner.visibility = View.GONE
        bannerAdapter.setBanners(banners)
    }

    private fun setupIndicator() {
        val handler = Handler()
        val timerTask = object: TimerTask() {
            override fun run() {
                handler.post {
                    if (currentBanner == pager_banner.adapter?.count) currentBanner = 0
                    pager_banner.setCurrentItem(currentBanner++, true)
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }

}