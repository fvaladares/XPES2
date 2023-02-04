package br.com.xpeducacao.xpes2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.com.xpeducacao.xpes2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val retorno = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(TAG_RETORNO)) {
                    binding.textViewMensagemRetorno.text = it.getStringExtra(TAG_RETORNO)
                } else {
                    Log.e("XPE.e", getString(R.string.err_tag_nao_encontrada))
                }
            }
        } else {
            Log.e("XPE.e", getString(R.string.err_sem_dados))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners() {
        binding.buttonEnviar.setOnClickListener {

            val message = binding.editTextMessage.text.toString()

            if (message.isBlank()) {
                binding.tilMessage.error = getString(R.string.err_campo_vazio)
            } else {
                binding.tilMessage.error = null
                Intent(
                    this,
                    ResultActivity::class.java
                ).apply {
                    putExtra(TAG, message)
                    retorno.launch(this)
                }
            }
        }
    }

    companion object {
        const val TAG = "tag_envio"
        const val TAG_RETORNO = "tag_retorno"
    }
}