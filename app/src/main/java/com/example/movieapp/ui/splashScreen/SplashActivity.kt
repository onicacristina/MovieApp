package com.example.movieapp.ui.splashScreen


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.ui.actors.ActorRepository
import com.example.movieapp.ui.genres.GenreRepository
import com.example.movieapp.ui.onBoardingScreen.OnBoardingScreenActivity
import com.example.movieapp.ui.searchScreen.SearchActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DELAY = 2000L

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val genresRepository = GenreRepository.instance
    private val actorsRepository = ActorRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupAnimation()
        initHandlerToOpenNextActivity()
    }

    private fun setupAnimation(){
        var imageView = findViewById<ImageView>(R.id.imageSplash)
        var textView = findViewById<TextView>(R.id.tv_splash)
        var top = AnimationUtils.loadAnimation(this, R.anim.top)
        var bottom = AnimationUtils.loadAnimation(this, R.anim.bottom)
        imageView.animation = top
        textView.animation = bottom
    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }
        handler?.postDelayed(runnable!!, DELAY)
    }

    private fun openNextScreen() {
        isSaved()
        finish()
    }

    private fun isSaved(){
        GlobalScope.launch (Dispatchers.IO){
            val genreCount = genresRepository.getCount()
            val actorCount = actorsRepository.getCount()
            withContext(Dispatchers.Main){
                verifyIsSaved(genreCount, actorCount)
            }
        }
    }

    private fun verifyIsSaved(genreCount: Int, actorCount: Int){
        val isSaved = genreCount > 0 && actorCount > 0
        if(isSaved)
            SearchActivity.open(this)
        else
            OnBoardingScreenActivity.open(this)

    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}