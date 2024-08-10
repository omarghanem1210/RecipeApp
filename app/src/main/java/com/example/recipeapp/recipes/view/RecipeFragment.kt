import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.recipeapp.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class RecipeFragment : Fragment() {

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var overlayContainer: FrameLayout
    private lateinit var videoThumbnail: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe2, container, false)

        // Find views
        overlayContainer = view.findViewById(R.id.overlay_video_container)
        youTubePlayerView = view.findViewById(R.id.youtube_player_view)
        videoThumbnail = view.findViewById(R.id.video_thumbnail)

        // Set up YouTube player lifecycle
        lifecycle.addObserver(youTubePlayerView)

        // Set up click listener on video thumbnail
        videoThumbnail.setOnClickListener {
            // Hide the thumbnail and show the video overlay
            videoThumbnail.visibility = View.GONE
            playVideo("2dR6SzUwbpY")  // Use the video ID directly
        }

        return view
    }

    private fun playVideo(videoId: String) {
        // Make the overlay visible
        overlayContainer.visibility = View.VISIBLE

        // Initialize the YouTube player and play the video
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Log.d("RecipeFragment", "YouTubePlayer is ready")  // Debug log
                try {
                    Log.d("RecipeFragment", "Loading video with ID: $videoId")  // Debug log
                    youTubePlayer.loadVideo(videoId, 0f)
                } catch (e: Exception) {
                    Log.e("RecipeFragment", "Error loading video: ${e.message}")
                }
            }
        })

        // Handle click on overlay to dismiss the player
        overlayContainer.setOnClickListener {
            stopVideo()
        }
    }

    private fun stopVideo() {
        // Stop the video and hide the overlay
        youTubePlayerView.release()
        overlayContainer.visibility = View.GONE
        videoThumbnail.visibility = View.VISIBLE  // Show the thumbnail again if needed
    }
}
