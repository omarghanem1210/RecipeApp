import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.SearchResponse
import com.example.recipeapp.SearchResult
import com.example.recipeapp.recipes.repo.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableLiveData<List<SearchResult>>()
    val searchResults: LiveData<List<SearchResult>> get() = _searchResults

    fun search(query: String) {
        Repository.search(query, object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    _searchResults.value = response.body()?.meals ?: emptyList()
                } else {
                    // Handle unsuccessful response
                    _searchResults.value = emptyList()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // Handle failure
                _searchResults.value = emptyList()
            }
        })
    }
}
