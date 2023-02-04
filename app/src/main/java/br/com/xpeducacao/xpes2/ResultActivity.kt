package br.com.xpeducacao.xpes2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.xpeducacao.xpes2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
        iniciarDados()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun configListeners() {
        binding.buttonRetornar.setOnClickListener {
            Intent().apply {
                putExtra(
                    "FABRICIO",
                    getString(R.string.msg_retorno_link_enquete)
                )
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }

    private fun iniciarDados() {
        val message = intent.getStringExtra(MainActivity.TAG)

        binding.textViewResposta.text = message
    }
}